import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Map;


public class Serveur {
    private final static int PORT = 4000;
    private final static int SIZE = 1;
    //private String fichier;
    private Boolean isFinished = false;
    private ServerSocket serverSocket;
    private ExecutorService execSlave ;
    private ThreadGroup groupeSlaves = new ThreadGroup("Esclaves");
    private  UsineDeThread monUsine;
    private CopyOnWriteArrayList<Future<Integer>> lesResultats = new CopyOnWriteArrayList<Future<Integer>>(); 
    //private  ConcurrentMap<Integer, Message> listMessage;

    Serveur(){}

    Serveur(int port, int size){
        try {
            this.serverSocket = new ServerSocket(port, size);
            //this.fichier = fich;
            this.monUsine = new UsineDeThread(groupeSlaves);
            this.execSlave = Executors.newSingleThreadExecutor(monUsine);  
        } catch (IOException e) {
            Logger.getLogger(Serveur.class.
                    getName()).log(Level.SEVERE, null, e);
        }

    }

    public Boolean getIsFinished(){
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished){
        this.isFinished = isFinished;
    }

    private void trace(String msg){
        System.out.println("Serveur :"+msg);
        Thread.yield();
    }

    public void manageRequests(){
        this.trace("Initialisé");
        try{
            execSlave.execute(new Slave(serverSocket.accept(),this));
            //Thread.sleep
        }
       catch (IOException e) {System.out.println(e);
        }
         finally {
            execSlave.shutdown();
            this.trace("- Serveur : Adieu !!!");
          }

        groupeSlaves.list();

       

        /*for (Future<Integer> futReq : lesResultats){
            try{
                if (futReq.get().getClient() == this){
                    this.trace("Sa valeur est "+futReq.get().getValeur());
                    listFuture.remove(futReq);//on nettoie la valeur récupérée
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }*/

       
        //listFuture.clear(); 
        //On nettoie ces résultats consommés
        //if (isFinished == true){
            //execSlave.shutdown();
        //}
       
    }

    public static void main(String[] args){
        
        Serveur serveur = new Serveur(PORT, SIZE);
        serveur.manageRequests();

       // CopyOnWriteArrayList<Future<Message>> fut = new CopyOnWriteArrayList<Future<Message>>();
       //Slave esclave =  new Slave();
       //Thread t = new Thread(esclave);
       //t.start();
   }



}