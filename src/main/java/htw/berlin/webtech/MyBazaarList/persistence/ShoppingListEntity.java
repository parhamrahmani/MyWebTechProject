package htw.berlin.webtech.MyBazaarList.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingListEntity {
    protected ShoppingListEntity() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "List Id" ,nullable = false)
    private Long listId;
    @Column(name="List Name", nullable = false)
    private String listName;

    @Column(name="Description")
    private String description;
    @OneToMany(mappedBy = "shoppingList", fetch = FetchType.EAGER)
    List<ProductEntity> productsInList = new ArrayList<>();

    public ShoppingListEntity(String listName, String description) {
        this.listName = listName;
        this.description = description;
    }

    public Long getListId() {
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

    public List<ProductEntity> getProductsInList() {
        return productsInList;
    }

    public void setProductsInList(List<ProductEntity> productsInList) {
        this.productsInList = productsInList;
    }
}
