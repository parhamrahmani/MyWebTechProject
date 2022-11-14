package htw.berlin.webtech.MyBazaarList.web.api;

import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListEntity;

import java.util.Arrays;
import java.util.List;


public class Product {
    private long id;
    private String productName;
    private String brand;
    String category;
    private ShoppingListEntity shoppingList;

    /**
     *
     * @param id the id of product
     * @param productName name of the product
     * @param brand brand of the product
     * @param category category of the product
     */
    public Product(long id, String productName, String brand, String category, ShoppingListEntity shoppingList) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.shoppingList = shoppingList;


    }

    public ShoppingListEntity getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingListEntity shoppingList) {
        this.shoppingList = shoppingList;
    }

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}