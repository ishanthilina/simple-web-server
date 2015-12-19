package info.ishans.html;

/**
 * Created by ishan on 12/19/15.
 */
public class HTMLProcessorImpl implements HTMLProcessor {
    public HTMLResponse HandleHTMLRequest(HTMLRequest request) {
        return new HTMLResponseImpl(200,"<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My First Heading</h1>\n" +
                "\n" +
                "<p>My first paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }
}
