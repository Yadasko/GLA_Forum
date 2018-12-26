# GLA_Forum

Repo pour le TP de GLA qui met en place un forum sécurisé.

## Listes des fichiers Java et leur utilité

### fr.acceis.forum.metier
- **Cryptutils**: Classe avec des méthodes statics pour gérer tout ce qui est lié au chiffrement des mots de passes
- **CustomLogger**: Classe avec des méthodes statics pour permettre de mettre en place des logs
  - Peut être amélioré en rajoutant des méthodes qui permettront de log directement un objet (et non pas écrire directement la ligne)
- **Datafetcher**: Interface définissant les méthodes liées à la base de données
- **Datafetcher_sqlite**: Implémentation de l'interface DataFetcher avec une BDD SQLITE
- **DBUtils**: Méthode permettant simplement de récuperer l'instance de datafetcher (Pattern Singleton)

### fr.acceis.forum.models
- **Message**: Modèle pour un message. Un message est associé à un fil de discussion
- **PasswordModel**: Un simple modèle pour avoir un mot de passe et son salt associé (utilisé dans Cryptutils)
- **Thread**: Modèle représentant un fil de discussion.
- **User**: Modèle représentant un utilisateur
- **UserSession**: Modèle représentant une session utilisateur. Contient aussi la méthode de login. (Pas super respectueux de la définition d'un modèle.)

### fr.acceis.forum.servlet
- **AccueilServlet**: Servlet pour la page d'accueil. Va charger la liste des threads et les passer à la JSP associée.
- **FileUploadServlet**: Servlet pour l'envoi d'une image. Permet de vérifier la taille et le type de fichier qui est envoyé. Ces images sont ensuites stockées en BLOB dans la BDD.
- **ImageServlet**: Servlet servant à l'affichage d'une image. Les images étant dans la BDD, on ne peut y accéder directement. (Evite par la même occasions les soucis de path traversing.)
- **LoginServlet**: Servlet pour l'affichage du form de login (GET) et le passage des paramètres à la méthode login de UserSession (POST).
- **LogoutServlet**: Servlet invalidant la session de l'utilisateur.
- **New_ThreadServlet**: Servlet permettant d'afficher le form de création de topic (GET) et le passage des paramètres pour la création d'un nouveau topic (POST).
- **SigninServlet**: Servlet permettant l'affichage du form de création d'utilisateur (GET) et le passage des paramètres pour cette création (POST).
- **ThreadServlet**: Servlet permettant l'affichage d'un tropic ainsi que de ces messages associés.
- **UserProfileServlet*: Servlet permettant l'affichage d'un profil utilisateur.

## Base de données
L'implémentation de la base de données se fait en utilisant SQLite pour sa facilité d'utilisation et de visualisation.
Toutes les requêtes sont disponibles dans la classe [Datafetcher_sqlite](https://github.com/Yadasko/GLA_Forum/blob/master/src/fr/acceis/forum/metier/Datafetcher_sqlite.java).
Il est important de noter que la base de données contient un trigger qui va supprimer tous les messages associés à un topic lors de la suppression de ce dernier.
```SQL
CREATE TRIGGER delete_associated_messages AFTER DELETE ON Threads
FOR EACH ROW 
BEGIN DELETE FROM Messages WHERE Messages.thread_id = OLD.id; END
```

## Améliorations
Le forum n'est en rien fini. Sans parler des innombrables améliorations graphiques et UX (comme rajouter des messages de feedback lors des erreurs), il reste quelques fonctionalités non-implémentées.

- Pouvoir supprimer les threads
  - Un trigger permettant de supprimer les messages associés à ce thread existe déjà, il suffit d'ajouter la méthode à l'interface de BDD et de trouver un bon endroit pour mettre le lien/bouton pour supprimer le thread
- Pouvoir supprimer les messages
- Pouvoir changer de mot de passe
