package info.ishans;

import info.ishans.communication.SocketInterface;
import info.ishans.communication.SocketInterfaceImpl;
import info.ishans.html.HTMLParser;
import info.ishans.html.HTMLProcessor;
import info.ishans.html.HTMLProcessorImpl;
import info.ishans.html.SimpleHTMLParser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class WebServer
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Server Started...!" );

        HTMLProcessor htmlProcessor = new HTMLProcessorImpl();
        HTMLParser htmlParser = new SimpleHTMLParser(htmlProcessor);
        SocketInterface socketInterface = new SocketInterfaceImpl(htmlParser, 8080);

        socketInterface.InitConnections();

        while (true)
        {
            socketInterface.Listen();
        }
    }
}
