package info.ishans.html;

/**
 * Created by ishan on 12/19/15.
 */
public interface HTMLRequest {

    HTMLRequestType GetRequestType();
    String GetRequestedURL();
    Double GetHTTPVersion();
    //A method to get payload??
}
