
public class Comment {
	
	
	private String dateC; //date du commentaire
	private int idCommentaire; //identifiant du commentaire
	private int idUser; //identifiant de l'utilisateur
	private String comment; //contenu du commentaire
	private String user; // nom de l'utilisateur
	private int pidCommentaire; //identifiant du commentaire que ce commentaire commente
	private int pidMessage; //identifiant du message que ce commentaire commente
	
	
	public Comment(String dateC, int idCommentaire, int idUser, String comment, String user, int pidCommentaire,
			int pidMessage) {
		
		this.dateC = dateC;
		this.idCommentaire = idCommentaire;
		this.idUser = idUser;
		this.comment = comment;
		this.user = user;
		this.pidCommentaire = pidCommentaire;
		this.pidMessage = pidMessage;
	}


	public String getDateC() {
		return dateC;
	}


	public void setDateC(String dateC) {
		this.dateC = dateC;
	}


	public int getIdCommentaire() {
		return idCommentaire;
	}


	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public int getPidCommentaire() {
		return pidCommentaire;
	}


	public void setPidCommentaire(int pidCommentaire) {
		this.pidCommentaire = pidCommentaire;
	}


	public int getPidMessage() {
		return pidMessage;
	}


	public void setPidMessage(int pidMessage) {
		this.pidMessage = pidMessage;
	}


	@Override
	public String toString() {
		return "Comment [dateC=" + dateC + ", idCommentaire=" + idCommentaire + ", idUser=" + idUser + ", comment="
				+ comment + ", user=" + user + ", pidCommentaire=" + pidCommentaire + ", pidMessage=" + pidMessage
				+ "]";
	}
	
	
	

}
