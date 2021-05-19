package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.model.ProductDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.AttachmentRepository;
import ecma.ai.warehouseapp.repository.CategoryRepository;
import ecma.ai.warehouseapp.repository.MeasurmentRepository;
import ecma.ai.warehouseapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ApiResponse addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse editProduct(@PathVariable Integer id,@RequestBody ProductDTO productDTO){
        return productService.edit(id,productDTO);
    }

    @GetMapping("/all")
    public ApiResponse allProductList(){
        return productService.getProducts();
    }

    @GetMapping("/getOneById/{id}")
    public ApiResponse getOneyId(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteBYId(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }

}
