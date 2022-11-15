package htw.berlin.webtech.MyBazaarList.web.api;

import java.util.List;

public class ShoppingList {
    private long listId;
    private String listName;
    private String description;
    private List<Long> productIds;

    public ShoppingList(long listId, String listName, String description, List<Long> productIds) {
        this.listId = listId;
        this.listName = listName;
        this.description = description;
        this.productIds = productIds;
    }
    public List<Long> getProductIds() {return productIds;}
    public void setProductIds(List<Long> productIds) {this.productIds = productIds;}
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
