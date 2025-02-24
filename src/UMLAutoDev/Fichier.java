package UMLAutoDev;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * The Fichier class represents a file that is read from a specified path.
 * It stores the content of the file as a list of strings, where each string
 * represents a line from the file.
 * 
 * This class is used to read the contents of a file into memory and retrieve
 * it as a list.
 * 
 * @author baptiste
 */
import java.nio.file.*;
import java.nio.channels.FileChannel;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


public class Fichier{
    //private Integer longueur;
    private ArrayList<String> contenu;
    
    public Fichier(String chemin){
        Path path = Paths.get(chemin);
        this.contenu = new ArrayList<>();

        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            List<String> lignes = Files.readAllLines(path);
            this.contenu.addAll(lignes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getContentAsList(){
        return this.contenu;
    }
}