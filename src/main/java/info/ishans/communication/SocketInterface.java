package info.ishans.communication;

import java.io.IOException;

/**
 * Created by ishan on 12/19/15.
 */
public interface SocketInterface {

    boolean InitConnections() throws IOException;
    void Listen() throws IOException;
}
