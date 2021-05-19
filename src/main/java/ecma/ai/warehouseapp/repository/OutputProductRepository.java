package ecma.ai.warehouseapp.repository;

import ecma.ai.warehouseapp.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {
    List<OutputProduct> findAllByProduct_Id(Integer id);


    List<OutputProduct> getByAmountDate(Date date);
}
