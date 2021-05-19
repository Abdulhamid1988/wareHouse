package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product existsByNameAndCategoryId(String name,Integer categoryId);

}
