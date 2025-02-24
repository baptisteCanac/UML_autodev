# UML_autodev

Extension NetBeans pour générer des fichiers Java automatiquement à partir d'un fichier texte UML.

## Description

UML_autodev ajoute deux boutons dans le menu "Tools" de NetBeans :

- **Open UML File** : Permet de sélectionner un fichier contenant un diagramme UML au format texte (`UML.txt`) et d'en récupérer le chemin.
- **Process UML File** : Génère automatiquement les fichiers Java correspondants aux classes définies dans le diagramme UML. Les fichiers Java sont créés dans le même répertoire que le fichier UML d'origine.

## Utilisation

1. **Installation** : Une fois l'extension installée, vous verrez les deux nouveaux boutons dans le menu **Tools** de NetBeans.
2. **Traitement du fichier UML** : Lancez `Process UML File` pour générer les fichiers Java à partir de votre fichier UML.
3. **Fichiers générés** : Les fichiers Java seront créés dans le répertoire où se trouve le fichier UML.

### Structure du fichier UML

Créez un fichier `UML.txt` dans le répertoire de votre projet et écrivez-le selon la structure suivante :

#### Exemple de syntaxe :
- **Définir une classe** :  
  `NomClasse {`

- **Définir un attribut** :  
  `- nomVariable: type`  

  - Par défaut, une variable est générée avec le modificateur `private`.  
  - Pour définir une visibilité spécifique, ajoutez un préfixe :  
    - `-_ nomVariable: type` → `public type nomVariable;`  
    - `-__ nomVariable: type` → `protected type nomVariable;`  

- **Définir un constructeur** :  
  Si vous souhaitez que le programme ne génère rien dans le constructeur :  
  `+ NomClasse: _`  

  Si vous souhaitez que le programme génère un constructeur qui initialise toutes les variables :  
  `+ NomClasse: *`  
  (Le programme générera automatiquement un constructeur avec toutes les variables comme paramètres et les initialisera dans le corps du constructeur.)

- **Définir une méthode** :  
  `+ nomMethode: typeDeRetour`  

  - Par défaut, une méthode est générée avec le modificateur `public`.  
  - Pour définir une visibilité spécifique, ajoutez un préfixe :  
    - `+__ nomMethode: typeDeRetour` → `protected typeDeRetour nomMethode();`  
    - `+___ nomMethode: typeDeRetour` → `private typeDeRetour nomMethode();`  


- **Définir une méthode `get`** :  
  Le programme génère automatiquement la méthode `get` avec le retour de la variable correspondante :  
  `+ getNomVariable: typeDeRetour`

- **Définir une méthode `set`** :  
  Le programme génère automatiquement la méthode `set` avec le paramètre approprié :  
  `+ setNomVariable: typeDeRetour`

- **Définir une méthode `toString`**:
  Le programme génère automatiquement la méthode `toString` avec le bon retour, et l'annotation @Override :  
  `+ toString: String`

## Exemple de fichier UML
```
Utilisateur{
-_ prenom: String  
- nom: String
- cb: Integer
- tel: int
+ Utilisateur: *
+___ Utilisateur: _
+ getPrenom: String
+ setPrenom: void
+ getNom: String
+___ setCb: void
}
```

### Resultat en code Java
```java
public class Utilisateur{
	public String prenom;
	private String nom;
	private Integer cb;
	private int tel;

	/**
	  * Constructor of the class Utilisateur
	  *
	  * @param prenom
	  * @param nom
	  * @param cb
	  * @param tel
	  */
	public Utilisateur(String prenom, String nom, Integer cb, int tel){
		this.prenom = prenom;
		this.nom = nom;
		this.cb = cb;
		this.tel = tel;
	}



	/**
	  * Constructor of the class Utilisateur
	  *
	  */
	public Utilisateur(){
		//constructor code
	}


	/**
	  * Returns variable: prenom
	  *
	  * @return prenom
	  */
	public String getPrenom(){
		return this.prenom;
	}


	/**
	  * définit/redéfinit la variableprenom
	  *
	  * @param prenom
	  */
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}


	/**
	  * Returns variable: nom
	  *
	  * @return nom
	  */
	public String getNom(){
		return this.nom;
	}

	private void setCb(){
		//your code
	}




```

### Automatisations et gestion des erreurs

- Le programme détecte automatiquement si certaines variables nécessitent des imports spécifiques. Par exemple, si une variable est de type `ArrayList`, l'extension ajoutera l'import nécessaire en haut du fichier Java.
  
    (Ne fonctionne qu'avec ArrayList et HashMap pour le moment)
- **Génération automatique de la Javadoc** :  
  UML_autodev génère automatiquement une **Javadoc personnalisée** pour :
  - Les **constructeurs**, avec le bon `@param` pour chaque variable initialisée.
  - Les **getters**, incluant le `@return` correspondant.
  - Les **setters**, documentant le paramètre avec `@param`.

- **Gestion des erreurs**

    Le programme détecte et corrige certaines erreurs courantes pour assurer un fonctionnement fluide :
  - **Correction des déclarations incorrectes** : Si une définition de variable ou de méthode ne commence pas par + ou -, elle est automatiquement transformée en variable.

  - Pop-ups d'information : Lorsqu'une correction automatique est appliquée, une petite fenêtre s'affiche pour informer l'utilisateur de l'erreur détectée et de la correction effectuée. Cependant, le processus continuera apres avoir cliqué sur 'ok' et la génération du code fonctionnera normalement.

  - Débogage automatique en développement : Certaines erreurs sont corrigées automatiquement, mais le système est encore en amélioration pour gérer un plus large éventail de cas.

## Comment la faire fonctionner

Dans netbeans cliquez sur `tools` -> `plugins`;
Cliquez sur `downloaded` puis sur `Add Plugins...` ensuite sélectionnez le fichier .nbm de ce projet.
Le reste des fichiers de ce code n'est utile que pour les développeurs souhaitant contribuer

## Configuration

Si vous souhaitez gagner du temps, vous pouvez modifier le chemin de votre fichier UML (`UML.txt`) directement dans le fichier `OpenUmlFile.java` à la ligne 29.

## Contributions et questions

Je suis ouvert à toute modification, suggestion ou amélioration de cette extension. N'hésitez pas à proposer des idées ou à soumettre des pull requests !

## Licence

Ce projet est sous licence **Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)**.  
Vous êtes libre de copier, modifier et distribuer ce travail à des fins non commerciales, à condition de donner un crédit approprié à l'auteur original.
