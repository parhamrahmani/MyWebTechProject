package htw.berlin.webtech.MyBazaarList.web;

import htw.berlin.webtech.MyBazaarList.service.ProductService;
import htw.berlin.webtech.MyBazaarList.service.ShoppingListService;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductRestController {
    private final ProductService productService;
    private final ShoppingListService service;

    private final String path = "/api/v1/products";
    public ProductRestController(ProductService productService, ShoppingListService service) {
        this.productService = productService;
        this.service = service;
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
    @GetMapping(path+"/findProductsInLatestShoppingListId")
    public ResponseEntity<List<Product>> fetchProductsByShoppingListId()
    {   int lastPosition = service.findAll().size()-1;
        var shoppingListId = service.findAll().get(lastPosition).getListId();
        List<Product> allProducts = this.productService.findAll();
        List<Product> productInThatList = new ArrayList<>();
        for (Product product : allProducts) {
            if(product.getShoppingList().getListId() == shoppingListId) productInThatList.add(product);
        }
        return ResponseEntity.ok(productInThatList);
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
