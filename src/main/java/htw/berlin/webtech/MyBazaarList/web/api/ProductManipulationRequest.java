package htw.berlin.webtech.MyBazaarList.web.api;

import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListEntity;

/**
 * @author Parham Rahmani 580200
 *
 * The ProductManipulationRequest class is used to represent a request to create or update a product. It has four fields:
 *
 * productName: a String representing the name of the product.
 * brand: a String representing the brand of the product.
 * category: a String representing the category of the product.
 * shoppingList: a ShoppingListEntity object representing the shopping list to which the product belongs.
 */

public class ProductManipulationRequest {
    private String productName;
    private String brand;
    private String category;
    private ShoppingListEntity shoppingList;
    public ProductManipulationRequest() {

    }

    public ProductManipulationRequest(String productName, String brand, String category, ShoppingListEntity shoppingList)
    {   this.productName = productName;
        this.brand = brand;
        this.category = category;
        this.shoppingList = shoppingList;
    }

    public ShoppingListEntity getShoppingList() {return shoppingList;}
    public void setShoppingList(ShoppingListEntity shoppingList) {this.shoppingList = shoppingList;}
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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