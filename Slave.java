
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;


public class Slave implements Runnable{
    private  final Socket socket;
    private File fichier;
    private Message message;
    private Comment comment;
    int val_imp;
    private List<Message> listeMessage;
    private List<Comment> listeComment;
    private List<Integer> listValImp = new ArrayList<>();
    
    Slave(Socket s, File f){
    	listeMessage = new ArrayList<Message>();
    	listeComment = new ArrayList<Comment>();
		fichier = new File("/home/yaba/Documents/M1InfoU13/s2/PDJ/DEMJava/src/reseauSocial.txt"); 
        this.socket = s;
        
    }

    @Override
    public void run() {
		     BufferedReader input;
			try {
				input = new BufferedReader(new FileReader(fichier));
				
				 StringBuffer sb = new StringBuffer();
	             //sb.append(input.readLine());
	             //System.out.println(sb);
	             
	             String st; 
		   		  int i=0;
		   			while ((st = input.readLine()) != null) {
		   				++i;
		   				//sb.append(st);
		   				String[] command = st.split("[|]");
		   				//System.out.println(i+" - "+command[3]);
		   				//String[] command = sb.toString().split("|");
		   				System.out.println(i+" - "+st);
		   				
		   				if (command.length == 4) {//c'est un message
		   					int idMessage = Integer.parseInt(command[0]);
		   					int idUser = Integer.parseInt(command[1]);
		   					String msge = command[2];
		   					String user = command[3];
		   					message = new Message(idMessage, idUser, msge, user);
		   					listeMessage.add(message);
		   				}
		   				else if (command.length > 4) { // C'est un commentaire
		   					int idComment = Integer.parseInt(command[0]);
		   					int idUser = Integer.parseInt(command[1]);
		   					String cmt = command[2];
		   					String user = command[3];
		   					int pidComment = Integer.parseInt(command[4]);
		   					int pidMessage = Integer.parseInt(command[5]);
		   					comment = new Comment(idComment, idUser, cmt, user, pidComment, pidMessage);
		   					listeComment.add(comment);
		   				}
		   			}
		   			
		   			for (int j=0; j<listeMessage.size(); j++) {
		   				
		   				for (int k=0; k<listeComment.size(); k++) {
		   					
		   					if (listeComment.get(k).getPidMessage() == listeMessage.get(j).getIdMessage()) {
		   						//On calcule la valeur d'importance d'un message quelconque
		   						val_imp = listeMessage.get(j).getScore()+listeComment.get(k).getScore();
		   						listValImp.add(val_imp); // on ajoute la valeur d'importance dans une liste
		   					}
		   					
		   				}
		   				
		   			}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
         
             
             
             /*switch (command[0]) {
                 case "SimplePrimerTest": 
                         primerAndWait(Integer.parseInt(command[1]));
                     break;
                 case "MultiplePrimerTest": 
                         primerAndCompletion (command);
                     break;
                 case "MultiplePrimerTest_Stream": 
                         primerAndCompletion_Stream (Stream.of(command));
                     break;
                 case "End": server.setIsFinished(true);
                     break;
                 default: ;
             }*/
             
             
    	//InputStream is;
        //try {
        	/*Preparation des entrees*/
        	/*is = socket.getInputStream();
        	InputStreamReader isr = new InputStreamReader(is);
            BufferedReader input = new BufferedReader(isr);
            
            BufferedReader br = new BufferedReader(new FileReader(fichier)); 
  		  
  		    String st; 
  		    int i=0;
  		    try {
  		    	while ((st = br.readLine()) != null) {
  		    		++i;
  		    		System.out.println(i+" - "+st);
  		    	}
  		    }
  			catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} */
  			    
            
            //Preparation des sorties
           // OutputStream os = socket.getOutputStream();
            //PrintWriter pw = new PrintWriter(os, true);
            
            /*for(;;){
				String req;
				while((req=input.readLine())!=null)
					//String rep = ;
					pw.println("Size = "+req.length());
			}*/
				
           
         /*}
        catch (SocketException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        try { 
            socket.close(); 
        }
        catch (IOException e) {
            System.out.println(e);
        }*/
    }
}
   
