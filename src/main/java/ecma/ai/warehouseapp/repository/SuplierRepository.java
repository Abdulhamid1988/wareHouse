package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuplierRepository extends JpaRepository<Supplier,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
