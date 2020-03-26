import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Server{
	private File fichier;
	//private int score;
	private static int port;
    private static int poolSize;
    private boolean isFinished;
    protected static Server scoreMsge;
    //private static DecompteScoreMessage scoreMsge; = new DecompteScoreMessage();
    
    private final ExecutorService pool;
    private final ServerSocket serverSocket;
    
    public Server(int port, int poolSize, File f) throws IOException{
 
    	this.fichier=f;
        serverSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(poolSize);
        
    }
    
    public static void decompteScoreComment() {
    	Timer timer = new Timer(); 
        TimerTask task = new Comment(); 
        long periode = 30*1000;
        timer.schedule(task, 1000, periode); 
    }
    
    public static void decompteScoreMessage() {
    	Timer timer = new Timer(); 
        TimerTask task = new Message(); 
        long periode = 30*1000;
        timer.schedule(task, 1000, periode); 
    }
    
    /*public static void decompteScoreMessage(Server s) throws InterruptedException {
    	scoreMsge = s;
    	//creating a new instance of timer class 
        Timer timer = new Timer(); 
        TimerTask task = new Message(); 
        

        //instance of date object for fixed-rate execution 
        Date date = new Date(); 
        
        // On décrémente de 1 le score toutes les 30 s
        long periode = 30 * 1000;
        timer.scheduleAtFixedRate(task, date, periode); 
        
        //System.out.println("Timer running"); 
        
        synchronized(scoreMsge) 
        { 
        	scoreMsge.wait(); 
         
            timer.cancel(); 
            
            timer.purge();
              
            //System.out.println(timer.purge()); 
        } 
    }*/
    
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
            	//score=20;
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

	public static void main(String[] args) throws Exception {
		 File file = new File("/home/yaba/Documents/M1InfoU13/s2/PDJ/DEMJava/src/reseauSocial.txt"); 
		
		 try {
	            Server server = new Server(3333,10, file);
	            //server.decompteScoreComment(server);
	            Server.decompteScoreMessage();
	            Server.decompteScoreComment();
	            
	            server.manageRequest();
	        } catch (IOException e) {
	            System.out.println(e);
	        }

	}

}

class DecompteScoreMessage{
    protected static DecompteScoreMessage planif;
    
    public static void manageScore() throws InterruptedException 
    {
    	planif = new DecompteScoreMessage();
    	//creating a new instance of timer class 
        Timer timer = new Timer(); 
        TimerTask task = new Message(); 
        

        //instance of date object for fixed-rate execution 
        Date date = new Date(); 
        
        // On décrémente de 1 le score toutes les 30 s
        long periode = 30 * 1000;
        timer.scheduleAtFixedRate(task, date, periode); 
        
        System.out.println("Timer running"); 
        
        synchronized(planif) 
        { 
            //make the main thread wait 
        	planif.wait(); 
              
            //once timer has scheduled the task 4 times,  
            //main thread resumes 
            //and terminates the timer 
            timer.cancel(); 
              
            //purge is used to remove all cancelled  
            //tasks from the timer'stak queue 
            System.out.println(timer.purge()); 
        } 
    }

}

class DecompteScoreComment{
    protected static DecompteScoreComment planif;
    
    public static void manageScore() throws InterruptedException 
    {
    	planif = new DecompteScoreComment();
    	//creating a new instance of timer class 
        Timer timer = new Timer(); 
        TimerTask task = new Comment(); 

        //instance of date object for fixed-rate execution 
        Date date = new Date(); 
        
        // On décrémente de 1 le score toutes les 30 s
        long periode = 30 * 1000;
        timer.scheduleAtFixedRate(task, date, periode); 
        
        System.out.println("Timer running"); 
        
        synchronized(planif) 
        { 
            //make the main thread wait 
        	planif.wait(); 
              
            //once timer has scheduled the task 4 times,  
            //main thread resumes 
            //and terminates the timer 
            timer.cancel(); 
              
            //purge is used to remove all cancelled  
            //tasks from the timer'stak queue 
            System.out.println(timer.purge()); 
        } 
    }
}
