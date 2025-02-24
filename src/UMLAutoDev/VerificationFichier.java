package UMLAutoDev;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * The VerificationFichier class is responsible for verifying the content of a UML file.
 * It checks for common syntax errors and applies temporary fixes where necessary.
 * If an error is found, it displays a message to the user and attempts to correct the issue.
 * This class is used to validate the structure and format of UML declarations in the file.
 * 
 * Common errors handled by this class include:
 * - Invalid characters at the start of variable or method declarations.
 * - Presence of spaces in class names, which are temporarily merged for analysis.
 * 
 * @author baptiste
 */
public class VerificationFichier {
    
    /**
     * List to store the corrected or validated lines of the UML file.
     */
    private ArrayList<String> codeVerif = new ArrayList<String>();

    /**
     * Temporary string to hold error messages for invalid lines.
     */
    private String retour = "";
    
    /**
     * Constructs a VerificationFichier object that processes and verifies the content of the given UML file.
     * 
     * @param fichier an ArrayList containing the lines of the UML file to be verified
     */
    public VerificationFichier(ArrayList<String> fichier) {
        for (String ligne : fichier) {
            if (!ligne.isEmpty()) {
                if (ligne.charAt(ligne.length() - 1) == '{') {
                    if (ligne.length() - ligne.replace(" ", "").length() > 0) {
                        codeVerif.add(ligne.replace(" ", ""));
                        JOptionPane.showMessageDialog(null, "Le nom de la classe ne peut pas contenir d'espaces.\nLors de l'analyse, les mots espacés seront temporairement fusionés.\nPensez à modifier votre fichier UML pour éviter ce problème", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        codeVerif.add(ligne);
                    }
                } else if (ligne.charAt(0) == '}' || ligne.charAt(ligne.length() - 1) == '{') {
                    codeVerif.add("}");
                } else if (ligne.charAt(0) != '-' && ligne.charAt(0) != '+') {
                    codeVerif.add("-" + ligne.substring(1));
                    retour = "Erreur de Syntaxe à la ligne " + ligne + " : Une déclaration de variable ou de méthode doit commencer par '-' ou par '+' et non par '" + ligne.charAt(0) + "'.\nVotre définition commence par un caractère invalide.\nVotre ligne à été temporairement corrigée en variable.\nPensez à modifier votre fichier UML pour éviter ce problème";
                    JOptionPane.showMessageDialog(null, retour, "Information", JOptionPane.INFORMATION_MESSAGE);
                    retour = "";
                } else {
                    codeVerif.add(ligne);
                }
            }
        }
    }

    /**
     * Returns the list of validated and corrected lines after the verification process.
     * 
     * @return an ArrayList containing the corrected or validated lines of the UML file
     */
    public ArrayList getRes() {
        return codeVerif;
    }

    /**
     * Returns a string representation of the verified content of the UML file.
     * The content is printed line by line to the console.
     * 
     * @return a string representation of the verified UML content
     */
    @Override
    public String toString() {
        for (String element : codeVerif) {
            System.out.println(element);
        }
        return "test";
    }
}
