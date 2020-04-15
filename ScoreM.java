import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.Map;

public class ScoreM implements Callable<Integer>{

    private int indiceMessage;
    private Message message;
    private final ConcurrentMap<Integer, Comment> comments;
    private int nbComment = 0;

    //private CopyOnWriteArrayList<Integer> lesScores;
    private int score;
    
    ScoreM(int indM, Message msg, ConcurrentMap<Integer, Comment> comments){
        this.message = msg;
        this.indiceMessage = indM;
        this.comments = comments;
    }

    private void trace(String m){
        System.out.println("\t Message "+indiceMessage +" : "+m);
        Thread.yield(); //On rend la commutation possible
    }


    public Integer call (){
        Thread.currentThread().setName("ThreadMessage_"+indiceMessage);
        String nomThread = Thread.currentThread().getName();
        System.out.println("Exécution dans le thread "+nomThread);

        //this.comments.forEach((idC, com) -> {
            ScoreC sc = new ScoreC(message, comments);
            FutureTask<Integer> fut = new FutureTask<>(sc);
            Thread t = new Thread(fut);
            t.start();

            try{
                nbComment +=fut.get();
    
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }catch (ExecutionException ee){
                ee.printStackTrace();
            }
    
        //});

       
        /*this.trace("Debut décompte");
        while(!message.isInactif()){
           message.diminuerScore();
           
           this.trace("\t - Le score du message "+ indiceMessage+ " passe à "+message.getScore());           

           message.esclave.requeteTraitee();
           try {
               Thread.sleep(1000);
           } catch (Exception e) {
               e.printStackTrace();
              // system.err.println(e);
           }
           //return message;
                
       }*/
        //this.trace("Décompte terminé");
        return nbComment;
    }

}