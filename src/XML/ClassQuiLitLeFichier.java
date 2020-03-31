package XML;


	import javax.xml.parsers.DocumentBuilderFactory;  
	import javax.xml.parsers.DocumentBuilder;  
	import org.w3c.dom.Document;  
	import org.w3c.dom.NodeList;  
	import org.w3c.dom.Node;  
	import org.w3c.dom.Element;  
	import java.io.File;  
	public class ClassQuiLitLeFichier {
	public static void main(String argv[])   
	{  
	try   
	{  
	
	File file = new File("C:/eclipce amine/Final/r.xml");  
	 
	DocumentBuilderFactory d = DocumentBuilderFactory.newInstance();  
	
	DocumentBuilder db = d.newDocumentBuilder();  
	Document doc = db.parse(file);  
	doc.getDocumentElement().normalize();  
	System.out.println("Les elements de la racine : " + doc.getDocumentElement().getNodeName());  
	NodeList nodeList = doc.getElementsByTagName("LesMessages");  
	
	for (int itr = 0; itr < nodeList.getLength(); itr++)   
	{  
	Node node = nodeList.item(itr);  
	System.out.println("\n Nom du Noeud :" + node.getNodeName());  
	if (node.getNodeType() == Node.ELEMENT_NODE)   
	{  
	Element eElement = (Element) node;  
	System.out.println("L'id du message : "+ eElement.getElementsByTagName("idmsg").item(0).getTextContent());  
	System.out.println("Le contenue du message : "+ eElement.getElementsByTagName("msg").item(0).getTextContent());  
	  
	}  
	}  
	}   
	catch (Exception e)   
	{  
	e.printStackTrace();  
	}  
	}  
	//le client doit uitliser cette classe pour lire le contenue du fichier r.xml
	
}
