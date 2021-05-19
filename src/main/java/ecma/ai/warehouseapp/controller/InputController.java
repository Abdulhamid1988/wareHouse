package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.InputProduct;
import ecma.ai.warehouseapp.model.InputDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.InputProductRepository;
import ecma.ai.warehouseapp.repository.InputRepository;
import ecma.ai.warehouseapp.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    InputRepository inputRepository;


    @PostMapping("/add")
    public ApiResponse addInput(@RequestBody InputDTO inputDTO){
        return inputService.addInput(inputDTO);
    }

    @GetMapping("/all")
    public ApiResponse getAllList(){
        return inputService.getAllList();
    }

    @PutMapping("/edit/{id}")
    public ApiResponse editInput(@PathVariable Integer id,@RequestBody InputDTO inputDTO){
        return inputService.editInput(id,inputDTO);
    }




    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return inputService.delete(id);
    }

}
