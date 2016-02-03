package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main (String [] args ) {	  
	  try {
		  System.out.println("Server started on port 14267");
		  //server started on port 14267
		  @SuppressWarnings("resource")
		  ServerSocket servsock = new ServerSocket(14267);
		  	while (true) {
			  	// server waiting for connections
			  	Socket sock = servsock.accept();
			  	new Thread(new Clients(sock)).start();
		  	}
	  } catch(Exception ex) {
		  System.out.println("Error "+ex.getMessage());
	  }
	}
}
