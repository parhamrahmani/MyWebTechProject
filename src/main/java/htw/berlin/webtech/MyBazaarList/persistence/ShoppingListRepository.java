package htw.berlin.webtech.MyBazaarList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {}
