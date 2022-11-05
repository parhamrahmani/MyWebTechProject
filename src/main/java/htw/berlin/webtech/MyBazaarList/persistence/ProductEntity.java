package htw.berlin.webtech.MyBazaarList.persistence;

import javax.persistence.*;

@Entity(name = "products")
public class ProductEntity {
    protected ProductEntity() {}
    public ProductEntity(String productName, String brand, String category) {

        this.productName = productName;
        this.brand = brand;
        this.category = category;
    }

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

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
