package htw.berlin.webtech.MyBazaarList.web;

import htw.berlin.webtech.MyBazaarList.service.ProductService;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductPostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path="/api/v1/products")
    //@ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Product>> fetchProducts() {
        return ResponseEntity.ok(productService.findAll());
    }
   @PostMapping(path="/api/v1/products")
    public ResponseEntity<Void> postProduct(@RequestBody ProductPostRequest request)
           throws URISyntaxException
    {
       var product = productService.postProduct (request);
       URI uri = new URI("/api/v1/products" + product.getId());
       return ResponseEntity.created(uri).build();
    }

}
