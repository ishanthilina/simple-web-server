package info.ishans.communication;

import info.ishans.html.HTMLParser;
import info.ishans.html.ParserStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ishan on 12/19/15.
 */
public class SocketInterfaceImpl implements SocketInterface {

    private int portNo = 8080;
    private ServerSocket servSocket;

    private HTMLParser htmlParser;

    public SocketInterfaceImpl(HTMLParser htmlParser, int portNo) {
        this.htmlParser = htmlParser;
        this.portNo = portNo;
    }

    public boolean InitConnections() throws IOException {
        servSocket = new ServerSocket(portNo);
        return true;
    }

    public void Listen() throws IOException {
        String str;

        Socket fromClientSocket = servSocket.accept();

        System.out.println("Connection from : " + fromClientSocket.getInetAddress().toString()+" : "+ fromClientSocket.getLocalPort());

        PrintWriter pw = new PrintWriter(fromClientSocket.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));
        while ((str = br.readLine()) != null) {
            ParserStatus status = htmlParser.GiveTextInput(str);
            if (status == ParserStatus.PARSING_COMPLETE)
            {
                pw.println(htmlParser.GetHTMLResponse());
                break;
            }
        }

        //break the connection
        pw.close();
        br.close();
        fromClientSocket.close();
    }
}
