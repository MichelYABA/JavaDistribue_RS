package up13.handler;

	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;




	public class Slave implements Runnable {
		private  Socket socket;
		private  Server server;
		Slave(Socket socket ) { this.socket = socket; }
		public void run() {
				   
			   try{
				   System.getProperties().put("java.protocol.handler.pkgs", "up13");
				   URL url = new URL("handler://xy.xy@xy.fr");					

					JDContent t = (JDContent) url.getContent();
					System.out.println("retrieved file : " + t.getFileName());
					System.out.println("file content : " + t.getFile());
				}catch (MalformedURLException ex){ System.out.println(ex); error("Bad URL");
				}catch (IOException ex){ System.out.println(ex); error("IOException occurred.");}
		}
				public static void error(String s){
					System.out.println(s); System.exit(1);
				}}
				
			

			    

		   
	                          
	         
		     
		