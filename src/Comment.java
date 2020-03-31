//XML dans Final-XML
//Handler dans JavaD-up13
public class Comment {
	
	private String dateC; //date du commentaire
	private int idCommentaire; //identifiant du commentaire
	private int idUser; //identifiant de l'utilisateur
	private String comment; //contenu du commentaire
	private String user; // nom de l'utilisateur
	private int pidCommentaire; //identifiant du commentaire que ce commentaire commente
	private int pidMessage; //identifiant du message que ce commentaire commente
	Comment(){}
	
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
}
