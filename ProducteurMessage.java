import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProducteurMessage implements Runnable{
    private int indiceMessage;
    private Message message;
    static CopyOnWriteArrayList<Integer> lesScores;
    //private final ConcurrentMap<Integer, Message> messages;
    ExecutorService execScore; 

    
    ProducteurMessage(int indM, Message msg)
    {
        this.message = msg;
        this.indiceMessage = indM;
        //lesScores = scores;
        //messages = msges;
        execScore = Executors.newSingleThreadExecutor();

    }

    private void trace(String m){
        System.out.println("\t Message "+indiceMessage +" : "+m);
        Thread.yield(); //On rend la commutation possible
    }

    public void run (){
        this.trace("Debut décompte");
        while(!message.isInactif()){
            
           message.diminuerScore();
           //lesScores.add(message.getScore());
           this.trace(" - Le score du message "+ indiceMessage+ " passe à "+message.getScore());

           //messages.forEach((id, mes) -> {
            //Runnable excS = () -> {
            //    System.out.println("**** MAJ **** Message *** "+indiceMessage+" Score : "+message.getScore()*2);
            //};
            //execScore.execute(excS);
       // });

           //System.out.println("Message "+indiceMessage+" passe à : "+me)

           message.esclave.requeteTraitee();
           try {
               Thread.sleep(30000);
           } catch (Exception e) {
               e.printStackTrace();
              // system.err.println(e);
           }
                
        }
        this.trace("Décompte terminé");
    }

}