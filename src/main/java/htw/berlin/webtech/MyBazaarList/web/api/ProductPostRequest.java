package htw.berlin.webtech.MyBazaarList.web.api;

public class ProductPostRequest {
    private String productName;
    private String brand;
    private String category;

    public ProductPostRequest(String productName,String brand,String category)
    {
        this.productName = productName;
        this.brand = brand;
        this.category = category;
    }

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
