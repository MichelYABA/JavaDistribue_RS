package up13.handler;

import java.net.*;
import java.io.*;

public class JDURLConnection extends URLConnection {

    public JDURLConnection(URL url) throws MalformedURLException {
		super(url);
		setContentHandlerFactory(new JDContentHandlerFactory());
    }	

	public void connect() {	}

   public InputStream getInputStream() {
		return null;
   }

   public String getHeaderField(String name) {
	if (name.equals("content-type")) return "handler";
	return null;
    }

}







