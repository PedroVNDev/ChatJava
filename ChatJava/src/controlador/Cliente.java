package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.*;


public class Cliente {


	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MarcoCliente mimarco=new MarcoCliente();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{

	public MarcoCliente(){

		setBounds(600,300,280,350);
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		add(milamina);
		setVisible(true);

		addWindowListener(new EnvioOnline());
	}	

}

//---------------------Envio de Señal Online-------------------//

class EnvioOnline extends WindowAdapter{

	public void windowOpened(WindowEvent e) {

		try {

			//Cambiar IP a la del equipoServidor
			Socket socketVentana = new Socket("10.10.200.113", 9999);

			PaqueteEnvio datos = new PaqueteEnvio();
			datos.setMensaje(" Online");

			ObjectOutputStream paquete_datos = new ObjectOutputStream(socketVentana.getOutputStream());
			paquete_datos.writeObject(datos);

			socketVentana.close();

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

}

//---------------------Envio de Señal Online-------------------//

class LaminaMarcoCliente extends JPanel implements Runnable{

	public LaminaMarcoCliente(){

		String nick_usuario = JOptionPane.showInputDialog("Nick: ");

		JLabel n_nick = new JLabel("Nick: ");
		add(n_nick);

		nick = new JLabel();
		nick.setText(nick_usuario);
		add(nick);

		JLabel texto=new JLabel("Online: ");
		add(texto);

		ip = new JComboBox();
		add(ip);

		campochat = new JTextArea(12,20);
		add(campochat);

		campo1=new JTextField(20);
		add(campo1);	

		miboton=new JButton("Enviar");
		EnviaTexto mievento = new EnviaTexto();
		miboton.addActionListener(mievento);

		add(miboton);	

		Thread mihilo = new Thread(this);
		mihilo.start();

	}

	private class EnviaTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			campochat.append("\n" + campo1.getText());

			try {

				Socket socketCliente;
				//Cambiar IP a la del equipoServidor
				socketCliente = new Socket("10.10.200.113", 9999);

				PaqueteEnvio datos = new PaqueteEnvio();

				datos.setNick(nick.getText());
				datos.setIp(ip.getSelectedItem().toString());
				datos.setMensaje(campo1.getText());

				ObjectOutputStream paquete_datos = new ObjectOutputStream(socketCliente.getOutputStream());
				paquete_datos.writeObject(datos);

				socketCliente.close();

				/* Envio
				DataOutputStream flujo_salida = new DataOutputStream(socketCliente.getOutputStream());
				flujo_salida.writeUTF(campo1.getText());
				flujo_salida.close(); */

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}


		}

	}

	private JTextField campo1;
	private JComboBox ip;
	private JLabel nick;
	private JButton miboton;
	private JTextArea campochat;

	@Override
	public void run() {

		try {

			ServerSocket servidor_cliente = new ServerSocket(9090);
			Socket cliente;

			PaqueteEnvio paqueteRecibido;

			while(true) {

				cliente = servidor_cliente.accept();
				ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
				paqueteRecibido = (PaqueteEnvio) flujoentrada.readObject();

				if(!paqueteRecibido.getMensaje().contentEquals(" Online")) {
					campochat.append("\n" + paqueteRecibido.getNick() + ": " + paqueteRecibido.getMensaje());
				}else {
					ArrayList<String> ipsMenu = new ArrayList<String>();
					ipsMenu = paqueteRecibido.getIps();
					ip.removeAllItems();
					for(String z: ipsMenu) {
						ip.addItem(z);
					}

				}

			}


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

}