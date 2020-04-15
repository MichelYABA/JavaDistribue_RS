import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;

class Message{
    private ZonedDateTime dateMessage; //date du message
    private int idMessage; //identifiant du message
    private int idUser; //identifiant de l'utilisateur
    private String message; //Contenu du message
    private String user; //nom de l'utilisateur
    private int score; //score du message
    Slave esclave;
    boolean decompte;
    private boolean actif;

    /* Constructeur */
    Message(){
        ZoneId zid = ZoneId.of("Europe/Paris");
        this.dateMessage = ZonedDateTime.now(zid);
        this.idMessage = 0;
        this.idUser = 0;
        this.message = null;
        this.user = null;
        this.score = 5;
    }

    Message(int idMessage, int idUser, String message, String user, Slave es){
        ZoneId zid = ZoneId.of("Europe/Paris");
        this.dateMessage = ZonedDateTime.now(zid);
        this.idMessage = idMessage;
        this.idUser = idUser;
        this.message = message;
        this.user = user;
        this.score = 5;
        esclave = es;
        decompte = false;
        actif = true;
    }

    /* Getters */
    public ZonedDateTime getDateMessage(){
        return this.dateMessage;
    }

    public int getIdMessage(){
        return this.idMessage;
    }

    public int getIdUser(){
        return this.idUser;
    }

    public String getMessage(){
        return this.message;
    }

    public String getUser(){
        return this.user;
    }

    public int getScore(){
        return this.score;
    }

    /* Setters */
    public void setIdMessage(int idM){
        this.idMessage = idM;
    }

    public void setIdUser(int idU){
        this.idUser = idU;
    }

    public void setMessage(String msg){
        this.message = msg;
    }

    public void setUser(String us){
        this.user = us;
    }

    public void setScore(int s){
        this.score = s;
    }

    /* Utilitaires */

    public boolean isInactif(){
        return score==0;
    }

    public void setInactif(Boolean val){
         actif = val;
    }

    public boolean getInactif(){
        return actif;
   }

    public void diminuerScore(){

        /*TimerTask tache = new TimerTask() {
            public void run() {
                --score;
                
                if (score ==0) {
                    cancel();
                    //inactif = true;
                    //System.out.println("Terminé");
                }
            }
        };
        
        new Timer().schedule(tache, 0, 1000);*/
        this.score--;
       // this.decompte = true;
    }

     public boolean pasDecompte(){
        return !decompte;
    }

     public boolean estDecompte(){
        return decompte;
    }
}