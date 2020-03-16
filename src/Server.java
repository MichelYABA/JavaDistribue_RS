import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
         try {
            for (;;){
            	score=20;
            	Socket s = serverSocket.accept();
                pool.execute(new Slave(s, fichier)); 
            }
        } catch (IOException ex) { 
            pool.shutdown(); 
        }    

    }
    
    public void isFinished(){
        if (isFinished)
            System.out.println("End");
    }

	public static void main(String[] args) {
		 File file = new File("/home/yaba/Documents/M1InfoU13/s2/PDJ/DEMJava/src/reseauSocial.txt"); 
		
		 try {
	            Server server = new Server(3333,10, file);
	            server.manageRequest();
	        } catch (IOException e) {
	            System.out.println(e);
	        }

	}

}
