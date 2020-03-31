package up13.handler;



import java.net.*;

public class Handler extends URLStreamHandler {

	public JDURLConnection openConnection(URL u) throws MalformedURLException {
		return new JDURLConnection(u);
	}
}

