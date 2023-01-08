# MyWebTechProject
------------------------------------------------------------------------------------------------------------------------
Parham Rahmani 580200
------------------------------------------------------------------------------------------------------------------------
English
------------------------------------------------------------------------------------------------------------------------
This is the backend to my project at htw berlin in the module "web technologies" with Mr. Arif Wider.

The topic of the project is a shopping list app that helps the user to organize a shopping list
with different categories like vegetables, fruits, breakfast, etc. The categorization
helps the user to think organized and not forget any product before shopping.

**Project Structure**

This project is divided into three main packages:

htw.berlin.webtech.MyBazaarList.persistence:
This package contains the JPA entity classes for shopping lists and products, as well as a repository interface for 
each entity. The entity classes are used to represent shopping lists and products in the database, and the repository
interfaces are used to perform CRUD operations on these entities.

htw.berlin.webtech.MyBazaarList.service:
This package contains the service classes for shopping lists and products. These classes provide the business logic
for the application and use the repository interfaces to interact with the database.

htw.berlin.webtech.MyBazaarList.web:
This package contains the REST controller classes for shopping lists and products. These classes expose a set of 
endpoints that can be called to perform various operations on shopping lists and products.

**Prerequisites**
To build and run this project, you will need the following:

Java 8 or above
Gradle 6.4 or above

**Endpoints**

The following endpoints are available for shopping lists:

GET /api/v1/shoppingLists: Returns a list of all shopping lists

GET /api/v1/shoppingLists/{id}: Returns the shopping list with the given id

POST /api/v1/shoppingLists: Creates a new shopping list with the information provided in the request body

PUT /api/v1/shoppingLists/{id}: Updates the shopping list with the given id with the information provided in the request
body

DELETE /api/v1/shoppingLists/{id}: Deletes the shopping list with the given id


The following endpoints are available for products:

GET /api/v1/products: fetches a list of all products

GET /api/v1/products/{id}: fetches the product with the given id

GET /api/v1/products/findProductsInLatestShoppingListId: fetches a list of products that belong to the latest
shopping list

POST /api/v1/products: creates a new product with the information provided in the request body

PUT /api/v1/products/{id}: updates the product with the given id with the information provided in the request body

DELETE /api/v1/products/{id}: deletes the product with the given id

In order to fully run the project with frontend you will also need the code to the frontend which is located in this 
GitHub repository:

_https://github.com/parhamrahmani/bazaar-list-frontend_



------------------------------------------------------------------------------------------------------------------------
Deutsch
------------------------------------------------------------------------------------------------------------------------
Dies ist das Backend zu meinem Projekt an der htw berlin im Modul "Webtechnologien" bei Herrn Arif Wider.

Das Thema des Projekts ist eine Einkaufslisten-App, die dem Benutzer hilft, eine Einkaufsliste mit verschiedenen 
Kategorien wie Gemüse, Obst, Frühstück, etc. zu organisieren. Die Kategorisierung hilft dem Benutzer, organisiert 
zu denken und kein Produkt vor dem Einkauf zu vergessen.

**Projektstruktur**

Dieses Projekt ist in drei Hauptpakete unterteilt:

htw.berlin.webtech.MyBazaarList.persistence:
Dieses Paket enthält die JPA-Entitätsklassen für Einkaufslisten und Produkte, sowie eine Repository-Schnittstelle 
für jede Entität. Die Entitätsklassen werden verwendet, um Einkaufslisten und Produkte in der Datenbank darzustellen, 
und die Repository-Schnittstellen werden verwendet, um CRUD-Operationen auf diesen Entitäten durchzuführen.

htw.berlin.webtech.MyBazaarList.service:
Dieses Paket enthält die Serviceklassen für Einkaufslisten und Produkte. Diese Klassen stellen die Geschäftslogik
für die Anwendung bereit und verwenden die Repository-Schnittstellen zur Interaktion mit der Datenbank.

htw.berlin.webtech.MyBazaarList.web:
Dieses Paket enthält die REST-Controller-Klassen für Einkaufslisten und Produkte. Diese Klassen stellen eine Reihe 
von Endpunkten zur Verfügung, die aufgerufen werden können, um verschiedene Operationen mit Einkaufslisten und Produkten
durchzuführen.

**Voraussetzungen**
Um dieses Projekt zu erstellen und auszuführen, benötigen Sie Folgendes:

Java 8 oder höher
Gradle 6.4 oder höher

**Endpunkte**

Die folgenden Endpunkte sind für Einkaufslisten verfügbar:

GET /api/v1/shoppingLists: Liefert eine Liste mit allen Einkaufslisten

GET /api/v1/shoppingLists/{id}: Liefert die Einkaufsliste mit der angegebenen ID

POST /api/v1/shoppingLists: Erzeugt eine neue Einkaufsliste mit den im Anfragetext angegebenen Informationen

PUT /api/v1/shoppingLists/{id}: Aktualisiert die Einkaufsliste mit der angegebenen ID mit den in der Anfrage angegebenen
Informationen 

DELETE /api/v1/shoppingLists/{id}: Löscht die Einkaufsliste mit der angegebenen ID


Die folgenden Endpunkte sind für Produkte verfügbar:

GET /api/v1/products: holt eine Liste aller Produkte

GET /api/v1/products/{id}: ruft das Produkt mit der angegebenen ID ab

GET /api/v1/products/findProductsInLatestShoppingListId: holt eine Liste der Produkte, die zur letzten
Einkaufsliste gehören

POST /api/v1/products: erstellt ein neues Produkt mit den im Anfragetext angegebenen Informationen

PUT /api/v1/products/{id}: aktualisiert das Produkt mit der angegebenen ID mit den im Anfragetext angegebenen Informationen

DELETE /api/v1/products/{id}: löscht das Produkt mit der angegebenen ID

Um das Projekt vollständig mit dem Frontend zu betreiben, benötigen Sie auch den Code für das Frontend, 
der sich in diesem  GitHub-Repository befindet:

_https://github.com/parhamrahmani/bazaar-list-frontend_

------------------------------------------------------------------------------------------------------------------------
