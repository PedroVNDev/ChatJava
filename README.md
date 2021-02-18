# Aplicación ChatJava 2 Trimestre PSP, 2ºDAM

Aplicaciones Java de escritorio que permiten la conexion a un servidor de chat mediante uno o varios clientes y el protocolo de conexion TCP, los cientes son capaces de elegir
un nick y elegir una ip (Si hay varios clientes conectados) para poder enviar mensajes entre si, el servidor recibe todos los mensajes y los reenvia a los clientes seleccionados
por el mismo cliente


### Pre-requisitos 📋

* Java 8 o Superior.
* Windows 10.

### Instalación 🔧

Puedes descargar el proyecto mediante el botón CODE o clonando el repositorio con git bash
--> $ git clone https://github.com/PinguExtremo/ChatJava


## Ejecución del programa ⚙️

# Importante:
* El servidor tiene una ip Fija en la clase cliente (10.10.200.113) Esta ip se debe de cambiar en caso de querer cambiar el equipo servidor esta realizado de esta manera porque no nos interesa tener varios servidores



El repositorio cuenta con dos .jar, cliente.jar y servidor.jar, para ejecutar la aplicacion primero iniciaremos el servidor y despues el cliente, para la optima experiencia 
con la aplicación tendremos que tener un cliente en 2 o mas equipos y estar en la misma red local. 

### Instrucciones de uso ⌨️

* Importante es recomendable desactivar el firewall en los equipos, ademas de ser obligatorio que esten en la misma red y rango de IP

-Primero iniciamos el servidor: 

<a href="https://imgur.com/wvkIEBn"><img src="https://i.imgur.com/wvkIEBn.png" title="source: imgur.com" /></a>

-Iniciamos el cliente, este nos pedira un nick:

<a href="https://imgur.com/xP7vaHl"><img src="https://i.imgur.com/xP7vaHl.png" title="source: imgur.com" /></a>

-En la ventana de el cliente podemos visualizar el nick que introducimos y la ip de nuestro equipo con la que nos vamos a identificar

<a href="https://imgur.com/2pFBtnH"><img src="https://i.imgur.com/2pFBtnH.png" title="source: imgur.com" /></a>

-Tenemos una lista que recoje las IPs de los clientes que se han conectado al servidor en este caso tendremos que seleccionar la ip de los equipos opuestos David es: 
10.10.26.9 y Alumno es: 10.10.200.113

<a href="https://imgur.com/516wNQo"><img src="https://i.imgur.com/516wNQo.png" title="source: imgur.com" /></a>

-Aqui podemos ver como el mensaje llega al servidor y David lo recibe en el caso opuesto es lo mismo:

<a href="https://imgur.com/6LGilo0"><img src="https://i.imgur.com/6LGilo0.png" title="source: imgur.com" /></a>

<a href="https://imgur.com/usEARhe"><img src="https://i.imgur.com/usEARhe.png" title="source: imgur.com" /></a>


## Resultados 📦

Aplicacion Servidor-Cliente que permite al cliente enviar mensajes al servidor, el mensaje del cliente tiene la ip y su estado de a que cliente se lo va a enviar. el servidor 
recibe e imprime el mensaje en el servidor y ambos clientes pasando toda la información (Ip, Nick, Estado, Mensaje).


## Construido con 🛠️

* [Eclipse](https://www.eclipse.org/downloads/) - Entorno de desarrollo
* [WindowBuilder](https://www.eclipse.org/windowbuilder/download.php) - Extension con la que se ha desarrollado el entorno gráfico de la app
* Sockets


## Creado por ✒️

* **Pedro Vicente** - *Desarrollador* - [PinguExtremo](https://github.com/PinguExtremo)
* **Alejandro Casado** - *Desarrollador* - [janovm](https://github.com/janovm)
