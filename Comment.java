
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;

class Comment{
    private ZonedDateTime dateComment; //date du commentaire
    private int idComment; //identifiant du commentaire
    private int idUser; //identifiant de l'utilisateur
    private String comment; //Contenu du commentaire
    private String user; //nom de l'utilisateur
    private int pidCommentaire; //identifiant du commentaire que ce commentaire commente
    private int pidMessage; //identifiant du message que ce commentaire commente
    private int score;
    Slave esclave;

    /* Constructeur */
    Comment(){
        ZoneId zid = ZoneId.of("Europe/Paris");
        this.dateComment = ZonedDateTime.now(zid);
        this.idComment = 0;
        this.idUser = 0;
        this.comment = null;
        this.user = null;
        this.pidCommentaire = 0;
        this.pidMessage = 0;
        this.score = 20;
    }

    Comment(int idComment, int idUser, String comment, String user, int pidC, int pidM, Slave es){
        ZoneId zid = ZoneId.of("Europe/Paris");
        this.dateComment = ZonedDateTime.now(zid);
        this.idComment = idComment;
        this.idUser = idUser;
        this.comment = comment;
        this.user = user;
        this.pidCommentaire = pidC;
        //this.pidCommentaire = (pidM != 0) ? pidC : -1;
        //this.pidMessage = (pidC != 0) ? pidM : -1;
        this.pidMessage = pidM;
        this.score = 5;
        esclave = es;
        //diminuerScore();
    }

    /* Getters */
    public ZonedDateTime getDateComment(){
        return this.dateComment;
    }

    public int getIdComment(){
        return this.idComment;
    }

    public int getIdUser(){
        return this.idUser;
    }

    public String getComment(){
        return this.comment;
    }

    public String getUser(){
        return this.user;
    }

    public int getPidComment(){
        return this.pidCommentaire;
    }

    public int getPidMessage(){
        return this.pidMessage;
    }

     public int getScore(){
        return this.score;
    }

    /* Setters */
    public void setIdComment(int idC){
        this.idComment = idC;
    }

    public void setIdUser(int idU){
        this.idUser = idU;
    }

    public void setComment(String com){
        this.comment = com;
    }

    public void setUser(String us){
        this.user = us;
    }

    public void setPidComment(int pidC){
        this.pidCommentaire = pidC;
    }

    public void setPidMessage(int pidM){
        this.pidMessage = pidM;
    }

    public void setScore(int s){
        this.score = s;
    }

    public boolean isInactif(){
        return score==0;
    }

    /* Utilitaires */
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
    }

    public boolean estUnSousCommentaire(){
        return this.pidMessage == -1;
    }

    public boolean estUnCommentaire(){
        return this.pidCommentaire==-1;
    }


}