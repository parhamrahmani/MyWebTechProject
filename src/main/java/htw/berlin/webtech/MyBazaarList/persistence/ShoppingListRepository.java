package htw.berlin.webtech.MyBazaarList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
/**
        * The ShoppingListRepository interface is a repository for storing ShoppingListEntity objects in a database.
        * It extends the JpaRepository interface and provides methods for interacting with the database.
        *
        * @author Parham Rahmani 580200
        *
        */
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {}
