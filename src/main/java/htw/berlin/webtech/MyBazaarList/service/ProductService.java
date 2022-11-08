package htw.berlin.webtech.MyBazaarList.service;

import htw.berlin.webtech.MyBazaarList.persistence.ProductEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ProductRepository;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductManipulationRequest;
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

    private Product transformEntity(ProductEntity productEntity)
    {
      return  new Product(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getBrand(),
              productEntity.getCategory()
        );
    }

    public Product findById(Long id) {
        var productEntity = productRepository.findById(id);
        return productEntity.map(this::transformEntity).orElse(null);
    }

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

    public boolean deleteById(Long id) {
        if(!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
