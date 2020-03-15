
public class Message {
	private String dateM; //date du message
	private int idMessage; //identifiant du message
	private int idUser; //identifiant de l'utilisateur
	private String message; //contenu du message
	private String user; // nom de l'utilisateur
	
	Message(){}
	
	/*Getters*/
	public String getDateMessage() {
		return dateM;
	}
	
	public int getIdMessage() {
		return idMessage;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getUser() {
		return user;
	}
	
	/* Setters */

}
