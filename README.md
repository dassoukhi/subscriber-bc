
# Project

Subscriber-bc, une application qui offre des fonctionnalités de gestion complètes pour les abonnés, de la création à la mise à jour en passant par la résiliation, via une API REST

## Features

- Créer un abonné
Permet d'ajouter un nouvel abonné avec toutes les données personnelles requises.
Génère un identifiant unique pour l'abonné.
Informe si l'abonné existe déjà.
- Récupérer un abonné
Permet de rechercher un abonné en fonction de divers critères tels que l'identifiant, le nom, l'e-mail, etc.
- Résilier un abonné
Désactive un abonné spécifié sans le supprimer.
L'abonné ne peut plus accéder aux services, mais ses données restent enregistrées.
- Mettre à jour les données personnelles d'un abonné

Permet de mettre à jour les informations personnelles d'un abonné spécifié, telles que le nom, le prénom, l'e-mail, etc.

## Run Locally

Cloner le projet

```bash
  git clone https://github.com/dassoukhi/subscriber-bc.git
```

```bash
  cd subscriber-bc
```
    
```bash
  docker-compose up -d
```

Lien [Swagger-ui](http://localhost:8080/swagger-ui/index.html)
ou
 http://localhost:8080/swagger-ui/index.html
## Running Tests

Execution de tests avec maven

```bash
  cd my-project
```

```bash
  mvn clean install
```


## Documentation

**DTO (Data Transfer Object):** SubscriberDto

- Poour séparer la couche model(base de données) de la couche controller.
- Masquer les attributs qui ne sont pas nécessaires pour une requête donnée. Ce qui permet de diminuer la taille du chargement des données.

**Résilier =>** avec mail en Paramètre au lieu de l'ID pour plus de cohérence