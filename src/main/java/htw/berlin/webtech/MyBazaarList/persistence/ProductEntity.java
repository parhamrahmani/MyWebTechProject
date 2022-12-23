package htw.berlin.webtech.MyBazaarList.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "products")
public class ProductEntity {
    protected ProductEntity() {}

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductEntity(String productName, String brand, String category , ShoppingListEntity shoppingList) {

        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.shoppingList = shoppingList;
    }

    public ShoppingListEntity getShoppingList() {return shoppingList;}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;

    @Column(name= "product_name", nullable = false)
    private String productName;

    @Column(name= "brand")
    private String brand ;

    @Column(name = "category")
    private  String category;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = ShoppingListEntity.class)
    private ShoppingListEntity shoppingList;

    public long getId() {
        return id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getCategory() {
        return category;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
}