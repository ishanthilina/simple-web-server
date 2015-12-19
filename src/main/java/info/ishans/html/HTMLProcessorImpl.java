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

        String fileName = null;
        if("/".equals(request.GetRequestedURL()))
        {
            fileName = "index";
        }
        else
        {
            //remove the preceding /
            fileName = request.GetRequestedURL();
            fileName = fileName.substring(1, fileName.length());

            //remove trailing .html if any
            if (fileName.endsWith(".html"))
            {
                fileName = fileName.substring(0,fileName.length()-5);
            }
        }

        System.out.println(fileName);

        String fileContent = null;
        try {
            fileContent = fileReader.ReadWholeFile(fileName+".html");
        } catch (IOException e) {
            e.printStackTrace();
            try {
                fileContent = fileReader.ReadWholeFile("404.html");
                return new HTMLResponseImpl(404,fileContent);
            } catch (IOException e1) {
                e1.printStackTrace();
                return new HTMLResponseImpl(404,"File Not found");
            }

        }


        return new HTMLResponseImpl(200,fileContent);
    }
}
