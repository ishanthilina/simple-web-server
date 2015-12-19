package info.ishans.html;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

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

        System.out.println(input);

        //check for the correct pattern -Ex : GET / HTTP/1.1
        Pattern pattern = Pattern.compile("^[A-Z]{3,4} [\\/][A-Za-z0-9]* HTTP[\\/]\\d\\.\\d");
        if (!pattern.matcher(input).matches()) {
            System.out.println("Match fail");
            response = new HTMLResponseImpl(400, "Bad request");
            return ParserStatus.PARSING_COMPLETE;
        }

        StringTokenizer inputTokenizer = new StringTokenizer(input);    //break using spaces

        //check number of tokens
        if (inputTokenizer.countTokens() != 3)
        {
            response = new HTMLResponseImpl(400, "Bad request");
            return ParserStatus.PARSING_COMPLETE;
        }
        HTMLRequestType requestType = GetRequestType(inputTokenizer.nextToken());
        String url = inputTokenizer.nextToken();

        //tokenize again - Text should be in "HTTP/X.X" format
        inputTokenizer = new StringTokenizer(inputTokenizer.nextToken(),"/");
        //check number of tokens
        if (inputTokenizer.countTokens() != 2)
        {
            response = new HTMLResponseImpl(400, "Bad request");
            return ParserStatus.PARSING_COMPLETE;
        }

        //skip HTTP text
        inputTokenizer.nextToken();
        requestedHTMLVersion = Double.parseDouble(inputTokenizer.nextToken());

        HTMLRequest htmlRequest = new HTMLRequestImpl(requestType, url, requestedHTMLVersion);
        response = htmlProcessor.HandleHTMLRequest(htmlRequest);

        return ParserStatus.PARSING_COMPLETE;
    }

    public String GetHTMLResponse() {

        StringBuilder responseBuilder = new StringBuilder();

        responseBuilder.append("HTTP/");
        responseBuilder.append(requestedHTMLVersion);
        responseBuilder.append(" ");
        responseBuilder.append(response.getResponseCode());
        responseBuilder.append(" ");
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

    HTMLRequestType GetRequestType(String type)
    {
        if ("GET".equals(type))
        {
            return  HTMLRequestType.GET;
        }
        else if("PUT".equals(type))
        {
            return  HTMLRequestType.PUT;
        }
        else if("POST".equals(type))
        {
            return  HTMLRequestType.POST;
        }
        else
        {
            return  HTMLRequestType.UNKNOWN;
        }
    }
}
