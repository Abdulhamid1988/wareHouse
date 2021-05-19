package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

}
