package UMLAutoDev;

/**
  * Used to analyse a file and classify evry object of the uml file
  * 
  * @author baptiste
  */
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Map;

public class FichierAnalyse {
    final private ArrayList<String> fichier;
    private HashMap<String, ArrayList<Triplet>> classes_associees_dic;
    
    public FichierAnalyse(ArrayList<String> fichier){
        this.fichier = fichier;
        this.classes_associees_dic = new HashMap<>();
    }
    
    public HashMap resultatAnalyse(){
        String nom_nouvelle_classe = null;
        ArrayList<Triplet> tempo = new ArrayList<Triplet>();
        
        for (String ligne: fichier){
            if (!ligne.isEmpty() && ligne.charAt(ligne.length() - 1) == '{'){
                //System.out.println("nouvelle classe trouvée: " + ligne);
                nom_nouvelle_classe = ligne.substring(0, ligne.length() - 1);
                tempo = new ArrayList<Triplet>();
            }else if (!ligne.isEmpty() && ligne.equals("}")){
                classes_associees_dic.put(nom_nouvelle_classe, tempo);
                nom_nouvelle_classe = null;
                tempo = null;
            }else{
                if (!ligne.isEmpty()){
                    
                    String[] ligne_split = ligne.split("\\s+");
                    
                    String nom_item = ligne_split[1];
                    nom_item = nom_item.substring(0, nom_item.length() -1);
                    
                    String type_renvoi = ligne_split[2];
                    
                    Triplet nouvelle_var = null;
                    
                    if (ligne.startsWith("-")){
                        if (ligne.charAt(1) == '_'){
                            if (ligne.charAt(2) == '_'){
                                nouvelle_var = new Triplet(nom_item, type_renvoi, "varProtected");
                            }else{
                                nouvelle_var = new Triplet(nom_item, type_renvoi, "varPublic");
                            }
                        }else{
                            nouvelle_var = new Triplet(nom_item, type_renvoi, "var");
                        }
                        
                        
                    }else if (ligne.startsWith("+")){
                        if (type_renvoi.equals("_") || type_renvoi.equals("*")){
                            System.out.println("constructeur trouvé");
                            nouvelle_var = new Triplet(nom_item, type_renvoi, "constructeur");
                        }
                        
                        else if (ligne.charAt(1) == '_'){
                            if (ligne.charAt(2) == '_'){
                                if (ligne.charAt(3) == '_'){
                                    nouvelle_var = new Triplet(nom_item, type_renvoi, "methodePrivate");
                                }else{
                                    System.out.println("fonction utilisée");
                                    nouvelle_var = new Triplet(nom_item, type_renvoi, "methodeProtected");
                                }
                            }else{
                                System.out.println("methode classique");
                                nouvelle_var = new Triplet(nom_item, type_renvoi, "methode");
                            }
                        }else{
                            nouvelle_var = new Triplet(nom_item, type_renvoi, "methode");
                        }
                        }
                    tempo.add(nouvelle_var);
                }
            }
        }
        
        return classes_associees_dic;
    }
    
    public void afficherResultat(){
        for (Map.Entry<String, ArrayList<Triplet>> entry : classes_associees_dic.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}