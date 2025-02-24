package UMLAutoDev;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * The Triplet class represents a simple data structure that holds three elements,
 * all of type String. These elements can be accessed individually via getter methods.
 * 
 * @author baptiste
 */
public class Triplet {
    /**
     * The first element of the triplet.
     */
    private String Element1;
    
    /**
     * The second element of the triplet.
     */
    private String Element2;
    
    /**
     * The third element of the triplet.
     */
    private String Element3;
    
    /**
     * Constructs a Triplet object with the specified elements.
     * 
     * @param Element1 the first element of the triplet
     * @param Element2 the second element of the triplet
     * @param Element3 the third element of the triplet
     */
    public Triplet(String Element1, String Element2, String Element3){
        this.Element1 = Element1;
        this.Element2 = Element2;
        this.Element3 = Element3;
    }
    
    /**
     * Returns the first element of the triplet.
     * 
     * @return the first element as a String
     */
    public String getElement1(){
        return Element1;
    }
    
    /**
     * Returns the second element of the triplet.
     * 
     * @return the second element as a String
     */
    public String getElement2(){
        return Element2;
    }
    
    /**
     * Returns the third element of the triplet.
     * 
     * @return the third element as a String
     */
    public String getElement3(){
        return Element3;
    }
    
    /**
     * Returns a string representation of the Triplet object in the form:
     * "(Element1, Element2, Element3)".
     * 
     * @return a string representation of the triplet
     */
    @Override
    public String toString(){
        return "(" + this.Element1 + ", " + this.Element2 + ", " + this.Element3 + ")";
    }
}