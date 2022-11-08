package htw.berlin.webtech.MyBazaarList.web.api;

public class ShoppingList {
    private long listId;
    private String listName;
    private String description;

    public ShoppingList(long listId, String listName, String description) {
        this.listId = listId;
        if(listName.isEmpty() || listName.equals(" ")) this.listName = "Unnamed Shopping List " + listId;
        else this.listName = listName;
        this.description = description;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
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
