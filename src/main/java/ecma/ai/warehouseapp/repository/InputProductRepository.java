package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.Input;
import ecma.ai.warehouseapp.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
    @Query(value = "select * from input_product as ip join input i on i.id = ip.input_id where i.date=:date", nativeQuery = true)
    List<InputProduct> findByDate(Date date);


    List<InputProduct> getByExpireDate(Date date);


}
