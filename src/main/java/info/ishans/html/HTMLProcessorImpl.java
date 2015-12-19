package info.ishans.html;

import info.ishans.common.FileReader;

import java.io.IOException;

/**
 * Created by ishan on 12/19/15.
 */
public class HTMLProcessorImpl implements HTMLProcessor {

    private FileReader fileReader;

    public HTMLProcessorImpl() {

        fileReader = new FileReader();
    }

    public HTMLResponse HandleHTMLRequest(HTMLRequest request) {

        String fileContent = null;
        try {
            fileContent = fileReader.ReadWholeFile("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new HTMLResponseImpl(200,fileContent);
    }
}
