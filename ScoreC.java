import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Map;

public class ScoreC implements Callable<Integer>{
    ConcurrentMap<Integer, Comment> comments;
    Message message;
    Comment comment;
    int numComment;
    int nbCom=0;

    public ScoreC(Message mes, ConcurrentMap<Integer, Comment> comments){
        this.message = mes;
        /*this.comment = com;
        this.numComment = numCom;*/
        this.comments = comments;
    }

    private void trace(String m){
        System.out.println(m);
        Thread.yield(); //On rend la commutation possible
    }

    public Integer call(){
        /*String nomThread = Thread.currentThread().setName("ThreadComment_"+numComment);
        System.out.println("Exécution dans le thread "+nomThread);*/

        comments.forEach((idC, comRoot)-> {
            if (comRoot.getPidMessage() == message.getIdMessage()){ 
                nbCom++;
                comments.forEach((id, comSec) -> {
                    if (comSec.getIdComment() == comRoot.getPidComment()){ 
                        nbCom++;
                        this.trace("\t \t ID Commentaire : " + comSec.getIdComment());

                    } 
                });
                this.trace("\t \t ID Commentaire : " + comRoot.getIdComment());
            }
        });

        return nbCom ;
    }
}