import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private File fichier;
    private int score;
    private static int port;
    private static int poolSize;
    private boolean isFinished;
    
    
    private final ExecutorService pool;
    private final ServerSocket serverSocket;
    
    public Server(int port, int poolSize, File f) throws IOException{
    	this.fichier=f;
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);
    }
    
    public static int getPort(){
        return port;
    }
    
    public static int getPoolSize(){
        return poolSize;
    }
    
    public static void setPort(int p){
        port = p;
    }
    
    public static void setPoolSize(int ps){
        poolSize=ps;
    }
    
    public void manageRequest(){
        

    }
  

	public static void main(String[] args) {
		

	}

}
