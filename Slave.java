import java.io.File;

public class Slave implements Runnable {
	private File fichier;
	private Server server;
	
	
	

	public Slave(File fichier, Server server) {
		// TODO Auto-generated constructor stub
	}
	

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader in = new BufferedReader(new FileReader("c:/test.txt"));
			String line;
			while ((line = in.readLine()) != null)
			{
		      // Afficher le contenu du fichier
   			  System.out.println (line);
			}
			in.close();
 
			
		}catch(IOException e) {System.err.println(e);}
		
		
		
	}

}
