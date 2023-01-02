package htw.berlin.webtech.MyBazaarList.web.api;

/**
 * @author Parham Rahmani 580200
 * The ShoppingListManipulationRequest class is a Java bean class that represents a request for manipulation
 * of a shopping list, such as creating or updating a shopping list. It has two fields: listName and description,
 * which correspond to the name and description of the shopping list. The class has getters and setters for these
 * fields, as well as a default constructor and a constructor that initializes the fields with the given values.
 * This class is used as a request body in HTTP requests to create or update shopping lists,
 * and it allows the client to specify the name and description of the shopping list they want to create or update.
 */

public class ShoppingListManipulationRequest {
    private String listName;
    private String description;

    public ShoppingListManipulationRequest(String listName, String description) {
        this.listName = listName;
        this.description = description;
    }
    public ShoppingListManipulationRequest() {}
    public String getListName() {return listName;}
    public void setListName(String listName) {this.listName = listName;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
