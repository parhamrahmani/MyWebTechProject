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


/***
 * @author Parham Rahmani 580200
 *
 * This is a Java Spring REST controller class that exposes a set of endpoints for performing CRUD
 * (create, read, update, delete) operations on products.
 *
 * The ProductService and ShoppingListService classes are dependencies injected into the controller
 * via its constructor. These dependencies are used to perform the actual CRUD operations on products
 * and shopping lists, respectively.
 */

@RestController
public class ProductRestController {
    private final ProductService productService;
    private final ShoppingListService service;

    private final String path = "/api/v1/products";
    public ProductRestController(ProductService productService, ShoppingListService service) {
        this.productService = productService;
        this.service = service;
    }

    /***
     * This method handles the GET /api/v1/products endpoint and returns a list of all products.
     * @return a response that if the all products are fetched
     */
    @GetMapping(path=path)
    public ResponseEntity<List<Product>> fetchProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    /***
     * This method handles the GET /api/v1/products/{id} endpoint and returns the product with the given id.
     * If no product with the given id is found, it returns a 404 Not Found response.
     * @param id the id of product
     * @return the response
     */
    @GetMapping(path = path+"/{id}")
    public ResponseEntity<Product> fetchProductById(@PathVariable Long id)
    {   var product = productService.findById(id);
        return product!= null? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    /**
     * This method handles the GET /api/v1/products/findProductsInLatestShoppingListId endpoint and returns
     * a list of products that belong to the latest shopping list. The latest shopping list is determined by
     * finding the shopping list with the highest ID among all shopping lists. The returned list includes all
     * products that have a reference to this shopping list. If no products are found in the latest shopping list,
     * an empty list is returned.
     * @return the response
     */
    @GetMapping(path+"/findProductsInLatestShoppingListId")
    public ResponseEntity<List<Product>> fetchProductsByLatestShoppingList()
    {   int lastPosition = service.findAll().size()-1;
        var shoppingListId = service.findAll().get(lastPosition).getListId();
        List<Product> allProducts = this.productService.findAll();
        List<Product> productInThatList = new ArrayList<>();
        for (Product product : allProducts) {
            if(product.getShoppingList().getListId() == shoppingListId) productInThatList.add(product);
        }
        return ResponseEntity.ok(productInThatList);
}

    /***
     * This method handles the POST /api/v1/products endpoint and creates a new product with the information provided
     * in the request body. It returns a 201 Created response with the location of the newly created product
     * in the Location header.
     * @param request it has the info that will be posted
     * @return the response
     * @throws URISyntaxException throws an exception when something is wrong with uri
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
     * This method handles the PUT /api/v1/products/{id} endpoint and updates an existing product with the given id
     * with the information provided in the request body. If no product with the given id is found, it returns a 404
     * Not Found response.
     * @param id id of the product to be updated
     * @param request the info that has to be updated
     * @return the response
     */

    @PutMapping(path = path + "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody ProductManipulationRequest request)
    {
        var product = productService.update(id,request);
        return product != null? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    /**
     * This method handles the DELETE /api/v1/products/{id} endpoint and deletes the product with the given id.
     * If no product with the given id is found, it returns a 404 Not Found response.
     * @param id the id of the product to be deleted
     * @return the response
     */
    @DeleteMapping(path = path + "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        boolean deleteSuccessful = productService.deleteById(id);
        return deleteSuccessful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
