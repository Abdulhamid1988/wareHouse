package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.model.CategoryDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.CategoryRepository;
import ecma.ai.warehouseapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;


    @PostMapping("/add")
    public ApiResponse addCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.addCategory(categoryDTO);
    }

    @GetMapping("/all")
    public ApiResponse getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return categoryService.getOne(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody CategoryDTO categoryDTO){
        return categoryService.editCategory(id,categoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }
}
