package ecma.ai.warehouseapp;

import ecma.ai.warehouseapp.entity.InputProduct;
import ecma.ai.warehouseapp.repository.InputProductRepository;
import ecma.ai.warehouseapp.repository.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WarehouseAppApplication {

    @Autowired
   static InputProductRepository inputProductRepository;

    @Autowired
    static InputRepository inputRepository;
    public static void main(String[] args) {
        SpringApplication.run(WarehouseAppApplication.class, args);



    }

}
