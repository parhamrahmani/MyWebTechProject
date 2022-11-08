package htw.berlin.webtech.MyBazaarList.web;

import htw.berlin.webtech.MyBazaarList.service.ShoppingListService;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingList;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingListManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ShoppingListRestController {
    private final ShoppingListService shoppingListService;
    private final String path = "/api/v1/lists";

    public ShoppingListRestController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping(path=path)
    public ResponseEntity<List<ShoppingList>> fetchShoppingLists() {
        return ResponseEntity.ok(shoppingListService.findAll());
    }

    @GetMapping(path = path+"/{listId}")
    public ResponseEntity<ShoppingList> fetchShoppingListById(@PathVariable Long listId)
    {   var shoppingList = shoppingListService.findById(listId);
        return shoppingList!= null? ResponseEntity.ok(shoppingList) : ResponseEntity.notFound().build();
    }


    @PostMapping(path=path)
    public ResponseEntity<Void> postShoppingList(@RequestBody ShoppingListManipulationRequest request)
            throws URISyntaxException
    {
        var list = shoppingListService.postProduct (request);
        URI uri = new URI("/api/v1/lists" + list.getListId());
        return ResponseEntity.created(uri).build();
    }


    @PutMapping(path = path + "/{listId}")
    public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable Long listId,@RequestBody ShoppingListManipulationRequest request)
    {
        var list = shoppingListService.update(listId,request);
        return list != null? ResponseEntity.ok(list) : ResponseEntity.notFound().build();
    }


    @DeleteMapping(path = path + "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        boolean deleteSuccessful = shoppingListService.deleteById(id);
        return deleteSuccessful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();


    }
}
