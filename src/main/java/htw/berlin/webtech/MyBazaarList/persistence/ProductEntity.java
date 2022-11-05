package htw.berlin.webtech.MyBazaarList.persistence;

import javax.persistence.*;

@Entity(name = "products")
public class ProductEntity {
    protected ProductEntity() {}
    public ProductEntity(long id, String productName, String brand) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,nullable = false)
    private long id;

    @Column(name= "product_name", nullable = false)
    private String productName;

    @Column(name= "brand")
    private String brand ;



    public long getId() {
        return id;
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
