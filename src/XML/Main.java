package XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Main {

	public static void main(String[] args) throws Exception{  
	    JAXBContext contextObj = JAXBContext.newInstance(ListeDes3.class);  
	  
	    Marshaller marshallerObj = contextObj.createMarshaller();  
	    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
	  
	    InfosMsgOuComm ans1=new InfosMsgOuComm(1,"message 1");  
	    InfosMsgOuComm ans2=new InfosMsgOuComm(2,"message 2");  
	      
	    ArrayList<InfosMsgOuComm> list=new ArrayList<InfosMsgOuComm>();  
	    list.add(ans1);  
	    list.add(ans2);  	      
	    
	    ListeDes3 que=new ListeDes3(list);  
	    //le nom du fichier xml generer est r.xml
	    marshallerObj.marshal(que, new FileOutputStream("r.xml"));  
	    //ici il faut l'envoyer au client qui doit le lire ce resultat
	    //add the jar dom
	    
	       
	}  
	}  