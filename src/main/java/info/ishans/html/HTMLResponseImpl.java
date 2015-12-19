package info.ishans.html;

/**
 * Created by ishan on 12/19/15.
 */
public class HTMLResponseImpl implements HTMLResponse {

    private int responseCode;
    private String responseBody;

    public HTMLResponseImpl(int responseCode, String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
