package htw.berlin.webtech.MyBazaarList.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
 List<ProductEntity> findAllById(Long id);
 List<ProductEntity> findAllByProductName(String productName);
 List<ProductEntity> findAllByBrand(String brand);
}
