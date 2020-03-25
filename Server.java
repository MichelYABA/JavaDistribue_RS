import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
	
	private File fichier;
    private int score=20;
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
    
    public void readtext() {
    	Slave slave= new Slave(fichier, this);
    	
    	Thread t =new Thread(slave);
    	t.start();
    	
    	
    }
    
    
    


    
    
    

}
