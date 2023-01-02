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

    @GetMapping(path=path)
    public ResponseEntity<List<ShoppingList>> fetchAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path = path+"/{id}")
    public ResponseEntity<ShoppingList> fetchById(@PathVariable Long id)
    {   var shoppingList = service.findById(id);
        return shoppingList!= null? ResponseEntity.ok(shoppingList) : ResponseEntity.notFound().build();
    }

    @GetMapping(path= path+"/getTheLatestList")
    public ResponseEntity<ShoppingList> fetchTheLatest()
    {
       int lastPosition = service.findAll().size()-1;
       var shoppingList = service.findAll().get(lastPosition);
       return shoppingList!= null? ResponseEntity.ok(shoppingList) : ResponseEntity.notFound().build();
       }
    @GetMapping(path= path+"/getTheLatestListId")
    public ResponseEntity<Long> fetchTheLatestId()
    {
        int lastPosition = service.findAll().size()-1;
        var shoppingListId = service.findAll().get(lastPosition).getListId();
        return new ResponseEntity<>(
                shoppingListId, HttpStatus.OK
        );
    }
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



    @PostMapping(path=path)
    public ResponseEntity<Void> post(@RequestBody ShoppingListManipulationRequest request)
            throws URISyntaxException
    {
        var list = service.postShoppingList (request);
        URI uri = new URI(path + list.getListId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = path + "/{id}")
    public ResponseEntity<ShoppingList> update(@PathVariable Long id, @RequestBody    ShoppingListManipulationRequest request)
    {
        var list = service.update(id,request);
        return list != null? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
    }


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
