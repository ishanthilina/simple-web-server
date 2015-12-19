package info.ishans.html;

/**
 * Created by ishan on 12/19/15.
 */
public class SimpleHTMLParser implements HTMLParser {

    private HTMLProcessor htmlProcessor;
    private HTMLResponse response;
    private Double requestedHTMLVersion = 1.0;  //TODO - properly set this after parsing input

    public SimpleHTMLParser(HTMLProcessor htmlProcessor) {
        this.htmlProcessor = htmlProcessor;
    }

    public ParserStatus GiveTextInput(String input) {

        //TODO - parse the request

        HTMLRequest htmlRequest = new HTMLRequestImpl(HTMLRequestType.GET, "/", requestedHTMLVersion);
        response = htmlProcessor.HandleHTMLRequest(htmlRequest);

        return ParserStatus.PARSING_COMPLETE;
    }

    public String GetHTMLResponse() {

        StringBuilder responseBuilder = new StringBuilder();

        responseBuilder.append("HTTP/");
        responseBuilder.append(requestedHTMLVersion);
        responseBuilder.append(" ");
        responseBuilder.append(response.getResponseCode());
        switch (response.getResponseCode())
        {
            case 200:
                responseBuilder.append("OK");
                break;
            default:
                responseBuilder.append("UnknownStatus");
                break;
        }
        responseBuilder.append("\n\n");
        responseBuilder.append(response.getResponseBody());

        return responseBuilder.toString();
    }
}
