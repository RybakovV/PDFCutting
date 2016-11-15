import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Vitaliy Ryvakov on 10.11.2016.
 */
public class GUI extends JFrame {
    private final JLabel jLabelPrefix;
    private final JFileChooser jFileChooser;
    private JTextField jTextFieldPrefix;
    JButton cutButton;

    public GUI(){
        super("PDFCutting");
        setLayout(new FlowLayout());

        jFileChooser = new JFileChooser("C:\\Obmen");
        jFileChooser.setAcceptAllFileFilterUsed(false);
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.setControlButtonsAreShown(false);
        add(jFileChooser);
        jLabelPrefix = new JLabel("Prefix: ");
        jTextFieldPrefix = new JTextField("short-");
        jTextFieldPrefix.setPreferredSize(new Dimension(100,20));
        add(jLabelPrefix);
        add(jTextFieldPrefix);

        cutButton = new JButton("CUT");
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File files[] = jFileChooser.getSelectedFiles();
                for (File file: files) {
                    String fileAbsolutePath = file.getAbsolutePath();
                    String fileName = file.getName();
                    String fileOutAbsolutePath = fileAbsolutePath.substring(0,fileAbsolutePath.length()-fileName.length()) +
                                                jTextFieldPrefix.getText() + fileName;
                    try {
                        PDFManager.cutToLastTwo(fileAbsolutePath,fileOutAbsolutePath);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                jFileChooser.updateUI();
            }
        });
        add(cutButton);
    }
}
