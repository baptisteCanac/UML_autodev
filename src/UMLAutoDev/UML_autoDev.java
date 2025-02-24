package UMLAutoDev;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;

/**
 * The UML_autoDev class is an action listener for processing UML files. 
 * It is registered as an action in the Tools menu of the IDE.
 * When triggered, it reads a UML file, verifies its content, analyzes the file,
 * and creates a new file based on the analysis.
 * 
 * This class is linked to the "Process UML File" action in the IDE's menu system.
 * It uses several classes to perform the verification, analysis, and creation 
 * of the processed file.
 * 
 * @author baptiste
 */

@ActionID(
        category = "File",
        id = "UMLAutoDev.ProcessUMLFile"
)
@ActionRegistration(
        displayName = "Process UML File"
)
@ActionReference(
        path = "Menu/Tools",
        position = 1
)
public final class UML_autoDev implements ActionListener {
    
    /**
     * Handles the action of processing the UML file. 
     * Reads the content of the UML file, verifies it, analyzes the file,
     * and creates a new file based on the analysis.
     * 
     * @param e the action event that triggers this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Reading the UML file
        Fichier fichier = new Fichier(OpenUMLFile.getSelectedFilePath());

        
        //System.out.println(fichier.getContentAsList());
        
        // Verifying the UML file content
        VerificationFichier verifCodeUML = new VerificationFichier(fichier.getContentAsList());
        
        // Analyzing the UML file
        FichierAnalyse fichierAnalyse = new FichierAnalyse(verifCodeUML.getRes());

        // Getting the analysis results
        HashMap<String, ArrayList<Triplet>> fichierApresAnalyse = fichierAnalyse.resultatAnalyse();
        //fichierAnalyse.afficherResultat();
        
        // Creating a new file based on the analysis
        CreateurFichier fichierCree = new CreateurFichier(fichierApresAnalyse);
        fichierCree.creer();

        /*if (filePath != null && !filePath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Traitement du fichier UML : " + filePath, "Traitement UML", JOptionPane.INFORMATION_MESSAGE);
            // Ajoute ici le code pour traiter le fichier UML
        } else {
            JOptionPane.showMessageDialog(null, "Aucun fichier UML sélectionné", "Erreur", JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
