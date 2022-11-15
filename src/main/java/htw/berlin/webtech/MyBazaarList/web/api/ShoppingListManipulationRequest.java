package htw.berlin.webtech.MyBazaarList.web.api;

public class ShoppingListManipulationRequest {
    private String listName;
    private String description;

    public ShoppingListManipulationRequest(String listName, String description) {
        this.listName = listName;
        this.description = description;
    }
    public String getListName() {return listName;}
    public void setListName(String listName) {this.listName = listName;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
