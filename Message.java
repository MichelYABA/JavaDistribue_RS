
public class Message {
	private String dateM; //date du message
	private int idMessage; //identifiant du message
	private int idUser; //identifiant de l'utilisateur
	private String message; //contenu du message
	private String user; // nom de l'utilisateur
	
	
	public Message(String dateM, int idMessage, int idUser, String message, String user) {
	
		this.dateM = dateM;
		this.idMessage = idMessage;
		this.idUser = idUser;
		this.message = message;
		this.user = user;
		
	}

	//getteurs et setteurs
	
	
	public String getDateM() {
		return dateM;
	}


	public void setDateM(String dateM) {
		this.dateM = dateM;
	}


	public int getIdMessage() {
		return idMessage;
	}


	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}


	public int getIdUser() {
		return idUser;
	}
	


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Message [dateM=" + dateM + ", idMessage=" + idMessage + ", idUser=" + idUser + ", message=" + message
				+ ", user=" + user + "]";
	}
	
	
	
	
	

}
