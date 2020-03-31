package up13.handler;
import java.net.*;

class JDContentHandlerFactory implements ContentHandlerFactory {
	public JDContentHandlerFactory() { }

	public ContentHandler createContentHandler(String mimeType) {
		return new JDContentHandler();
	}
}
