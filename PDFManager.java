import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Vitaliy Ryvakov on 10.11.2016.
 */
public class PDFManager {
    private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc ;
    private COSDocument cosDoc ;

    private String Text ;
    private String fileInPath;
    private File fileIn;
    private String fileOutPath;
    private File fileOut;

    public PDFManager() {

    }
    public String ToText() throws IOException
    {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        fileIn = new File(fileInPath);
        parser = new PDFParser(new RandomAccessFile(fileIn,"r")); // update for PDFBox V 2.0

        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(1000);

        // reading text from page 1 to 10
        // if you want to get text from full pdf fileIn use this code
        // pdfStripper.setEndPage(pdDoc.getNumberOfPages());

        Text = pdfStripper.getText(pdDoc);
        return Text;
    }



    public void setFileInPath(String fileInPath) {
        this.fileInPath = fileInPath;
    }




    public void removeFirstPage() throws IOException {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        fileIn = new File(fileInPath);
        parser = new PDFParser(new RandomAccessFile(fileIn,"r")); // update for PDFBox V 2.0
        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(1000);
        if( pdDoc.isEncrypted() ) {
            throw new IOException( "Encrypted documents are not supported for this example" );
        }
        if( pdDoc.getNumberOfPages() <= 1 ) {
            throw new IOException( "Error: A PDF document must have at least one page, " +
                    "cannot remove the last page!");
        }
        pdDoc.removePage(0);
        fileOut = new File(fileOutPath);
        pdDoc.save(fileOut);

    }


    public void setFileOutPath(String fileOutPath) {
        this.fileOutPath = fileOutPath;
    }

    public static void cutToLastTwo(String fileInPath, String fileInName, String fileOutPath, String fileOutName) throws IOException {
        PDDocument document = null;
        try {
            InputStream inputStream = new FileInputStream(fileInPath+fileInName);
            document = PDDocument.load(inputStream);
            if( document.isEncrypted() ){
                throw new IOException( "Encrypted documents are not supported for this example" );
            }
            int numberOfPage = document.getNumberOfPages();
            for (int i = 0; i < numberOfPage-2; i++) {
                document.removePage(0);
            }
            document.save(fileOutPath+fileOutName);
        }
        finally{
            if( document != null ){
                document.close();
            }
        }
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
