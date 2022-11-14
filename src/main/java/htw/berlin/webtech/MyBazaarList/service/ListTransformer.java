package htw.berlin.webtech.MyBazaarList.service;

import htw.berlin.webtech.MyBazaarList.persistence.ProductEntity;
import htw.berlin.webtech.MyBazaarList.persistence.ShoppingListEntity;
import htw.berlin.webtech.MyBazaarList.web.api.ShoppingList;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ListTransformer {
    public ShoppingList listTransformer(ShoppingListEntity shoppingListEntity)
    {
        var productIds = shoppingListEntity.getProductEntityList().stream().map(ProductEntity::getId).collect(Collectors.toList());
        return new ShoppingList(
                shoppingListEntity.getListId(),
                shoppingListEntity.getListName(),
                shoppingListEntity.getDescription(),
                productIds
        );
    }
}
