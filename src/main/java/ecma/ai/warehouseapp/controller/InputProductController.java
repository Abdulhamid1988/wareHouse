package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.InputProduct;
import ecma.ai.warehouseapp.model.InputProductDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Inputproduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping("/add")
    public ApiResponse addInputProduct(@RequestBody InputProductDTO inputProductDto) {
        return inputProductService.addInputProduct(inputProductDto);
    }

    @GetMapping
    public ApiResponse getInputProducts() {
        return inputProductService.getInputProducts();
    }

    @GetMapping("/getProduct/{id}")
    public InputProduct getInputProductById(@PathVariable Integer id) {
        return inputProductService.getInputProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteInputProduct(@PathVariable Integer id) {
        return inputProductService.deleteInputProduct(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse editInputProduct(@PathVariable Integer id, @RequestBody InputProductDTO inputProductDto) {
        return inputProductService.editInputProduct(id,inputProductDto);
    }

}
