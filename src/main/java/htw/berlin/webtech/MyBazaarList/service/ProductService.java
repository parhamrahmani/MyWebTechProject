package htw.berlin.webtech.MyBazaarList.service;

import htw.berlin.webtech.MyBazaarList.persistence.Category;
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
        var category = Category.valueOf(request.getCategory());
        var productEntity = new ProductEntity(request.getProductName(),request.getBrand(),category,request.getShoppingList());
        productEntity = productRepository.save(productEntity);

        return transformEntity(productEntity);
    }

    private Product transformEntity(ProductEntity productEntity)
    {
        var category = productEntity.getCategory() != null ? productEntity.getCategory().name() : Category.Other.name();

        return  new Product(
                productEntity.getId(),
                productEntity.getProductName(),
                productEntity.getBrand(),
                category,
                productEntity.getShoppingList()
        );
    }

    public Product findById(Long id) {
        var productEntity = productRepository.findById(id);
        return productEntity.map(this::transformEntity).orElse(null);
    }

    public Product update(Long id, ProductManipulationRequest request) {
        var category = Category.valueOf(request.getCategory());
        var productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isEmpty()) return null;
        var productEntity = productEntityOptional.get();
        productEntity.setProductName(request.getProductName());
        productEntity.setBrand(request.getBrand());
        productEntity.setCategory(category);
        productEntity = productRepository.save(productEntity);
        return transformEntity(productEntity);

    }

    public boolean deleteById(Long id) {
        if(!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}