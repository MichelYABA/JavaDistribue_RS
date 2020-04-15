import java.util.concurrent.CopyOnWriteArrayList;
import java.io.File;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.concurrent.Future;
import java.io.BufferedReader;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.LinkedHashMap;
import static java.util.stream.Collectors.*;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Slave implements Runnable{
    private String fichier;
    private final Socket socket;
    private final Serveur serveur;
    private List<String> contentFile;
    private CopyOnWriteArrayList<Message> messages;
    private CopyOnWriteArrayList<Comment> comments;
    private List<Message> listMessages;
    private List<Comment> listComments;
    private final Object reveil = new Object();
    private  CopyOnWriteArrayList<Integer> listVI;
    private static final Object mutex1 = new Object();
    private static final Object mutex2 = new Object();
    private int nbM, nbC;
    private static int cptM = 0;
    private static int cptC = 0;
    private ExecutorService execMessage;
    private ExecutorService execComment;
    private ThreadGroup groupeMessage = new ThreadGroup("Messages");
    private ThreadGroup groupeComment = new ThreadGroup("Comments");
    private  UsineDeThread usineMessage;
    private  UsineDeThread usineComment;
    private  CopyOnWriteArrayList<Future<Integer>> listFutureVI;
    int importantValue = 0;
    ExecutorService execScoreMsg; 
    ExecutorService execScoreCom; 
    ExecutorService execScore; 
    ArrayBlockingQueue<Integer> tabScoreMessage;
    ArrayBlockingQueue<Integer> tabScoreComment;
    private final int CAPACITY = 20;
    static  ConcurrentMap<Integer, Message> ensMessages;
    static  ConcurrentMap<Integer, Comment> ensComments;
    private Message message;
    private Comment comment;
    CopyOnWriteArrayList<Integer> mesScores;
    CompletionService<Integer> completionService ;
    List<Future<ConcurrentMap<Message, Integer>>>  listeVIMessage = new ArrayList<>();
    int totalCom=0;
    private ExecutorService execParal ;
    private List<String[]> fileDesMessages;
    private List<String[]> fileDesComments;
    private ConcurrentMap<Message, Integer> tabValeurImp;
    String requette="";
    public final static int PORT = 4000;
    int nombreDeMessage;
    //String[] ligneSplit;




    //public static List<String[]> fileDesComments;

    /*Slave(){
        this.socket = new Socket();
        this.serveur = new Serveur();
        this.fichier =  "reseauSocialTest.txt"; 
        this.messages = new CopyOnWriteArrayList<Message>();
        this.comments = new CopyOnWriteArrayList<Comment>();
    }*/

    Slave(Socket socket, Serveur serveur){
        
        execScoreMsg = Executors.newFixedThreadPool(4);
        execScoreCom = Executors.newFixedThreadPool(4);
        execScore = Executors.newFixedThreadPool(10);
        //Executors.newSingleThreadScheduledExecutor();
        this.socket = socket;
        this.serveur = serveur;
        fichier =  "reseauSocialTest.txt"; 
        this.messages = new CopyOnWriteArrayList<Message>();
        this.comments = new CopyOnWriteArrayList<Comment>();

        this.usineMessage = new UsineDeThread(groupeMessage);
        

        this.usineComment = new UsineDeThread(groupeComment);

        //this.listFutureVI = fut;
        listVI = new CopyOnWriteArrayList<Integer>();
        
        listComments = new ArrayList<Comment>();
        listMessages = new ArrayList<Message>();

        ensComments = new ConcurrentHashMap<Integer, Comment>();
        ensMessages = new ConcurrentHashMap<Integer, Message>();
        //synchronized (mutex){
        //    this.monId = cpt++;
        //}
        //completionService = new ExecutorCompletionService<Integer>(execMessage);
        //this.execParal = Executors.newSingleThreadExecutor();

        tabValeurImp = new ConcurrentHashMap<Message, Integer>();
       
        this.execComment = Executors.newSingleThreadExecutor();

        this.execMessage = Executors.newFixedThreadPool(200);

        this.execParal = Executors.newFixedThreadPool(200); 

    }

    public static void trace(String msg){
        System.out.println("Esclave "+msg);
        Thread.yield();
    }

    private void traceMessage(int msg){
        System.out.println("\t Identifiant : "+msg);
        Thread.yield();
    }

    private void traceComment(int com){
        System.out.println("\t Identifiant : "+com);
        Thread.yield();
    }

    public void requeteTraitee(){
        synchronized (reveil){
            reveil.notifyAll();
        }
    }

    private void traceConsommateur(int com){
        System.out.println("\t Consommateur : "+com);
        Thread.yield();
    }

    /*public String toString(){
        return "Esclave "+ monId;
    }*/

    public void displayMessage(CopyOnWriteArrayList<Message> liste) {
        
        liste.forEach( (l) -> {
            System.out.println(l.getIdMessage());
        });
        		
    }

    public void displayComment(CopyOnWriteArrayList<Comment> liste) {
        
        liste.forEach( (l) -> {
            System.out.println(l.getIdComment());
        });
        		
    }

    /*public void requeteTraitee(){
        synchronized (reveil){
            reveil.notifyAll();
        }
    }*/

    public void run() {
        Slave.trace("Initialisé");

        contentFile = new ArrayList<String>();

       
        //fileDesMessages = new ArrayList<String[]>();
        //fileDesComments = new ArrayList<String[]>();
        int nblig;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fichier))) {
        	
            contentFile = br.lines().collect(Collectors.toList());

            contentFile.forEach((ligne)-> {
                //int a=0;
                int min = 1; int max=3;
                int n = new Random().nextInt((max - min) + 1) + min;

                try {
                    Thread.sleep(n*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String[] ligneSplit = ligne.split("[|]", -1);

                if (ligneSplit[4].isEmpty() && ligneSplit[5].isEmpty())
                    this.createAndExecuteMessages(ligneSplit, n);
                else
                    this.createAndExecuteComments(ligneSplit, n);

            });

        }catch (IOException e) {
            e.printStackTrace();

        }

        //nblig=contentFile.size();

        //int nbMessage = fileDesMessages.size();
        //int nbCom = fileDesComments.size();

        

        //fileCommentaire();
            
        


      
        //execComment.shutdown();
        ConcurrentMap<Message, Integer> VI = new ConcurrentHashMap<Message, Integer>();
        Map<Message, Integer> VIv2 = new HashMap<>();
        Iterator<Future<ConcurrentMap<Message, Integer>>> it = listeVIMessage.iterator();
        while(it.hasNext()){
           try {
              it.next().get().forEach((sms, valImp) -> {
                if (VIv2.containsKey(sms))
                    VIv2.remove(sms);
                VIv2.put(sms, valImp);
            });
           } catch (InterruptedException | ExecutionException eee) {
              eee.printStackTrace();
           }
        }
        
    
        Map<Message, Integer> sortedByValue = VIv2.entrySet()
                                                    .stream()
                                                    .sorted(Map.Entry.<Message, Integer> comparingByValue().reversed())
                                                    .limit(3)
                                                    .collect(
                                                        toMap(Map.Entry::getKey, 
                                                        Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        /*Set< Map.Entry< String,Integer> > st = hm.entrySet();
        for (Map.Entry< String,Integer> me:st) 
       { 
           System.out.print(me.getKey()+":"); 
           System.out.println(me.getValue()); 
       } */
        //System.out.println("\t ######  "+ sortedByValue+" ######");
        sortedByValue.forEach((s, v) -> {
        
            requette+=s.getIdMessage()+"|"+s.getIdUser()+"|";
            //System.out.println("\t ##### [ "+s.getIdMessage()+" , "+v+" ] #####");
        });

        System.out.println("\t ##### "+requette+" #####");


        try{
            OutputStream os = this.socket.getOutputStream();
                
            // Le serveur envoie la réponse au client
            System.out.println("J'envoie la réponse au client");
            //os.write(rep);
            //On envoie une chaine de caractere
            PrintWriter pw = new PrintWriter(os, true);
            pw.println(requette);

            execParal.shutdown();
            execMessage.shutdown();
            execComment.shutdown();
            this.socket.close(); //On ferme le socket
        
        }catch(IOException ef) {
            ef.printStackTrace();
        }
		

        

    }

    public List<String[]> fileMessage(List<String> fileM){
        List<String[]> tabLigneSplit = new  ArrayList<String[]>();
        fileM.forEach((ligne)-> {
           
            String[] ligneSplit = ligne.split("[|]", -1);
            if (ligneSplit[4].isEmpty() && ligneSplit[5].isEmpty()) {
                tabLigneSplit.add(ligneSplit);
            }

        });
        return tabLigneSplit;
    }

    public List<String[]> fileComment(List<String> fileC){
        List<String[]> tabLigneSplit = new  ArrayList<String[]>();
        fileC.forEach((ligne)-> {
           
            String[] ligneSplit = ligne.split("[|]", -1);
            if (!ligneSplit[4].isEmpty() || !ligneSplit[5].isEmpty()) {
                tabLigneSplit.add(ligneSplit);
            }

        });
        return tabLigneSplit;
    }

    public void createAndExecuteComments(String[] ligne, int n){
       
        /*fileDesComments.forEach((ligne)-> {

            int min = 1; int max=3;
            int n = new Random().nextInt((max - min) + 1) + min;

            try {
                Thread.sleep(n*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
                        
            synchronized (mutex2){
                nbC=cptC++;
            }

            int idComment = Integer.parseInt(ligne[0]);
            int idUser = Integer.parseInt(ligne[1]);
            String comment = ligne[2];
            String nomUser = ligne[3];
            int pidComment = ligne[4].isEmpty() ? 0 : Integer.parseInt(ligne[4]);
            int pidMessage = ligne[5].isEmpty() ? 0 : Integer.parseInt(ligne[5]);

            trace(" - Creation du commentaire "+cptC+" après "+n+" secondes");
                    
            Comment commentaire = new Comment(idComment, idUser, comment, nomUser, pidComment, pidMessage, this);

            ensComments.putIfAbsent(cptC, commentaire);
            execComment.execute(new ProducteurComment(cptC, commentaire));

                        
            try {
                synchronized(reveil){
                    //Etant en attente de réponse du serveur,
                    //On appelle la commande wait()
                    reveil.wait();
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            }
                   
       // });
    }

   public void createAndExecuteMessages(String[] ligne, int n){
    /*fileDesMessages.forEach((ligne)-> {
        int a=0;
        int min = 1; int max=3;
        int n = new Random().nextInt((max - min) + 1) + min;

        try {
            Thread.sleep(n*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        synchronized (mutex1){
            this.nbM=cptM++;
        }      
            
        int idMessage = Integer.parseInt(ligne[0]);
        int idUser = Integer.parseInt(ligne[1]);
        String msge = ligne[2];
        String nomUser = ligne[3];
                    
        Slave.trace(" - Creation du message "+cptM+" après "+n+" secondes");

        message = new Message(idMessage, idUser, msge, nomUser, this);
        ensMessages.putIfAbsent(cptM, message); 
        execMessage.execute(new ProducteurMessage(cptM, message));
        Future<ConcurrentMap<Message, Integer>> VIMessage = execParal.submit(new FileDiscussion(cptM, message, execMessage, tabValeurImp));

        listeVIMessage.add(VIMessage);

        try {
            synchronized(reveil){
                //Etant en attente de réponse du serveur,
                //On appelle la commande wait()
                reveil.wait();
            }
        } catch (InterruptedException e) {
            System.err.println(e);
        }
              
                  
    //});
   }

}