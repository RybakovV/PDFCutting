import javax.swing.*;
import java.io.IOException;

/**
 * Created by Vitaliy Ryvakov on 10.11.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        GUI window = new GUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500,500);
        window.setVisible(true);


/*
        PDFManager pdfManager = new PDFManager();
        pdfManager.setFileInPath("C:\\200.pdf");
        System.out.println(pdfManager.ToText());
        pdfManager.setFileOutPath("C:\\200_out.pdf");
        pdfManager.removeFirstPage();
*/

    }
}
