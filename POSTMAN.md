# Tester le CRUD des clients avec Postman

## Démarrer l'application

Le projet utilise MySQL. Démarre MySQL et vérifie les identifiants dans `src/main/resources/application.properties` (par défaut : base `autoloc_db`, utilisateur `root`, mot de passe vide), puis lance l'application depuis la racine du projet :

```powershell
.\mvnw.cmd spring-boot:run
```

L'API écoute sur `http://localhost:8080`.

## Importer les requêtes

Dans Postman, choisis **Import** et sélectionne `postman/AutoLoc-Clients.postman_collection.json`. La collection contient les cinq opérations CRUD sous `/api/clients`.

Pour créer un client, envoie d'abord **Créer un client**. Copie son `id` de la réponse dans la variable de collection `clientId`, puis essaie **Consulter**, **Modifier** et **Supprimer**. **Lister les clients** affiche tous les clients.

| Opération | Méthode | URL | Résultat attendu |
|---|---|---|---|
| Créer | POST | `http://localhost:8080/api/clients` | `201 Created` |
| Lister | GET | `http://localhost:8080/api/clients` | `200 OK` |
| Consulter | GET | `http://localhost:8080/api/clients/{id}` | `200 OK` ou `404 Not Found` |
| Modifier | PUT | `http://localhost:8080/api/clients/{id}` | `200 OK` ou `404 Not Found` |
| Supprimer | DELETE | `http://localhost:8080/api/clients/{id}` | `204 No Content` ou `404 Not Found` |

## Tester aussi les contrats

Importe `postman/AutoLoc-Contrats.postman_collection.json`, puis lance sa collection avec **Run** (Runner). Les cinq requêtes s'exécutent dans l'ordre : création, liste, détail, modification et suppression. La requête POST mémorise automatiquement l'identifiant créé dans `contractId`.

Le CRUD des contrats utilise `POST`, `GET` (liste et détail), `PUT` et `DELETE` sous `/api/contrats`. L'exemple crée un contrat sans réservation associée ; la relation `reservation` est facultative dans ce test.

Exemple de corps JSON pour POST et PUT :

```json
{
  "nom": "Ben Ali",
  "prenom": "Amine",
  "email": "amine@example.com",
  "telephone": "20123456",
  "numPermis": "P123456",
  "dateInscription": "2026-10-14"
}
```

## Suite CRUD pour les neuf entités

Le fichier `postman/AutoLoc-CRUD-Toutes-Entites.postman_collection.json` lance cinq requêtes par entité : création, liste, consultation par identifiant, modification et suppression. Les scripts Postman vérifient les statuts HTTP, l’identifiant renvoyé et le format de la liste. Importez la collection puis sélectionnez **Run** sur la collection complète.

Pour exécuter la suite sans serveur MySQL, démarrez l’application avec le profil Postman qui utilise une base H2 en mémoire :

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=postman"
```

L’application écoute sur `http://localhost:8080`. La base mémoire est recréée à chaque démarrage et convient aux essais CRUD de cette collection. Pour l’environnement habituel, démarrez MySQL puis lancez `.\mvnw.cmd spring-boot:run` sans profil.

