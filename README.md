# Esse3-Bootleg Wiki

Welcome to the documentation for the Esse3-Bootleg MVC Web Application.

## Table of Contents

1. [Introduction](#1-introduction)
2. [Getting Started](#2-getting-started)
    1. [Prerequisites](#21-prerequisites)
    2. [Installation and deployment](#22-installation-and-deployment)
3. [Architecture Overview](#3-architecture-overview)
4. [License](#4-license)
    - [License Information](#license-information)

## 1. Introduction

This project aims to develop a web app designed for student career management and teacher course management and mark assignments for the course of Advanced Programming of Cryptographic Methods.

The application offers authentication, role-based access, and tailored dashboards. Role-based access distinguishes student, teacher, and administrator privileges. 
Students access a dashboard displaying enrolled and accessible courses with grades. Professors utilize a specialized dashboard for course management and grade assignments.
Administrators can add student and teacher accounts, and also courses.

The app is an MVC web app implemented following the J2EE design pattern. 
Data persistence is ensured by a PostgreSQL database that must ensure data at rest confidentiality.
The model communication with the database is mediated by Java Persistence API (JPA) and must guarantee data in motion confidentiality.
Also, client-server communication must be encrypted. TLS with self-signed certificates will be used.
Grades assignation operated by the teacher role must be signed.

## 2. Getting Started

### 2.1 Prerequisites

To run the application locally, you'll need to have the following software, tools, and dependencies installed:

1. **Docker:**
   - Version: 24.0.7
   - [Docker Installation Guide](https://docs.docker.com/get-docker/)
  
2. **Docker Compose:**
   - Version: 2.23.1
   - [Docker Compose Installation Guide](https://docs.docker.com/compose/install/)

### 2.2 Installation and deployment

Since the infrastructure is dockerized, deploying the application is straightforward. Follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone git@github.com:lallo-unitn/Esse3-Bootleg-APFCM.git
   cd Esse3-Bootleg-APFCM
2. **Up the docker infrastructure:**
   ```bash
   docker-compose up

This command initializes and starts the Docker containers based on the configurations in the docker-compose.yml file. The application, along with its dependencies, will be deployed.

Once the containers are up and running, you can access the application by navigating to [http://localhost:8080/adv_prog_project_war](http://localhost:8080/adv_prog_project_war) in your web browser.

1. **Down the Docker Infrastructure:**
   ```bash
   docker-compose down

## 3. Architecture Overview

The tech stack for this project is composed of the following components:

1. **Database:**
   - PostgreSQL v. 16.1

2. **Application Server:**
   - WildFly 30.0.0-Final

3. **Java Development Kit (JDK):**
   - OpenJDK 17

4. **Model (Persistence):**
   - Java Persistence API (JPA) with Hibernate

5. **Control:**
   - Servlets

6. **View:**
   - JavaServer Pages (JSP)


## 10. License

### License Information

## License

This project is licensed under the [GNU General Public License v3.0 (GPL-3.0)](https://opensource.org/licenses/GPL-3.0). See the [LICENSE](link-to-license-file) file for details.
