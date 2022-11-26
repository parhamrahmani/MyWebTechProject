package htw.berlin.webtech.MyBazaarList.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "shoppingLists")
public class ShoppingListEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id" ,nullable = false)
    private long listId;

    @Column (name = "List_name")
    private String listName;

    @Column (name = "description")
    private String description;

    @JsonIgnore
    @OneToMany (mappedBy = "shoppingList" , fetch = FetchType.EAGER)
    private final List<ProductEntity> productEntityList = new ArrayList<>();

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }
    public ShoppingListEntity(String listName, String description) {
        this.listName = listName;
        this.description = description;
    }
    protected ShoppingListEntity() {}
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
