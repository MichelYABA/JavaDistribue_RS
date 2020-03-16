
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
    
    Slave(Socket s, File f){
        this.socket = s;
        
    }

    @Override
    public void run() {
    	
         
    }
}
   
