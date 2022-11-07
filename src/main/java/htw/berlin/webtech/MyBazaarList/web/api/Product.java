package htw.berlin.webtech.MyBazaarList.web.api;

import java.util.Arrays;
import java.util.List;


public class Product {
    private long id;
    private String productName;
    private String brand;
    List<String> categories = Arrays.asList(
            "Vegetables" , "Fruits" , "Bread/Bakery" , "Breakfast"
            ,"Dairy", "Canned/Jarred Goods" , "Dry/Baking Goods" ,
            "Frozen Foods", "Meat" , "Cleaning Goods"
            ,"Paper Goods" , "Personal Care" ,"Snacks",  "Other","Oil","Spices");
    String category;

    /**
     *
     * @param id the id of product
     * @param productName name of the product
     * @param brand brand of the product
     * @param category category of the product
     */
    public Product(long id, String productName, String brand, String category) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        if(this.categories.contains(category)) this.category = category;
        else this.category = "";

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
    public void addCategory(String category)
    {
        categories.add(category);
    }
}
