package up13.handler;


import java.net.*;
import java.io.*;

public class JDContentHandler extends ContentHandler {	
	public String fileName="";
	 public static String file="";
	

	public Object getContent(URLConnection urlc) throws IOException {
		URL url = urlc.getURL();
		String[] userInfo = url.getUserInfo().split("");		
		fileName = url.getQuery();
		setFile();
		return new JDContent(fileName,file);
	}
	
	public static void setFile() throws IOException {
		file="reseauSocial.txt";
		InputStream in = new FileInputStream(file);
		int b;
		while ((b = in.read()) != -1) {
			file += (char) b; 
		};
		in.close();	
		
	}
		
	public static void error(String s){
		System.out.println(s); System.exit(1);
	}
}
