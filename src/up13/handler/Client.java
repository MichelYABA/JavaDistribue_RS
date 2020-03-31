package up13.handler;

import java.io.*; 
	import java.net.InetAddress;
	import java.net.Socket;

	import javax.print.attribute.standard.OutputDeviceAssigned;


	public class Client {


			public static void main(String[] args) {
			
			try {
				Socket connexion = new Socket(InetAddress.getLocalHost(),33333);
				Writer out = new OutputStreamWriter(connexion.getOutputStream(), "8859_1");
			  out.write("22 3456 ");
			  System.out.println("ok2");
			  out.flush();

			   connexion.shutdownOutput();	
			 
			} catch (IOException e) {System.out.println(e);}
			}}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//ouvrir connaxion
		//envoyer req
		//attendre reponse
		//afficher reponse




