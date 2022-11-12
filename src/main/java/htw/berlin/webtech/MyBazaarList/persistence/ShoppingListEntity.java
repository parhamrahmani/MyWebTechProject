package htw.berlin.webtech.MyBazaarList.persistence;

import javax.persistence.*;

@Entity (name = "shoppingLists")
public class ShoppingListEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "list id" ,nullable = false)
    private long listId;
    @Column (name = "List Name")
    private String listName;
    @Column (name = "description")
    private String description;

    public ShoppingListEntity(String listName, String description) {
        this.listName = listName;
        this.description = description;
    }

    protected ShoppingListEntity() {}

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
