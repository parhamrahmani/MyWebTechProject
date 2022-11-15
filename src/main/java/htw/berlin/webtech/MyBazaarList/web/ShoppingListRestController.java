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
    private final ShoppingListService service;
    private final String path = "/api/v1/shoppingLists";

    public ShoppingListRestController(ShoppingListService service) {
        this.service = service;
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
    {
        boolean deleteSuccessful = service.deleteById(id);
        return deleteSuccessful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
