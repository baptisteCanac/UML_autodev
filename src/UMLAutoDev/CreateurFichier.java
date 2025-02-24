package UMLAutoDev;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Create the code file in calling NouveauFichier
 * then create the file in the right path
 * 
 * @author baptiste
 */
public class CreateurFichier {
    private HashMap<String, ArrayList<Triplet>> data;
    private String repertoire;
    private String dossier;
    
    public CreateurFichier (HashMap<String, ArrayList<Triplet>> data){
        this.data = data;
        try {
            Process process = Runtime.getRuntime().exec("pwd");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String currentDirectory = reader.readLine();
            repertoire = currentDirectory;
            String[] dossier_tempo = repertoire.split("/");
            dossier = dossier_tempo[dossier_tempo.length -1];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void creer(){
        for (Map.Entry<String, ArrayList<Triplet>> entry : data.entrySet()) {
            String code_par_classe = "";
            NouveauFichier fichier = new NouveauFichier();
            
            String importeur = "";
            
            for (Triplet element: entry.getValue()){
                try{
                    if (element.getElement2().substring(0, 10).equals("ArrayList<")){
                        fichier.nouvelImport("java.util.ArrayList");
                    }else if (element.getElement2().substring(0, 8).equals("HashMap<")){
                        fichier.nouvelImport("java.util.HashMap");
                    }
                }catch (Exception e){}
                
                if (element.getElement3() == "var"){
                    fichier.nouvelleVar(element);
                }else if (element.getElement3() == "varPublic"){
                    fichier.nouvelleVar(element, "public");
                }else if (element.getElement3() == "varProtected"){
                    fichier.nouvelleVar(element, "protected");
                }else if (element.getElement3() == "constructeur"){
                    fichier.nouveauConstructeur(element);
                }else if (element.getElement3() == "methode"){
                    if (element.getElement1().substring(0, 3).equals("get")){
                        fichier.nouveauGet(element);
                    }else if (element.getElement1().substring(0, 3).equals("set")){
                        for (Triplet el: entry.getValue()){
                            if (element.getElement1().substring(3, element.getElement1().length()).toLowerCase().equals(el.getElement1())){
                                fichier.nouveauSet(element, el);
                            }
                        }
                    }else if (element.getElement1().equals("toString")){
                        fichier.nouveauToString(element);
                    }else if (element.getElement1().equals("main")){
                        fichier.nouveauMain(element);
                    }else{
                        fichier.nouvelleMethode(element);
                    }
                }else if (element.getElement3() == "methodeProtected"){
                    fichier.nouvelleMethode(element, "protected");
                }else if (element.getElement3() == "methodePrivate"){
                    fichier.nouvelleMethode(element, "private");
                }
            }
            this.creerFichier(entry.getKey(), fichier.creerCode(entry.getKey()));
        }
    }
    
    public void creerFichier(String nom_fichier, String code) {
        String fileName = OpenUMLFile.getPath() + "/" + nom_fichier + ".java";
        String fileContent = code;

        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("Created file: " + file.getName());

                // Écriture dans le fichier
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(fileContent);
                }

                System.out.println("Content write with succes !");
            } else {
                System.out.println("File allready exist");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating file");
            e.printStackTrace();
        }
    }
}
