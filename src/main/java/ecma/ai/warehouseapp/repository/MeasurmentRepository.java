package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurmentRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);
}
