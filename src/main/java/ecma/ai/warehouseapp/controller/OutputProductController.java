package ecma.ai.warehouseapp.controller;


import ecma.ai.warehouseapp.model.OutputProductDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.service.InputProductService;
import ecma.ai.warehouseapp.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;


    @GetMapping("/addOutputproduct")
    public ApiResponse getOutputProducts() {
        return outputProductService.getOutputProducts();
    }

    @PostMapping("/addOutputProduct")
    public ApiResponse add(@RequestBody OutputProductDTO outputProductDTO){
        return outputProductService.addOutputProduct(outputProductDTO);
    }


    @GetMapping("/byid/{id}")
    public ApiResponse getOutputProductById(@PathVariable Integer id) {
        return outputProductService.getOutputProductByProductId(id);
    }

    @PutMapping("/{id}")
    public ApiResponse editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDTO outputProductDto) {
        return outputProductService.editOutputProduct(id,outputProductDto);
    }

}
