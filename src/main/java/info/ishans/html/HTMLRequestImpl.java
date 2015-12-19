package info.ishans.html;

/**
 * Created by ishan on 12/19/15.
 */
public class HTMLRequestImpl implements HTMLRequest {

    private HTMLRequestType requestType;
    private String requestedURL;
    private Double httpVersion;

    public HTMLRequestImpl(HTMLRequestType requestType, String requestedURL, Double httpVersion) {
        this.requestType = requestType;
        this.requestedURL = requestedURL;
        this.httpVersion = httpVersion;
    }

    public HTMLRequestType GetRequestType() {
        return requestType;
    }

    public String GetRequestedURL() {
        return requestedURL;
    }

    public Double GetHTTPVersion() {
        return httpVersion;
    }
}
