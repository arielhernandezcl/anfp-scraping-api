# ANFP Scraping API

## Descripción
Este proyecto es un scraper web que extrae estadísticas del Campeonato Chileno de fútbol desde la página oficial de la ANFP. Utiliza Spring Boot y JSoup para realizar el scraping y expone los datos a través de una API REST.

## Características
- Scraping de estadísticas del Campeonato Chileno
- API REST para acceder a los datos scrapeados
- Implementado con Spring Boot y JSoup

## Tecnologías utilizadas
- Java 21
- Spring Boot
- JSoup
- Maven

## Requisitos previos
- Java 17 o superior
- Maven 3.6 o superior

## Instalación
1. Clona el repositorio:
- git clone https://github.com/arielhernandezcl/anfp-scraping-api

2. Navega al directorio del proyecto:
- cd anfp-scraper

3. Compila el proyecto:
- mvn clean install

## Uso
1. Inicia la aplicación:
- mvn spring-boot:run

2. Accede a los datos a través de la API:
- GET http://localhost:8080/estadisticas/campeonato-itau

## Estructura del proyecto
- `AnfpScraping.java`: Servicio que realiza el scraping de datos.
- `AnfpController.java`: Controlador REST que expone los datos scrapeados.

## Contribuir
Las contribuciones son bienvenidas. Por favor, abre un issue primero para discutir qué te gustaría cambiar.

## Licencia
[MIT](https://choosealicense.com/licenses/mit/)

## Contacto
Si requieres algo mas avanzado lo podemos conversar
Ariel Hernandez - [@alounderx](https://twitter.com/alounderx) - hola@arielhernandez.net

Enlace del proyecto: [https://github.com/arielhernandezcl/anfp-scraping-api](https://github.com/arielhernandezcl/anfp-scraping-api)
