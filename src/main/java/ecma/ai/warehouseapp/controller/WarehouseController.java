package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.Warehouse;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.WarehouseRepository;
import ecma.ai.warehouseapp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    WarehouseService warehouseService;



    @PostMapping("/add")
    public ApiResponse add(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping("/all")
    public ApiResponse getAll() {
        List<Warehouse> all = warehouseRepository.findAll();
        return new ApiResponse("Xamma omborlar ro'yxati!", true, all);
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable Integer id) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        return new ApiResponse("Ombor", true, byId.get());
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteWareHouse(@PathVariable Integer id){
       return warehouseService.delete(id);
    }

}
