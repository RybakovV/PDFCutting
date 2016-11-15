import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;

/**
 * Created by Vitaliy Ryvakov on 10.11.2016.
 */
public class RemoveFirstPage {
    private RemoveFirstPage() {
        //utility class, should not be instantiated.
        }
    /**
    * This will print the documents data.
    *
    * @param args The command line arguments.
    *
    * @throws Exception If there is an error parsing the document.
    */
    public static void main( String[] args ) throws Exception {

        String fileInPath = "C:\\";
        String fileInName = "PK-3100-00_Half_of_Odd_Numbers.pdf";
        String fileOutPath = "C:\\Obmen\\";
        String fileOutPrephics = "short-";
        String fileOutName = fileOutPrephics + fileInName;
        PDFManager.cutToLastTwo(fileInPath, fileInName, fileOutPath, fileOutName);
    }

}
