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
`nomVariable: type`

- **Définir un constructeur** :  
Si vous souhaitez que le programme ne génère rien dans le constructeur :  
`NomClasse: _`

(Note : La fonctionnalité d'initialisation des variables dans le constructeur n'est pas encore développée.)
- **Définir une méthode** :  
`nomMethode: typeDeRetour`

- **Définir une méthode `get`** :  
Le programme génère automatiquement la méthode `get` avec le retour de la variable correspondante :  
`getNomVariable: typeDeRetour`

- **Définir une méthode `set`** :  
Le programme génère automatiquement la méthode `set` avec le paramètre approprié :  
`+ setNomVariable: typeDeRetour`


### Fonctionnalités automatiques

- Le programme détecte automatiquement si certaines variables nécessitent des imports spécifiques. Par exemple, si une variable est de type `ArrayList`, l'extension ajoutera l'import nécessaire en haut du fichier Java.

## Configuration

Si vous souhaitez gagner du temps, vous pouvez modifier le chemin de votre fichier UML (`UML.txt`) directement dans le fichier `OpenUmlFile.java` à la ligne 29.

## Licence

Ce projet est sous licence **Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)**.  
Vous êtes libre de copier, modifier et distribuer ce travail à des fins non commerciales, à condition de donner un crédit approprié à l'auteur original.
