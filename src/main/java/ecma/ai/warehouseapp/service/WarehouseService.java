package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Warehouse;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse addWarehouse(Warehouse warehouse) {
        Warehouse warehouse1 = new Warehouse();

        warehouse1.setActive(warehouse.getActive());
        if (warehouseRepository.existsByName(warehouse.getName())) {

            return new ApiResponse("Bunday nomli ombor mavjud!", false);
        }
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return new ApiResponse("Added!", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()) {
            warehouseRepository.deleteById(id);
            return new ApiResponse("deleted successfully", true);
        }
        return new ApiResponse("id not founnt", false);
    }

    public ApiResponse edit(Integer id, Warehouse warehouse) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()) {

            if (warehouseRepository.existsByName(warehouse.getName())) {

                return new ApiResponse("Bunday nomli ombor mavjud!", false);
            }
            Warehouse editingWareHouse = byId.get();
            if (!editingWareHouse.getActive()) {
                return new ApiResponse("bu warehouse noactive", false);
            }

                editingWareHouse.setName(warehouse.getName());
                editingWareHouse.setActive(warehouse.getActive());

                return new ApiResponse("edited successfully", true);
            }
        return new ApiResponse("id not fount", false);


        }

    }









