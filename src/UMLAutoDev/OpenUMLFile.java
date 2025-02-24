package UMLAutoDev;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

import java.nio.file.Path;
import java.nio.file.Paths;

@ActionID(
        category = "File",
        id = "UMLAutoDev.OpenUMLFile"
)
@ActionRegistration(
        displayName = "Open UML file"
)
@ActionReference(
        path = "Menu/Tools",
        position = 2
)
public final class OpenUMLFile implements ActionListener {

    private static String selectedFilePath;
    
    /**
     * Handles the action when the user selects "Open UML File".
     * Opens a file chooser to select a UML file and stores its path.
     * @param e The action event triggered by the user.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Affichage pour indiquer que l'on va ouvrir un fichier
        
        System.out.println("Opening selected file");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an UML file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers UML", "uml", "txt"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File umlFile = fileChooser.getSelectedFile();
            selectedFilePath = umlFile.getAbsolutePath();

            // Affichage du fichier sélectionné dans un message et dans la console
            JOptionPane.showMessageDialog(null, "Selected file : " + selectedFilePath, "UML file", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Selected file : " + selectedFilePath);  // Affichage du chemin dans la console
        } else {
            JOptionPane.showMessageDialog(null, "No selected file ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Gets the path of the selected UML file.
     * Used to create files in the uml.txt repertory
     * @return The absolute path of the selected file.
     */
    public static String getSelectedFilePath() {
        return selectedFilePath;
    }
    
    /**
     * Gets the directory path of the selected UML file.
     * @return The parent directory of the selected file.
     */
    public static String getPath(){
        Path path = Paths.get(selectedFilePath);
        return path.getParent().toString();
    }
}
