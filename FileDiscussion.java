import java.lang.Runnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutionException;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class FileDiscussion implements Callable<ConcurrentMap<Message, Integer>>{
    private static int cptC = 0; 
    private ConcurrentMap<Integer, Message> listMessage;
    private ConcurrentMap<Integer, Comment> listComment;
    private ExecutorService poolMessage;
    private ExecutorService poolComment;
    private static final Object mutex2 = new Object();
    Message message;
    int numM, numC, nbC;
    int impValue = 0;
    int nc = 0;
    private ConcurrentMap<Integer, Comment> tabCommentParMessage;
    private final Object reveil = new Object();
    private ConcurrentMap<Message, Integer> tabValeurImp;
    private List<String[]> fileDesComments;
    private String fichier;


    public FileDiscussion(int num, Message message, ExecutorService excEs, ConcurrentMap<Message, Integer> tabValeurImp) {
        this.fichier =  "reseauSocialTest.txt"; 
        poolMessage = excEs;
        this.message = message;
        numM = num;
        tabCommentParMessage = new ConcurrentHashMap<Integer, Comment>();
        listComment = new ConcurrentHashMap<Integer, Comment>();
        //fileDesComments = fil;
       poolComment = Executors.newFixedThreadPool(10);
       this.tabValeurImp = tabValeurImp;

    }

    public void requeteTraitee(){
        synchronized (reveil){
            reveil.notifyAll();
        }
    }

    private void trace(String m){
        System.out.println(m);
        Thread.yield(); //On rend la commutation possible
    }

    

    public ConcurrentMap<Message, Integer> call() {
        
        //for (;;) {

            //while (!poolMessage.isTerminated()) {
                //this.fileCommentaire();
                //parcoursComment();
                //Slave.ensMessages.forEach(action);
                //while (!Slave.ensMessages.isEmpty()){
                Slave.ensMessages.forEach((idM, msge) -> {
                    while(!msge.isInactif()){
                    try {

                        while (msge.getScore() == 0) {
                            //poolMessage.isTerminated();
                            msge.setInactif(true); // On rend le message inactif
                            //tabValeurImp.put();
                        }
                       
                        impValue += msge.getScore();
                        if (!Slave.ensComments.isEmpty()) {
                            Slave.ensComments.forEach((id, comRoot) -> {
                                if (comRoot.getPidMessage() == msge.getIdMessage()) {
                                    if (!tabCommentParMessage.containsValue(comRoot)){
                                        tabCommentParMessage.putIfAbsent(id, comRoot);
                                        nc++;
                                        this.trace("\t **** Message " + idM + " : " + nc + " commentaire ****");
                                        this.trace("\t \t **** ID Commentaire : " + comRoot.getIdComment()+" ****");
                                    }
                                    
                                    Slave.ensComments.forEach((id1, comSec) -> {
                                        if (comSec.getIdComment() == comRoot.getPidComment()) {
                                            //if (message.getInactif()==false)
                                            //    impValue += comSec.getScore();
                                            //nc++;
                                            if (!tabCommentParMessage.containsValue(comSec)){
                                                tabCommentParMessage.putIfAbsent(id1, comSec);
                                                nc++;
                                                this.trace("\t **** Message " + idM + " : " + nc + " commentaire ****");
                                                this.trace("\t \t **** ID Commentaire : " + comSec.getIdComment()+" ****");
                                            }
                                        }
                                    });
                                }
                            });
                        } else {
                            this.trace("\t **** Message " + idM + " Pas de commentaire ****");
                        }
    
                        tabCommentParMessage.forEach((ncom, cmt) -> {
                            //if (message.getInactif()==false)
                                impValue += cmt.getScore();
                        });
    
                        this.trace("\t **** Valeur importante Message " + idM + " = " + impValue + " ****");
                        
                        if (msge.getScore()==1){
                            //while (!tabValeurImp.containsKey(msge))
                                tabValeurImp.put(msge, impValue);

                                //while (tabValeurImp.containsKey(msge))
                                //    tabValeurImp.remove(msge);
                                
                                //    tabValeurImp.put(msge, impValue);
                            //System.out.println("[ "+msge.getIdMessage()+" , "+impValue+" ]");
                        }
                        // });
                      // }
                       
                        // }else{
                        Thread.sleep(30000);
                        // }
    
                    } catch (Exception e) {
                        e.printStackTrace();
                        
                    }
                }

                    

                });

                

               return tabValeurImp;
                
            //}

            
            
            

                
            //}

       // }

    }

    /*public void parcoursComment(){
       
        this.fileDesComments.forEach((ligne)-> {

            int min = 1; int max=3;
            int n = new Random().nextInt((max - min) + 1) + min;

            try {
                Thread.sleep(n*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                        
            synchronized (mutex2){
                nbC=cptC++;
            }

            int idComment = Integer.parseInt(ligne[0]);
            int idUser = Integer.parseInt(ligne[1]);
            String comment = ligne[2];
            String nomUser = ligne[3];
            int pidComment = ligne[4].isEmpty() ? 0 : Integer.parseInt(ligne[4]);
            int pidMessage = ligne[5].isEmpty() ? 0 : Integer.parseInt(ligne[5]);

            Slave.trace(" - Creation du commentaire "+cptC+" après "+n+" secondes");
                    
            Comment commentaire = new Comment(idComment, idUser, comment, nomUser, pidComment, pidMessage, this);

            listComment.putIfAbsent(cptC, commentaire);
            poolComment.execute(new ProducteurComment(cptC, commentaire, listComment));

                        
            try {
                synchronized(reveil){
                    //Etant en attente de réponse du serveur,
                    //On appelle la commande wait()
                    reveil.wait();
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            }
                   
        });
    }*/

  


}