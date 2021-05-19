package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Attachment;
import ecma.ai.warehouseapp.entity.Category;
import ecma.ai.warehouseapp.entity.Measurement;
import ecma.ai.warehouseapp.entity.Product;
import ecma.ai.warehouseapp.model.ProductDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.AttachmentRepository;
import ecma.ai.warehouseapp.repository.CategoryRepository;
import ecma.ai.warehouseapp.repository.MeasurmentRepository;
import ecma.ai.warehouseapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    MeasurmentRepository measurmentRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public ApiResponse addProduct(ProductDTO productDTO){

        Optional<Attachment> photoId = attachmentRepository.findById(productDTO.getAttachment_id());
        Optional<Category> categoryRepositoryById = categoryRepository.findById(productDTO.getCategoryId());
        Optional<Measurement> measurmentRepositoryById = measurmentRepository.findById(productDTO.getMeasurement_id());
        Product product = new Product();
        product.setName(productDTO.getName());

        if (photoId.isPresent()){
            Attachment photo = photoId.get();
            product.setAttachment(photo);
        }

        if (categoryRepositoryById.isPresent()){

            Category category = categoryRepositoryById.get();
            if (category.getActive()){
                product.setCategory(category);
            }
            else {
                return new ApiResponse("bu kategory no active iltimos boshqa category tanlang",false);
            }

        }

        if (measurmentRepositoryById.isPresent()){
            Measurement measurement = measurmentRepositoryById.get();
            if (measurement.getActive()) {
                product.setMeasurement(measurement);
            }else {
                return new ApiResponse("measurment id faol emas iltimas boshqa o'lchov birligi tanlang",false);

            }
        }

        int code=productRepository.findAll().size()+1;
        product.setCode("Product"+code);
        productRepository.save(product);

        return new ApiResponse("added successfully",true);

    }

    public ApiResponse edit(Integer id,ProductDTO productDTO){
        Optional<Product> byId = productRepository.findById(id);
        Optional<Attachment> photoId = attachmentRepository.findById(productDTO.getAttachment_id());
        Optional<Category> categoryRepositoryById = categoryRepository.findById(productDTO.getCategoryId());
        Optional<Measurement> measurmentRepositoryById = measurmentRepository.findById(productDTO.getMeasurement_id());
        if (byId.isPresent()){
            Product product = byId.get();
            if (photoId.isPresent()){
                Attachment photo = photoId.get();
                product.setAttachment(photo);
            }

            if (categoryRepositoryById.isPresent()){
                Category category = categoryRepositoryById.get();
                if (category.getActive()){
                    product.setCategory(category);
                }
                else {
                    return new ApiResponse("bu kategory no active iltimos boshqa category tanlang",false);
                }
            }

            if (measurmentRepositoryById.isPresent()){
                Measurement measurement = measurmentRepositoryById.get();
                if (measurement.getActive()) {
                    product.setMeasurement(measurement);
                }
                return new ApiResponse("measurment id faol emas iltimas boshqa o'lchov birligi tanlang",false);

            }

            int code=productRepository.findAll().size()+1;
            product.setCode("Product"+code);
            productRepository.save(product);

            return new ApiResponse("edited successfully",true);

        }

        return new ApiResponse("SOmething went wrong",false);
    }


    public ApiResponse getProducts(){
        List<Product> all = productRepository.findAll();
        return new ApiResponse("allProductList",true,all);
    }

    public ApiResponse getProductById(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return new ApiResponse("productById", true, optionalProduct.get());
        }
        return new ApiResponse("id not fount",false);
    }

    public ApiResponse deleteProduct(Integer id){
        productRepository.deleteById(id);
        return new ApiResponse("Product deleted",true);
    }

}
