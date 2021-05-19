package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Category;
import ecma.ai.warehouseapp.model.CategoryDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse addCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setActive(categoryDTO.getActive()); ///
        if (categoryDTO.getParentCategoryId()!=null){
            Optional<Category> byId = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (!byId.isPresent()){
                return new ApiResponse("bunday id mavjud emas",false);
            }
            category.setParentCategory(byId.get());
        }

        categoryRepository.save(category);

        return new ApiResponse("saved successfully",true);
    }

    public ApiResponse getAll(){
        List<Category> all = categoryRepository.findAll();
        return new ApiResponse("Category List",true,all);
    }

    public ApiResponse getOne(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            Category category = byId.get();
            return new ApiResponse("category",true,category);
        }

        return new ApiResponse("category id not fount",false);
    }

    public ApiResponse editCategory(Integer id,CategoryDTO categoryDTO){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            Category category = byId.get();
            category.setName(categoryDTO.getName());
            category.setActive(categoryDTO.getActive()); //
            if(categoryDTO.getParentCategoryId()!=null){
                Optional<Category> parentCategoryId = categoryRepository.findById(categoryDTO.getParentCategoryId());
                if (parentCategoryId.isPresent()){
                    category.setParentCategory(parentCategoryId.get());
                    return new ApiResponse("edited successfully",true);
                }
            }
        }
        return new ApiResponse("Id not fount",false);
    }

    public ApiResponse deleteCategory(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            categoryRepository.deleteById(id);
            return new ApiResponse("Deleted successfully",true);
        }
        return new ApiResponse("id not fount please try again",false);
    }
}
