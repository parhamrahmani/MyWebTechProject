package htw.berlin.webtech.MyBazaarList.web.api;

public class Product {
    private long id;
    private String productName;
    private String brand;
   /* private Category category;
    enum Category{

    }*/

    public Product(long id, String productName, String brand) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
    }

    public long getId() {
        return id;
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
