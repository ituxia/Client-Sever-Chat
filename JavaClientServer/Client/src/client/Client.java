package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ObjectOutputStream ostream = null;
		Listen listen = null;
		Socket sock;
		try {
			sock = new Socket(InetAddress.getLocalHost(),14267);
			ostream = new ObjectOutputStream(sock.getOutputStream());
			listen = new Listen(sock);
			new Thread(listen).start();
		} catch (UnknownHostException e) {
    		System.out.println(e.getMessage());
		} catch (IOException e) {
    		System.out.println(e.getMessage());
		}

		try {
			System.out.println("Adding client to server");
			//How to add client to the server
			ostream.writeObject("add");
			ostream.flush();
			System.out.println("Please enter your alias");
			String alias = scan.next();
			ostream.writeObject(alias);
			ostream.flush();
			System.out.println("Client successfully added with alias = " + alias);
			String leave;
			do {
				System.out.println("\n\n\n");
				
				//How to message other client
				ostream.writeObject("message");
				ostream.flush();
				ostream.writeObject(alias);
				ostream.flush();
				System.out.println("Please enter alias of friend");
				String friendAlias = scan.nextLine();
				ostream.writeObject(friendAlias);
				ostream.flush();
				System.out.println("Please enter message");
				String message = scan.nextLine();
				ostream.writeObject(message);
				ostream.flush();
				
				System.out.println("\n\nDo you want to leave? (yes or no)");
				leave = scan.next();
			} while(!leave.equalsIgnoreCase("yes"));
			
			//How to leave server
			ostream.writeObject("Exit");
			ostream.flush();
			Thread.sleep(1000);
			ostream.close();
			listen.end();
		} catch (IOException e) {
    		System.out.println(e.getMessage());
		} catch (InterruptedException e) {
    		System.out.println(e.getMessage());
		}
	}

}
