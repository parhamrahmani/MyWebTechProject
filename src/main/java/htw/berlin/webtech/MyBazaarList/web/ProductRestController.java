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


    /**
     *  gets all the products
     * @return The HTTP Status 200 when the list of products are found
     */
    @GetMapping(path=path)
    public ResponseEntity<List<Product>> fetchProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    /**
     * gets the product with the parameter id
     * @param id the id of the product that we want to get
     * @return HTTP Status: 404 when the product entity is not found
     *         HTTP Status: 200 when the product entity is found
     */
   @GetMapping(path = path+"/{id}")
    public ResponseEntity<Product> fetchProductById(@PathVariable Long id)
   {   var product = productService.findById(id);
       return product!= null? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
   }

    /**
     * it posts/creates new products into the DB
     * @param request it contains the data of the product we want to post to our database without id, The id
     *                will be generated in DB.
     * @return HTTP Status: 201 when creation is successful
     * @throws URISyntaxException throws exception when URI is wrong
     */
   @PostMapping(path=path)
    public ResponseEntity<Void> postProduct(@RequestBody ProductManipulationRequest request)
           throws URISyntaxException
    {
       var product = productService.postProduct (request);
       URI uri = new URI("/api/v1/products" + product.getId());
       return ResponseEntity.created(uri).build();
    }

    /**
     *
     * @param id the id of the product to be updated
     * @param request the data that is going to be patched on DB, the new data on the
     *                existing product
     * @return the updated product
     */
    @PutMapping(path = path + "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody ProductManipulationRequest request)
    {
        var product = productService.update(id,request);
        return product != null? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id the id of the product to be deleted
     * @return HTTP Status 200 : product found and deleteD
     * http status 404 : product not found/ already deleted
     */
    @DeleteMapping(path = path + "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        boolean deleteSuccessful = productService.deleteById(id);
        return deleteSuccessful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();


    }

}
