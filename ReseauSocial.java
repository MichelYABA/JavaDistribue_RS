import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class ReseauSocial {

    public static void main(String[] args) throws IOException {
    try{
        Socket connexion = new Socket(InetAddress.getLocalHost(),Slave.PORT);

        InputStream is = connexion.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
       
        String rep;
		while((rep=br.readLine())!=null)
            System.out.println(rep);
        
        connexion.shutdownOutput();

    }catch (IOException e) {
        e.printStackTrace();
    }
    }

}