# TechFlow - Gestion des Interventions Techniques

## 📌 Présentation
_**TechFlow** est une application web permettant aux entreprises de maintenance industrielle de gérer efficacement leurs interventions techniques. Cette plateforme centralise la gestion des interventions, des équipements et des utilisateurs pour une meilleure organisation et un suivi optimisé._

_Nous nous trouvons actuellement dans la backend partie API, le frontend est accessible ici(en attente du développement frontend)._

## 🚀 Fonctionnalités principales
### 🛠 Gestion des interventions
- Création, modification et suppression des interventions
- Attribution des interventions aux techniciens
- Suivi des statuts : en cours, terminée, annulée
- Ajout de commentaires et pièces jointes

### 🔧 Gestion des équipements
- Enregistrement des équipements par site client
- Historique des interventions associées à chaque équipement
- Ajout de documents techniques et notices

### 👤 Gestion des utilisateurs
- Rôles : Administrateurs, Techniciens, Clients
- Authentification par email/mot de passe
- Gestion des permissions et accès

### 📊 Tableau de bord et reporting
- Vue globale des interventions
- Statistiques détaillées sur les interventions
- Export des rapports en PDF et Excel


### 🔔 Notifications
- Alertes email et SMS pour les nouvelles interventions
- Rappels automatiques pour les interventions en retard

## 🏗 Technologies utilisées
Composant	Technologie
Frontend	Vue.js / React.js
Backend	Spring Boot
Base de données	PostgreSQL
Authentification	JWT / OAuth
Hébergement	AWS / DigitalOcean

## ⚙️ Installation et configuration
### 1️⃣ Prérequis
- Java 17+ et Maven
- PostgreSQL installé et configuré

### 2️⃣ Cloner le dépôt

```bash
git clone https://github.com/armanceau/taskmanager.git
cd taskmanager
```

### 3️⃣ Installation des dépendances
__Backend__
```
mvn clean install
```

### 5️⃣ Lancer l’application
__Démarrer le backend__
```
bash
mvn spring-boot:run
```

_L’application sera accessible sur http://localhost:8080._

## API Reference

### 01- Utilisateur

#### S'inscrire

```http
  POST /localhost:8080/auth/signup
```

| Paramètres | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fullName`      | `string` | **`Required`** Nom de l'utilisateur |
| `email`      | `string` | **`Required`** **`Unique`** Courriel de l'utilisateur |
| `password`      | `string` | **`Required`** Mot de passe de l'utilisateur |

#### Se connecter

```http
  POST /localhost:8080/auth/login
```

| Paramètres | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`      | `string` | **`Required`** **`Unique`** Courriel de l'utilisateur |
| `password`      | `string` | **`Required`** Mot de passe de l'utilisateur |
