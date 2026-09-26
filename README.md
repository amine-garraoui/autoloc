# AutoLoc

AutoLoc est le modèle de domaine JPA d'une plateforme de gestion de location de véhicules multi-agences. Ce projet met en œuvre les entités et associations du diagramme fourni pour l'étude de cas ASI.

## Technologies

- Java 17
- Maven
- Spring Boot 3.1.3
- Spring Data JPA et Spring MVC
- MySQL
- Lombok
- H2 pour le test de démarrage et la génération du schéma en mémoire

## Entités

Agence, Vehicule, Equipement, Client, Employe, Reservation, Contrat, Paiement et Maintenance.

## Énumérations

- `CategorieVehicule` : `CITADINE`, `BERLINE`, `SUV`, `UTILITAIRE`
- `StatutVehicule` : `DISPONIBLE`, `LOUE`, `MAINTENANCE`
- `RoleEmploye` : `AGENT`, `MANAGER`
- `StatutReservation` : `EN_ATTENTE`, `CONFIRMEE`, `ANNULEE`, `TERMINEE`
- `ModePaiement` : `CARTE`, `ESPECES`, `VIREMENT`

Les cinq listes de valeurs sont celles montrées sur le diagramme de classes du PDF. Les enums sont persistés sous forme de chaînes (`EnumType.STRING`).

## Base de données

Nom de la base : `autoloc`.

Hibernate crée/met à jour les tables avec `spring.jpa.hibernate.ddl-auto=update`. `database.sql` crée la base si l'on souhaite la préparer manuellement ; il ne prétend pas que des tables ont déjà été générées. L'utilisateur MySQL doit avoir le droit de créer la base si l'option `createDatabaseIfNotExist=true` est utilisée.

## Configuration MySQL

Par défaut, l'application se connecte à `jdbc:mysql://localhost:3306/autoloc` avec l'utilisateur `root` et un mot de passe vide, ce qui correspond à une installation locale de développement. Aucun secret n'est stocké dans le dépôt. Définissez selon votre installation les variables `DB_URL`, `DB_USERNAME` et `DB_PASSWORD` avant de lancer Maven. Exemple PowerShell :

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "votre-mot-de-passe-local"
```

Vous pouvez aussi définir `DB_URL` si votre hôte/port MySQL diffère.

## Installation et lancement

Un JDK 17 et Maven sont nécessaires.

```bash
mvn clean install
mvn spring-boot:run
```

Les tests démarrent le contexte Spring avec H2 en mémoire et vérifient la création des tables JPA, y compris la table de jointure `vehicule_equipement`. Le profil normal reste configuré pour MySQL.

## Choix documentés

- Les statuts de réservation et modes de paiement reprennent les valeurs représentées dans le diagramme UML.
- Les dates sans heure utilisent `LocalDate`, les tarifs et montants `BigDecimal`.
- Les côtés propriétaires sont `Vehicule` pour la jointure d'équipements, `Contrat` pour la réservation, et chaque côté `ManyToOne` pour sa clé étrangère.
- L'étude ne précise pas de devise, de contraintes de validation ou de règles de suppression supplémentaires ; elles ne sont donc pas inventées ici.

## GitHub

Repository : [`amine-garraoui/autoloc`](https://github.com/amine-garraoui/autoloc) (public, branche `main`).
