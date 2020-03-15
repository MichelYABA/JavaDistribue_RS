
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;


public class Slave implements Runnable{
    private  final Socket socket;
    
    Slave(Socket s){
        this.socket = s;
        
    }

    @Override
    public void run() {
    	InputStream is;
        try {
        	/*Preparation des entrees*/
        	is = socket.getInputStream();
        	InputStreamReader isr = new InputStreamReader(is);
            BufferedReader input = new BufferedReader(isr);
            
            //Preparation des sorties
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            
            for(;;){
				String req;
				while((req=input.readLine())!=null)
					//String rep = ;
					pw.println("Size = "+req.length());
			}
				
           
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
   
