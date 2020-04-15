import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ArrayBlockingQueue;

class ScoreMessage implements Runnable{
    private  int numM;
    private boolean test;
    private Message message;
    private ArrayBlockingQueue<Integer> tabScore;  

    ScoreMessage(int cpt, Message msg, ArrayBlockingQueue<Integer> tab) {
        this.message = msg;
        this.tabScore = tab;
        test = true; 
        numM=cpt;
    }

    public void run() {
            //this.trace("Je dis " + message);
            while (!message.isInactif()){
               
    
                try {
                    message.diminuerScore();
                    tabScore.put(message.getScore());
                    this.trace("\t - Le score du message "+ numM+ " passe à "+message.getScore());
                    //this.trace(" \t - Le score du message passe à : "+tabScore.take());
                       
                    Thread.sleep(1000);
                   // }
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                message.esclave.requeteTraitee();
                //if (message.getScore()==0)
                //    test = false;
            }
           
        
        /*this.trace("Decrementation score terminé");
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }*/
        this.trace("\t Decompte du message "+numM+" terminé");
    }

    /*private void terminaison(String m){
        System.out.println("Tache "+monNumero+" : "+m);
    }*/

    private void trace(String s) {
        System.out.println(s);
    }
}