import java.util.TimerTask;

public class Comment  extends TimerTask{
	private static int score;
	private String dateC; //date du commentaire
	private int idCommentaire; //identifiant du commentaire
	private int idUser; //identifiant de l'utilisateur
	private String comment; //contenu du commentaire
	private String user; // nom de l'utilisateur
	private int pidCommentaire; //identifiant du commentaire que ce commentaire commente
	private int pidMessage; //identifiant du message que ce commentaire commente
	
	Comment(){
		score = 20;
	}
	
	Comment(int idC, int idU, String comment, String user, int pidC, int pidM){
		this.idCommentaire = idC;
		this.idUser = idU;
		this.comment = comment;
		this.user = user;
		this.pidCommentaire = pidC;
		this.pidMessage = pidM;
		score = 20;
	}
	
	public int getScore() {
		return score;
	}
	
	/*Getters*/
	public String getDateCommentaire() {
		return this.dateC;
	}
	
	public int getIdCommentaire() {
		return this.idCommentaire;
	}
	
	public int getIdUser() {
		return this.idUser;
	}
	
	public String getCommentaire() {
		return this.comment;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public int getPidCommentaire() {
		return this.pidCommentaire;
	}
	
	public int getPidMessage() {
		return this.pidMessage;
	}
	
	/* Setters */
	public void setDateCommentaire(String d) {
		this.dateC = d;
	}
	
	public void setIdCommentaire(int id) {
		this.idCommentaire = id;
	}
	
	public void setIdUser(int id) {
		this.idUser = id;
	}
	
	public void setCommentaire(String msg) {
		this.comment = msg;
	}
	
	public void setUser(String us) {
		this.user = us;
	}
	
	public void setPidCommentaire(int pidC) {
		this.pidCommentaire = pidC;
	}
	
	public void setPidMessage(int pidM) {
		this.pidMessage = pidM;
	}
	
	@Override
	public void run() {
		// on décrémente le score de 1
		--score;
		
		/*if (score == 0) {
			synchronized(DecompteScoreComment.planif) 
            { 
				DecompteScoreComment.planif.notify(); 
            } 
		}*/
	}
}
