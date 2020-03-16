
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


public class Slave implements Runnable{
    private  final Socket socket;
    private File fichier;
    
    Slave(Socket s, File fich){
        this.socket = s;
        this.fichier = fich;
    }

    @Override
    public void run() {
    	InputStream is;
        try {
        	/*Preparation des entrees*/
        	is = socket.getInputStream();
        	InputStreamReader isr = new InputStreamReader(is);
            BufferedReader input = new BufferedReader(isr);
            
  		  	BufferedReader br = new BufferedReader(new FileReader(fichier)); 
  		  	

  		  String st; 
  		  int i=0;
  		  try {
  			while ((st = br.readLine()) != null) {
  				++i;
  				for (i=1; i<11; i++)
  					System.out.println(i+" - "+st);
  			}
  		  }
  			catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} 
  		  	
  		  	
            //Preparation des sorties
            /*OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            
            for(;;){
				String req;
				while((req=input.readLine())!=null)
					//String rep = ;
					pw.println("Size = "+req.length());
			}*/
				
           
         }
        catch (SocketException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        try { 
            socket.close(); 
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
   
