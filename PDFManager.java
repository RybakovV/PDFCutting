import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Vitaliy Ryvakov on 10.11.2016.
 */
public class PDFManager {

    public PDFManager() {

    }

    public static void cutToLastTwo(String fileInAbsolutePath, String fileOutAbsolutePath) throws IOException {
        PDDocument document = null;
        try {
            InputStream inputStream = new FileInputStream(fileInAbsolutePath);
            document = PDDocument.load(inputStream);
            if( document.isEncrypted() ){
                throw new IOException( "Encrypted documents are not supported for this example" );
            }
            int numberOfPage = document.getNumberOfPages();
            for (int i = 0; i < numberOfPage-2; i++) {
                document.removePage(0);
            }
            document.save(fileOutAbsolutePath);
        }
        finally{
            if( document != null ){
                document.close();
            }
        }
    }

}
