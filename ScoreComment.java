import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ArrayBlockingQueue;


class ScoreComment implements Runnable{
    private  int numC;
    private boolean test;
    private Comment comment;
    private ArrayBlockingQueue<Integer> tabScore;

    ScoreComment(int cpt, Comment com, ArrayBlockingQueue<Integer> tab) {
        this.comment = com;
        this.tabScore = tab;
        test = true; 
        numC = cpt;
    }

    public void run() {
       
            //this.trace("Je dis " + message);
            while (!comment.isInactif()){
                
    
                try {
                    comment.diminuerScore();
                    tabScore.put(comment.getScore());
                    //this.trace(" \t - Le score du commentaire passe à : "+tabScore.take());
                    this.trace("\t - Le score du commentaire "+ numC+ " passe à "+comment.getScore());
                       
                        Thread.sleep(1000);
                   // }
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                comment.esclave.requeteTraitee();
                //if (message.getScore()==0)
                //    test = false;
            }
           
        
        /*this.trace("Decrementation score terminé");
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }*/
        this.trace("\t Decompte du commentaire "+numC+" terminé");
    }

    /*private void terminaison(String m){
        System.out.println("Tache "+monNumero+" : "+m);
    }*/

    private void trace(String s) {
        System.out.println(s);
    }
}