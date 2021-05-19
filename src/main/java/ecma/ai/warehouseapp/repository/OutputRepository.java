package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository extends JpaRepository<Output,Integer> {

}
