package htw.berlin.webtech.MyBazaarList.service;

import htw.berlin.webtech.MyBazaarList.persistence.Category;
import htw.berlin.webtech.MyBazaarList.persistence.ProductEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListRepository;
import htw.berlin.webtech.MyBazaarList.web.api.Product;
import htw.berlin.webtech.MyBazaarList.web.api.ProductManipulationRequest;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingList;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingListManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    private final ListTransformer listTransformer;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, ListTransformer listTransformer) {
        this.shoppingListRepository = shoppingListRepository;
        this.listTransformer = listTransformer;
    }
  public ShoppingList  transformEntity(ShoppingListEntity shoppingListEntity)
  {
      //var category = shoppingListEntity.getListName()
      var productIds = shoppingListEntity.getProductEntityList().stream().map(ProductEntity::getId).collect(Collectors.toList());

      ShoppingList shoppingList = new ShoppingList(
              shoppingListEntity.getListId(),
              shoppingListEntity.getListName(),
              shoppingListEntity.getDescription(),
              productIds

      );
      return shoppingList;
  }
    public List<ShoppingList> findAll()
    {
        List<ShoppingListEntity> shoppingListEntities = shoppingListRepository.findAll();
        // transfer product entity into product
        return shoppingListEntities.stream().map(this::transformEntity)
                .collect(Collectors.toList());
    }
    public ShoppingList postShoppingList(ShoppingListManipulationRequest request)
    {var shoppingListEntity = new ShoppingListEntity(request.getListName(),request.getDescription());
        shoppingListEntity = shoppingListRepository.save(shoppingListEntity);
        return transformEntity(shoppingListEntity);
    }
    public ShoppingList findById(Long id) {
        var shoppingList = shoppingListRepository.findById(id);
        return shoppingList.map(this::transformEntity).orElse(null);
    }

    public ShoppingList update(Long id, ShoppingListManipulationRequest request) {
        var shopppingListOptional = shoppingListRepository.findById(id);
        if (shopppingListOptional.isEmpty()) return null;
        var shopppingListEntity = shopppingListOptional.get();
        shopppingListEntity.setListName(request.getListName());
        shopppingListEntity.setDescription((request.getDescription()));
        shopppingListEntity = shoppingListRepository.save(shopppingListEntity);
        return transformEntity(shopppingListEntity);

    }

    public boolean deleteById(Long id) {
        if(!shoppingListRepository.existsById(id)) return false;
        shoppingListRepository.deleteById(id);
        return true;
    }

}
