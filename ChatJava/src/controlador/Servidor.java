package controlador;

import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoServidor mimarco=new MarcoServidor();

		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}	
}

class MarcoServidor extends JFrame implements Runnable{

	public MarcoServidor(){

		setBounds(1200,300,280,350);				
		JPanel milamina= new JPanel();
		milamina.setLayout(new BorderLayout());

		textAreaServidor=new JTextArea();

		milamina.add(textAreaServidor,BorderLayout.CENTER);
		add(milamina);
		setVisible(true);

		Thread hilo= new Thread(this);
		hilo.start();

	}

	private	JTextArea textAreaServidor;

	@Override
	public void run() {

		try {

			ServerSocket servidor = new ServerSocket(9999);

			String nick, ip, mensaje_cliente;
			ArrayList <String> listaIp = new ArrayList <String>();
			PaqueteEnvio paquete_recibido;

			while(true) {

				Socket socketServidor = servidor.accept();

				ObjectInputStream paquete_datos = new ObjectInputStream(socketServidor.getInputStream());
				paquete_recibido = (PaqueteEnvio) paquete_datos.readObject();

				nick =  paquete_recibido.getNick();
				ip =  paquete_recibido.getIp();
				mensaje_cliente =  paquete_recibido.getMensaje();

				if(!mensaje_cliente.equals(" Online")) {

					textAreaServidor.append("\n" + nick + ": " + mensaje_cliente);

					Socket enviaDestinatario = new Socket(ip, 9090);
					ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());

					paqueteReenvio.writeObject(paquete_recibido);

					paqueteReenvio.close();
					enviaDestinatario.close();
					socketServidor.close();

				}else {

					//---------------------Detecta Online-------------------//

					InetAddress localizacion = socketServidor.getInetAddress();

					String ipRemota = localizacion.getHostAddress();

					listaIp.add(ipRemota);
					paquete_recibido.setIps(listaIp);

					for(String z:listaIp) {

						System.out.println("Array: " + z);

						Socket enviaDestinatario = new Socket(z, 9090);
						ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());

						paqueteReenvio.writeObject(paquete_recibido);

						paqueteReenvio.close();
						enviaDestinatario.close();
						socketServidor.close();
					}

					//---------------------Detecta Online-------------------//

				}

				/* Envio
				DataInputStream flujo_entrada = new DataInputStream(socketServidor.getInputStream());

				String mensaje = flujo_entrada.readUTF();
				System.out.println(mensaje);
				textAreaServidor.append("\n" + mensaje); */

			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
