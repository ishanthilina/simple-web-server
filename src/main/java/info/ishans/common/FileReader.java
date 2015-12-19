package info.ishans.common;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ishan on 12/19/15.
 */
public class FileReader {
    private String folderLocation = "./files/";

    public String ReadWholeFile(String fileName) throws IOException {
        String fileContent;

        FileInputStream inputStream = new FileInputStream(folderLocation+fileName);
        try
        {
            fileContent = IOUtils.toString(inputStream);
        }
        finally {
            inputStream.close();
        }

        return fileContent;
    }
}
