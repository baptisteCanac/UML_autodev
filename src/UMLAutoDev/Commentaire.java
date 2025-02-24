package UMLAutoDev;

import java.util.ArrayList;

/**
 * This class generates Javadoc comments dynamically.
 * It allows setting descriptions, parameters, and return values.
 * 
 * @author baptiste
 */
public class Commentaire {
    private String debut = "\n\t/**\n\t  *";
    private String texte;
    private ArrayList<String> returns;
    private ArrayList<String> param;
    private String code;
    
    public Commentaire(){
        this.texte = "";
        this.code = "";
        this.returns = new ArrayList<String>();
        this.param = new ArrayList<String>();
    }
    
    public void addReturn(String nomVar){
        this.returns.add(nomVar);
    }
    
    public void addParam(String param){
        this.param.add(param);
    }
    
    public void setTexte(String texte){
        this.texte = texte;
    }
    
    public String creer(){
        this.code += debut;
        
        if (texte.length() != 0){
            this.code += " " + texte;
            this.code += "\n\t  *";
        }
        
        if (!returns.isEmpty()){
            // si la méthode renvoie qlq chose
            Integer i = 0;

            if (returns.size() == 1){
                this.code += " Returns variable: ";
            }else{
                this.code += " Returns the fields: ";
            }

            for (String element: returns){
                i ++;
                if (i > 1){
                    this.code += ", ";
                }
                this.code += element;
            }
            
            this.code += "\n\t  *";
            this.code += "\n\t  *";
            
            for (String element: returns){
                this.code += " @return ";
                this.code += element;
            }
        }
        
        if(!param.isEmpty()){
            for (String parametre: param){
                this.code += "\n\t  * @param ";
                this.code += parametre;
            }
        }
        
        this.code += "\n\t  */\n";
        //System.out.println("---" + this.code);
        return this.code;
    }
}
