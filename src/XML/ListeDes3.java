package XML;


	import java.util.List;  
	 
	import javax.xml.bind.annotation.XmlRootElement;  
	  
	@XmlRootElement  public class ListeDes3 {  
		 
		public List<InfosMsgOuComm> LesMessages;  
		public ListeDes3() {}  
		public ListeDes3(List<InfosMsgOuComm> LesMessages) {  
		    super();  
		    
		    this.LesMessages = LesMessages;  
		}  
	
	}