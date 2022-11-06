package htw.berlin.webtech.MyBazaarList.web.api;

public class ProductManipulationRequest {
    private String productName;
    private String brand;
    private String category;

    /**
     *
     * @param productName the new name/existing name that is going to be changed/overwritten in the database
     * @param brand the new name/existing brand that is going to be changed/overwritten in the database
     * @param category the new name/existing category that is going to be changed/overwritten in the database
     */
    public ProductManipulationRequest(String productName, String brand, String category)
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
