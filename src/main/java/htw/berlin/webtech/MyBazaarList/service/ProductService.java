package htw.berlin.webtech.MyBazaarList.service;
import htw.berlin.webtech.MyBazaarList.persistence.ProductEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ProductRepository;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductManipulationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll()
    {
        List<ProductEntity> products = productRepository.findAll();
        // transfer product entity into product
        return products.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }
    public Product postProduct(ProductManipulationRequest request)
    {
       var productEntity = new ProductEntity(request.getProductName(),request.getBrand(),request.getCategory());
       productEntity = productRepository.save(productEntity);

        return transformEntity(productEntity);
    }

    /**
     *  transforms a Product Entity into a Product with an id
     * @param productEntity the product entity soon to be transformed into a Product
     * @return the Product with id
     */
    private Product transformEntity(ProductEntity productEntity)
    {
      return  new Product(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getBrand(),
              productEntity.getCategory()
        );
    }

    /**
     * finds a product by id
     * @param id demanded product's id
     * @return the demanded Product if existent, else null
     */
    public Product findById(Long id) {
        var productEntity = productRepository.findById(id);
        return productEntity.map(this::transformEntity).orElse(null);
    }

    /**
     *  updates Products by id
     * @param id product id
     * @param request the information that has to be updated on the DB
     */
    public Product update(Long id, ProductManipulationRequest request) {
        var productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isEmpty()) return null;
     var productEntity = productEntityOptional.get();
     productEntity.setProductName(request.getProductName());
     productEntity.setBrand(request.getBrand());
     productEntity.setCategory(request.getCategory());
     productEntity = productRepository.save(productEntity);
        return transformEntity(productEntity);

    }

    /**
     * checks if a product is deleted by id
     * @param id the id of the to be deleted Product
     * @return false if the product with the input if is not found
     * true if it before deletion it is existing
     */
    public boolean deleteById(Long id) {
        if(!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
