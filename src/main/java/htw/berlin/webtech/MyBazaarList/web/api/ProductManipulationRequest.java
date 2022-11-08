package htw.berlin.webtech.MyBazaarList.web.api;

import htw.berlin.webtech.MyBazaarList.persistence.Categories;

public class ProductManipulationRequest {
    private String productName;
    private String brand;
    private Categories category;

    public ProductManipulationRequest(String productName, String brand, Categories category)
    {
        this.productName = productName;
        this.brand = brand;
        this.category = category;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
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
