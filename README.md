# Microservices DDD - REST & JWT (HTTP Synchrone)

Ce projet fait partie d'une étude comparative d'architectures microservices et démontre l'implémentation des principes de **Domain-Driven Design (DDD)** et de l'**Architecture Hexagonale**, en utilisant **REST** pour la communication inter-services et **JWT** pour la sécurisation.

---

## 🏗️ Aperçu de l'Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                        CLIENT (Navigateur)                      │
└─────────────────────────┬───────────────────────────────────────┘
                          │
    ┌─────────────────────┴─────────────────────┐
    │                                           │
    ▼                                           ▼
┌───────────────────┐                   ┌───────────────────┐
│  product-service  │◄────  REST  ─────►│  client-service   │
│   (Fournisseur)   │                   │  (Consommateur)   │
│    Port: 8081     │                   │    Port: 8082     │
└───────────────────┘                   └───────────────────┘
         │                                       │
         ▼                                       │
   ┌──────────┐                                  │
   │  Bdd H2  │                                  │
   └──────────┘                                  │
         │                                       │
         └───────────── Appel REST ──────────────┘
```

---

## 🛠️ Stack Technique

| Composant | Technologie |
|-----------|------------|
| Langage | Java 17 |
| Framework | Spring Boot 3 |
| Communication | API REST (RestTemplate) |
| Sécurité | Spring Security + JWT (HMAC) |
| IHM | Thymeleaf (Design premium, Thème Sombre & Glassmorphism) |
| Base de Données | H2 (En mémoire) |
| Outil de Build | Maven |

---

## 📂 Structure du Projet

### `product-service` (Portail Fournisseur)
*   `domain/` : Règles métiers isolées.
    *   `model/` : Entités (`Product`, `Supplier`) et Objets de Valeur (`Price`, `Email`).
    *   `repository/` : Interfaces des ports de persistance.
*   `application/` : Cas d'utilisation orchestrant le domaine (`RegisterSupplierUseCase`, `LoginSupplierUseCase`, `CreateProductUseCase`).
*   `infrastructure/` : Implémentations techniques.
    *   `persistence/` : Adaptateurs JPA.
    *   `security/` : Configuration de Spring Security et génération des tokens JWT.
*   `interface_layer/` : Contrôleurs Web MVC et API REST pour exposer les produits.

### `client-service` (Galerie Client)
*   `domain/model/` : Projection de lecture des produits (`ProductView`).
*   `infrastructure/rest/` : Client REST (`ProductRestClient`) interrogeant le service produit.
*   `interface_layer/` : Affichage de la galerie via les templates Thymeleaf.

---

## 🔌 API REST (product-service)

| Méthode | Point d'accès (Endpoint) | Description | Authentification |
|--------|--------------------------|-------------|------------------|
| GET | `/api/products` | Récupère tous les produits publiés | Non |
| POST | `/api/products` | Crée et publie un produit | Oui (JWT requis) |

---

## 🚀 Démarrage Rapide

### Prérequis
*   JDK 17 ou supérieur
*   Maven 3.8+

### Étape 1 : Lancer le service Produit (product-service)
```bash
cd product-service
mvn spring-boot:run
```
*   Accès UI Fournisseur : [http://localhost:8081/login](http://localhost:8081/login)

### Étape 2 : Lancer le service Client (client-service)
```bash
cd client-service
mvn spring-boot:run
```
*   Accès UI Galerie Client : [http://localhost:8082/products](http://localhost:8082/products)
