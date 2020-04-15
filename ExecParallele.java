import java.lang.Runnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;

public class ExecParallele implements Runnable {
    private ConcurrentMap<Integer, Message> listMessage;
    private ConcurrentMap<Integer, Comment> listcomment;
    private ExecutorService poolMessage;
    Message message;
    int numM, numC;
    int impValue = 0;
    int nc = 0;
    private ConcurrentMap<Integer, Comment> tabCommentParMessage;

    public ExecParallele(int num, Message message, ExecutorService excEs, ConcurrentMap<Integer, Comment> listeC) {
        poolMessage = excEs;
        this.message = message;
        numM = num;
        listcomment = listeC;
        tabCommentParMessage = new ConcurrentHashMap<Integer, Comment>();
    }

    /*
     * public ExecParallele(int numC, ConcurrentMap<Integer, Comment> listeC){
     * listcomment = listeC; this.numC = numC; }
     */

    private void trace(String m){
        System.out.println(m);
        Thread.yield(); //On rend la commutation possible
    }

    /*public void run(){
        while (!poolMessage.isTerminated()) {
            try{
                if (message.getScore() == 0) {
                    poolMessage.isTerminated();
                    message.setInactif(true); // On rend le message inactif
                }

            if (!listcomment.isEmpty()) {

                ScoreC sc = new ScoreC(this.message, this.listcomment);
                FutureTask<Integer> fut = new FutureTask<>(sc);
                Thread t = new Thread(fut);
                t.start();

                try{
                    nc +=fut.get();

                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }catch (ExecutionException ee){
                    ee.printStackTrace();
                }
             }else {
                this.trace("\t Message " + numM + " Pas de commentaire");
            }
                this.trace("\t **** Nombre de commentaire Message " + numM + " = " + nc + " ****");

                Thread.sleep(30000);
            } catch (Exception e) {
                e.printStackTrace();
                // System.err.println("Serveur, ne doit jamais arriver,"+e);
                // return;
            }
        }
    }*/

    public void run() {
        for (;;) {
            while (!poolMessage.isTerminated()) {
                try {
                    // if (listMessage != null){
                    // listMessage.forEach((id, m) -> {
                    // while ()
                    if (message.getScore() == 0) {
                        poolMessage.isTerminated();
                        message.setInactif(true); // On rend le message inactif
                    }
                    // System.out.println("**** MAJ Message "+numM+" - Score :
                    // "+message.getScore()*2+" ***");
                   //while (message.getInactif()==false){
                    //if (message.getInactif()==false)
                        impValue += message.getScore();
                    if (!listcomment.isEmpty()) {
                        listcomment.forEach((id, comRoot) -> {
                            if (comRoot.getPidMessage() == message.getIdMessage()) {
                                if (!tabCommentParMessage.containsKey(id)){
                                    tabCommentParMessage.putIfAbsent(id, comRoot);
                                    nc++;
                                    this.trace("\t **** Message " + numM + " : " + nc + " commentaire ****");
                                    this.trace("\t \t **** ID Commentaire : " + comRoot.getIdComment()+" ****");
                                }
                                
                                listcomment.forEach((id1, comSec) -> {
                                    if (comSec.getIdComment() == comRoot.getPidComment()) {
                                        //if (message.getInactif()==false)
                                        //    impValue += comSec.getScore();
                                        //nc++;
                                        if (!tabCommentParMessage.containsKey(id1)){
                                            tabCommentParMessage.putIfAbsent(id1, comSec);
                                            nc++;
                                            this.trace("\t **** Message " + numM + " : " + nc + " commentaire ****");
                                            this.trace("\t \t **** ID Commentaire : " + comSec.getIdComment()+" ****");
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        this.trace("\t **** Message " + numM + " Pas de commentaire ****");
                    }

                    tabCommentParMessage.forEach((ncom, cmt) -> {
                        //if (message.getInactif()==false)
                            impValue += cmt.getScore();
                    });

                    this.trace("\t **** Valeur importante Message " + numM + " = " + impValue + " ****");

                    // });
                  // }
                   
                    // }else{
                    Thread.sleep(30000);
                    // }

                } catch (Exception e) {
                    e.printStackTrace();
                    // System.err.println("Serveur, ne doit jamais arriver,"+e);
                    // return;
                }
            }

        }

    }

}