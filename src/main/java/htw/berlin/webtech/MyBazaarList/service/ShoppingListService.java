package htw.berlin.webtech.MyBazaarList.service;

import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListRepository;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingList;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingListManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public List<ShoppingList> findAll() {
      List<ShoppingListEntity> listOfShoppingLists = shoppingListRepository.findAll();
      return listOfShoppingLists.stream().map(this::transformEntity).collect(Collectors.toList());

    }
    private ShoppingList transformEntity(ShoppingListEntity shoppingListEntity) {
        return new ShoppingList(
                shoppingListEntity.getListId(),
                shoppingListEntity.getListName(),
                shoppingListEntity.getDescription()
        );
    }

    public ShoppingList findById(Long listId) {
        var shoppingListEntity = shoppingListRepository.findById(listId);
        return shoppingListEntity.map(this::transformEntity).orElse(null);
    }

    public ShoppingList postProduct(ShoppingListManipulationRequest request) {
        var shoppingListEntity = new ShoppingListEntity(request.getListName(),request.getDescription());
        shoppingListEntity = shoppingListRepository.save(shoppingListEntity);
        return transformEntity(shoppingListEntity);}

    public ShoppingList update(Long listId, ShoppingListManipulationRequest request) {
        var shoppingListEntityOptional = shoppingListRepository.findById(listId);
        if(shoppingListEntityOptional.isEmpty()) return null;
        var shoppingListEntity = shoppingListEntityOptional.get();
        shoppingListEntity.setListName(request.getListName());
        shoppingListEntity.setDescription(request.getDescription());
        return transformEntity(shoppingListEntity);
    }

    public boolean deleteById(Long listId) {
        if(!shoppingListRepository.existsById(listId)) return false;
        shoppingListRepository.deleteById(listId);
        return true;
    }
}
