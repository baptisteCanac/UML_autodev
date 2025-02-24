package UMLAutoDev;

import java.util.ArrayList;

/**
 * The NouveauFichier class is responsible for generating code based on the input
 * triplet data. It handles imports, variable declarations, constructors, getters, setters,
 * toString methods, and main methods, among other functionalities. The class allows the
 * construction of Java code based on the specified components.
 * 
 * The code generated is stored in the 'code' attribute and can be retrieved as a string
 * representation of the class.
 * 
 * @author baptiste
 */
public class NouveauFichier {
    /**
     * A string that holds the imports for the class.
     */
    private String imports = "";
    
    /**
     * A string that holds the generated code for the class.
     */
    private String code = "";
    
    /**
     * A list that holds the variables (triplets) for the class.
     */
    private ArrayList<Triplet> variables = new ArrayList<Triplet>();
    
    /**
     * Adds a new import statement to the imports string.
     * 
     * @param importStr the import statement to be added
     */
    public void nouvelImport(String importStr){
        this.imports += ("import " + importStr + ";\n");
    }
    
    /**
     * Adds a new variable to the code as a private field.
     * 
     * @param var the triplet representing the variable to be added
     */
    public void nouvelleVar(Triplet var){
        this.code += "\tprivate " + var.getElement2() + " " + var.getElement1() + ";\n";
        variables.add(var);
    }
    
    /**
     * Adds a new variable to the code with a specified visibility.
     * 
     * @param var the triplet representing the variable to be added
     * @param visibility the visibility modifier (e.g., public, private)
     */
    public void nouvelleVar(Triplet var, String visibility){
        this.code += "\t" + visibility + " " + var.getElement2() + " " + var.getElement1() + ";\n";
        variables.add(var);
    }
    
    /**
     * Adds a new constructor to the class, based on the provided triplet.
     * 
     * @param constr the triplet representing the constructor to be added
     */
    public void nouveauConstructeur(Triplet constr){
        if (constr.getElement2().equals("_")){
            this.code += "\n";
            Commentaire commentaire = new Commentaire();
            commentaire.setTexte("Constructor of the class " + constr.getElement1());
            this.code += commentaire.creer();
            this.code += "\tpublic " + constr.getElement1() + "(){\n\t\t//constructor code\n\t}\n\n";
            
        }else if (constr.getElement2().equals("*")){
            //this.code += "\n";
            Commentaire commentaire = new Commentaire();
            commentaire.setTexte("Constructor of the class " + constr.getElement1());
            
            for (Triplet element: variables){
                commentaire.addParam(element.getElement1());
            }
            this.code += commentaire.creer();
            
            this.code += "\tpublic " + constr.getElement1() + "(";
            
            Integer i = 0;
            
            for (Triplet element: variables){
                if (i == 0){
                    this.code += element.getElement2() + " " + element.getElement1();
                }else{
                    this.code += ", " + element.getElement2() + " " + element.getElement1();
                }
                i += 1;
            }
            this.code += "){\n\t\t";
            
            i = 0;
            if (variables.isEmpty()){
                this.code += "//You select '*' for the constructor however no variable has been finded\n\t";
            }else{
                for (Triplet element: variables){
                    if (i == variables.size() -1){
                        this.code += "this." + element.getElement1() + " = " + element.getElement1() + ";\n\t";
                    }else{
                        this.code += "this." + element.getElement1() + " = " + element.getElement1() + ";\n\t\t";
                    }
                    i ++;
                }
            }
            
            this.code += "}\n\n";
        } 
    }
    
    /**
     * Adds a new getter method for the specified variable.
     * 
     * @param get the triplet representing the getter method to be added
     */
    public void nouveauGet(Triplet get){
        String nomMethode = get.getElement1();
        String retourMethode = get.getElement2();
        String varRetournee = nomMethode.substring(3, nomMethode.length()).toLowerCase();
        
        Commentaire commentaire = new Commentaire();
        commentaire.addReturn(varRetournee);
        //commentaire.addParam(nomMethode.substring(3, nomMethode.length()).toLowerCase());
        this.code += commentaire.creer();
        this.code += "\tpublic " + retourMethode + " " + nomMethode + "(){\n\t\treturn this." + nomMethode.substring(3, nomMethode.length()).toLowerCase() + ";\n\t}\n\n";
    }
    
    /**
     * Adds a new setter method for the specified variable.
     * 
     * @param set the triplet representing the setter method to be added
     * @param el the triplet representing the element to be set
     */
    public void nouveauSet(Triplet set, Triplet el){  
        Commentaire commentaire = new Commentaire();
        commentaire.setTexte("définit/redéfinit la variable" + set.getElement1().substring(3, set.getElement1().length()).toLowerCase());
        commentaire.addParam(set.getElement1().substring(3, set.getElement1().length()).toLowerCase());
        this.code += commentaire.creer();
        this.code += "\tpublic " + set.getElement2() + " " + set.getElement1() + "(" + el.getElement2() + " " + el.getElement1() + "){\n\t\tthis." + set.getElement1().substring(3, set.getElement1().length()).toLowerCase() + " = " + set.getElement1().substring(3, set.getElement1().length()).toLowerCase() + ";\n\t}\n\n";
    }
    
    /**
     * Adds a new toString method for the class.
     * 
     * @param el the triplet representing the element for the toString method
     */
    public void nouveauToString(Triplet el){
        this.code += "\t@Override\n\tpublic " + el.getElement2() + " " + el.getElement1() + "(){\n\t\treturn \"\";\n\t}\n\n";

    }
    
    /**
     * Adds a new main method to the class.
     * 
     * @param el the triplet representing the main method
     */
    public void nouveauMain(Triplet el){
        this.code += "\tpublic static void main(String[] args) {\n\t\t// your main\n\t}";
    }
    
    /**
     * Adds a new method to the class.
     * 
     * @param el the triplet representing the method to be added
     */
    public void nouvelleMethode(Triplet el){
        this.code += "\tpublic " + el.getElement2() + " " + el.getElement1() + "(){\n\t\t//your code here\n\t}\n\n";
    }
    
    /**
     * Adds a new method with specified visibility to the class.
     * 
     * @param el the triplet representing the method to be added
     * @param visibility the visibility modifier (e.g., public, private)
     */
    public void nouvelleMethode(Triplet el, String visibility){
        this.code += "\t" + visibility + " " + el.getElement2() + " " + el.getElement1() + "(){\n\t\t//your code\n\t}\n\n";
    }
    
    /**
     * Creates the full class code by combining imports, class signature, and generated code.
     * 
     * @param nomClasse the name of the class to be generated
     * @return the full code of the class as a string
     */
    public String creerCode(String nomClasse){
        String signatureClasse = "public class " + nomClasse + "{\n";
        
        if (!this.imports.isEmpty()){
            signatureClasse = "\n" + signatureClasse;
        }
        
        return this.imports + signatureClasse + this.code;
    }
    
    /**
     * Returns the generated code of the class.
     * 
     * @return the generated code as a string
     */
    public String getCode(){
        return this.code;
    }
    
    /**
     * Returns a string representation of the class.
     * 
     * @return the generated code as a string
     */
    @Override
    public String toString(){
        return this.code;
    }
}