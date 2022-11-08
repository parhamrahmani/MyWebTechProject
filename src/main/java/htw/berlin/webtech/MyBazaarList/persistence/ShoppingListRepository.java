package htw.berlin.webtech.MyBazaarList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity,Long> {
    List<ShoppingListEntity> findByListId(Long listId);
    List<ShoppingListEntity> findByListName(String listName);
}
