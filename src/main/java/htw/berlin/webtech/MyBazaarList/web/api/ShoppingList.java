package htw.berlin.webtech.MyBazaarList.web.api;

public class ShoppingList {
    private long listId;
    private String listName;
    private String description;

    public ShoppingList(long listId, String listName, String description) {
        this.listId = listId;
        this.listName = listName;
        this.description = description;
    }

    public long getListId() {
        return listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
