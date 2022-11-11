package htw.berlin.webtech.MyBazaarList.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity(name = "products")
public class ProductEntity {

    protected ProductEntity() {}



    public ProductEntity(String productName, String brand, Category category) {

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
    @Enumerated(value = EnumType.STRING)
    private  Category category;




    public long getId() {
        return id;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}