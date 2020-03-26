import java.util.TimerTask;

public class Message  extends TimerTask{
	private static int score;
	private String dateM; //date du message
	private int idMessage; //identifiant du message
	private int idUser; //identifiant de l'utilisateur
	private String message; //contenu du message
	private String user; // nom de l'utilisateur
	private static boolean inactif;
	
	Message(){
		inactif = false;
		score=20;
	}
	
	Message(int idM, int idU, String msge, String user){
		this.idMessage = idM;
		this.idUser = idU;
		this.message = msge;
		this.user = user;
		inactif = false;
		score=20;
	}
	
	public static boolean getInactif() {
		return inactif;
	}
	
	/*Getters*/
	
	public  int getScore() {
		return score;
	}
	public String getDateMessage() {
		return this.dateM;
	}
	
	public int getIdMessage() {
		return this.idMessage;
	}
	
	public int getIdUser() {
		return this.idUser;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getUser() {
		return this.user;
	}
	
	/* Setters */
	public void setDateMessage(String d) {
		this.dateM = d;
	}
	
	public void setIdMessage(int id) {
		this.idMessage = id;
	}
	
	public void setIdUser(int id) {
		this.idUser = id;
	}
	
	public void setMessage(String msg) {
		this.message = msg;
	}
	
	public void setUser(String us) {
		this.user = us;
	}
	
	public void setScore(int s) {
		//this.score=s;
	}

	@Override
	public void run() {
		--score;
		/*if (score == 0) {*/
			
				// on décrémente le score de 1
				
				
				if (score == 0)
					inactif = true;
				
				synchronized(Server.scoreMsge) 
	            { 
					Server.scoreMsge.notify(); 
	            } 
		/*}*/
	}

}
