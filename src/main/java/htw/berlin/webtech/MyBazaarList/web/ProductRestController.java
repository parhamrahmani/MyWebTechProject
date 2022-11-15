package htw.berlin.webtech.MyBazaarList.web;

import htw.berlin.webtech.MyBazaarList.service.ProductService;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProductRestController {
    private final ProductService productService;
    private final String path = "/api/v1/products";
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path=path)
    public ResponseEntity<List<Product>> fetchProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(path = path+"/{id}")
    public ResponseEntity<Product> fetchProductById(@PathVariable Long id)
    {   var product = productService.findById(id);
        return product!= null? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping(path=path)
    public ResponseEntity<Void> postProduct(@RequestBody ProductManipulationRequest request)
            throws URISyntaxException
    {
        var product = productService.postProduct (request);
        URI uri = new URI("/api/v1/products" + product.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = path + "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody ProductManipulationRequest request)
    {
        var product = productService.update(id,request);
        return product != null? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = path + "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        boolean deleteSuccessful = productService.deleteById(id);
        return deleteSuccessful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
