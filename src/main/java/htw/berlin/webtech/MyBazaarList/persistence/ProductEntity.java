package htw.berlin.webtech.MyBazaarList.persistence;

import javax.persistence.*;

@Entity(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;

    @Column(name= "product_name", nullable = false)
    private String productName;

    @Column(name= "brand")
    private String brand ;
    @Column(name = "category")
    @Enumerated(value = EnumType.STRING)
    private  Categories category;
    @ManyToOne(cascade = CascadeType.MERGE)
            @JoinColumn(name = "shoppingList_Id", referencedColumnName = "listId")
    private ShoppingListEntity shoppingList;

    public ProductEntity(String productName, String brand, Categories category) {
        this.productName = productName;
        this.brand = brand;
        this.category = category;}

    protected ProductEntity() {}

    public ShoppingListEntity getShoppingList() {
        return shoppingList;
    }
    public void setShoppingList(ShoppingListEntity shoppingList) {
        this.shoppingList = shoppingList;
    }
    public long getId() {
        return id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Categories getCategory() {
        return category;
    }
    public void setCategory(Categories category) {
        this.category = category;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
