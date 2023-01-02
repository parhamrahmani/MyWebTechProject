package htw.berlin.webtech.MyBazaarList.web;

import htw.berlin.webtech.MyBazaarList.service.ProductService;
import htw.berlin.webtech.MyBazaarList.service.ShoppingListService;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingList;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingListManipulationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.event.PaintEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Parham Rahmani 580200
 *
 * This is a Java Spring REST controller class that exposes a set of endpoints for performing CRUD
 * (create, read, update, delete) operations on shopping lists.
 *
 * The ShoppingListService and ProductService classes are dependencies injected into the controller
 * via its constructor. These dependencies are used to perform the actual CRUD operations on shopping lists
 * and products, respectively.
 */
@RestController
public class ShoppingListRestController {
    private final ShoppingListService service;
    private final ProductService productService;
    private final String path = "/api/v1/shoppingLists";
    private List<ShoppingList> shoppingLists= new ArrayList<>();

    public ShoppingListRestController(ShoppingListService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    /***
     * This method handles the GET /api/v1/shoppingLists endpoint and returns a list of all shopping lists.
     * @return the response
     */
    @GetMapping(path=path)
    public ResponseEntity<List<ShoppingList>> fetchAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /***
     * This method handles the GET /api/v1/shoppingLists/{id} endpoint and returns the shopping list with the given id.
     * If no shopping list with the given id is found, it returns a 404 Not Found response.
     * @param id the id of shopping list to be found
     * @return the response
     */
    @GetMapping(path = path+"/{id}")
    public ResponseEntity<ShoppingList> fetchById(@PathVariable Long id)
    {   var shoppingList = service.findById(id);
        return shoppingList!= null? ResponseEntity.ok(shoppingList) : ResponseEntity.notFound().build();
    }

    /***
     * This method handles the GET /api/v1/shoppingLists/getTheLatestList endpoint and returns the latest shopping list.
     * The latest shopping list is determined by finding the shopping list with the highest ID among all shopping lists.
     * If no shopping lists are found, it returns a 404 Not Found response.
     * @return the response
     */
    @GetMapping(path= path+"/getTheLatestList")
    public ResponseEntity<ShoppingList> fetchTheLatest()
    {
       int lastPosition = service.findAll().size()-1;
       var shoppingList = service.findAll().get(lastPosition);
       return shoppingList!= null? ResponseEntity.ok(shoppingList) : ResponseEntity.notFound().build();
       }

    /***
     * This method handles the GET /api/v1/shoppingLists/getTheLatestList endpoint and returns the latest shopping list.
     * The latest shopping list is determined by finding the shopping list with the highest ID among all shopping lists.
     * If no shopping lists are found, it returns a 404 Not Found response.
     * @return the response
     */
    @GetMapping(path= path+"/getTheLatestListId")
    public ResponseEntity<Long> fetchTheLatestId()
    {
        int lastPosition = service.findAll().size()-1;
        var shoppingListId = service.findAll().get(lastPosition).getListId();
        return new ResponseEntity<>(
                shoppingListId, HttpStatus.OK
        );
    }

    /***
     * This method handles the GET /api/v1/shoppingLists/getProductsByShoppingListId/{id} endpoint and returns a list
     * of products that belong to the shopping list with the given id. The method first fetches the shopping list with
     * the given id and then retrieves the list of product IDs associated with that shopping list. It then looks up each
     * product by its ID and adds it to the list of products to be returned. If no shopping list with the given id is
     * found, it returns a 404 Not Found response.
     * @param id the id of the shopping list that its products should be extracted
     * @return the response and productList
     */
    @GetMapping(path = "/api/v1/shoppingLists/getProductsByShoppingListId"+"/{id}")
    public ResponseEntity<List<Product>> fetchProductsByShoppingListId(@PathVariable Long id){
        ShoppingList fetchedShoppingList = service.findById(id);
        List<Long> productIdsFromFetchedShoppingList = fetchedShoppingList.getProductIds();
        List<Product> productList = new ArrayList<>();
        for (Long productId : productIdsFromFetchedShoppingList) productList.add(productService.findById(productId));
return new ResponseEntity<>(
        productList, HttpStatus.OK
);
    }


    /***
     * This method handles the POST /api/v1/shoppingLists endpoint and creates a new shopping list with the information
     * provided in the request body. It returns a 201 Created response with the location of the newly created
     * shopping list in the Location header.
     * @param request the info that will be posted
     * @return the response
     * @throws URISyntaxException throws an exception when something is wrong with uri
     */
    @PostMapping(path=path)
    public ResponseEntity<Void> post(@RequestBody ShoppingListManipulationRequest request)
            throws URISyntaxException
    {
        var list = service.postShoppingList (request);
        URI uri = new URI(path + list.getListId());
        return ResponseEntity.created(uri).build();
    }

    /***
     * This method handles the PUT /api/v1/shoppingLists/{id} endpoint and updates an existing shopping list with the
     * given id with the information provided in the request body. If no shopping list with the given id is found, it
     * returns a 404 Not Found response.
     * @param id the id of the list that will be updated
     * @param request the info that should be updated
     * @return the response
     */
    @PutMapping(path = path + "/{id}")
    public ResponseEntity<ShoppingList> update(@PathVariable Long id, @RequestBody ShoppingListManipulationRequest request)
    {
        var list = service.update(id,request);
        return list != null? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
    }

    /***
     * This method handles the DELETE /api/v1/shoppingLists/{id} endpoint and attempts to delete the shopping list
     * with the given id. Before attempting to delete the shopping list, the method first checks if the shopping list
     * has any associated products. If it does, the method first deletes all the products before attempting
     * to delete the shopping list itself. If the shopping list has no associated products or all of
     * the associated products have been successfully deleted, the method then attempts to delete the shopping list.
     * If the shopping list with the given id is not found, the method returns a 404 Not Found response.
     * If the shopping list has associated products that could not be deleted, the method returns a 409 Conflict response.
     * If the shopping list and all of its associated products are successfully deleted, the method returns
     * a 204 No Content response.
     * @param id the id of the shopping list to be deleted
     * @return the response
     */
    @DeleteMapping(path = path + "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {    ShoppingList fetchedShoppingList = service.findById(id);
        List<Long> productIdsFromFetchedShoppingList = fetchedShoppingList.getProductIds();
        for(Long productId : productIdsFromFetchedShoppingList) productService.deleteById(productId);
        if(productIdsFromFetchedShoppingList.isEmpty()) {
        boolean deleteSuccessful = service.deleteById(id);
        return deleteSuccessful? ResponseEntity.ok().build() : ResponseEntity.noContent().build(); }
        else return ResponseEntity.noContent().build();
    }
}
