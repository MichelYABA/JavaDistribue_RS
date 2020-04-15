import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ProducteurComment implements Runnable{
    private int indiceComment;
    private Comment comment;
    static CopyOnWriteArrayList<Integer> lesScores;
   // private final ConcurrentMap<Integer, Comment> listComments;
    ExecutorService execScore; 

    
    ProducteurComment(int indC, Comment comment)
    {
        this.comment = comment;
        this.indiceComment = indC;
        //lesScores = scores;
        //listComments = comments;
        execScore = Executors.newSingleThreadExecutor();

    }

    private void trace(String m){
        System.out.println("\t Comment "+indiceComment +" : "+m);
        Thread.yield(); //On rend la commutation possible
    }

    public void run (){
        this.trace("Debut décompte");
        while(!comment.isInactif()){
            
           comment.diminuerScore();
           //lesScores.add(message.getScore());
           this.trace("\t - Le score du commentaire "+ indiceComment+ " passe à "+comment.getScore());

           comment.esclave.requeteTraitee();
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