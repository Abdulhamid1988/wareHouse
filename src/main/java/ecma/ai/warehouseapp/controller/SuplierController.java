package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.Supplier;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.SuplierRepository;
import ecma.ai.warehouseapp.service.SuplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SuplierController {


    @Autowired
    SuplierRepository suplierRepository;

    @Autowired
    SuplierService suplierService;


    @PostMapping("/addSupplier")
    public ApiResponse addSUpplier(@RequestBody Supplier supplier){
        return suplierService.addSupplier(supplier);
    }

    @GetMapping("/supplier/all")
    public ApiResponse getAll_List(){
        List<Supplier> all = suplierRepository.findAll();
        return new ApiResponse("Taminlovchilar ro'yhati",true,all);
    }

    @PutMapping("/edit/Supplier/{id}")
    public ApiResponse editSupplier(@RequestBody Supplier supplier,@PathVariable Integer id){
        return suplierService.editSupplier(supplier,id);
    }

    @DeleteMapping("/delete/supplier/{id}")
    public ApiResponse deleteSupplier(@PathVariable Integer id){
        return suplierService.deleteSupplier(id);
    }
}
