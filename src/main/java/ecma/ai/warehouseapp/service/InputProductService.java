package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Input;
import ecma.ai.warehouseapp.entity.InputProduct;
import ecma.ai.warehouseapp.entity.Product;
import ecma.ai.warehouseapp.model.InputProductDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.InputProductRepository;
import ecma.ai.warehouseapp.repository.InputRepository;
import ecma.ai.warehouseapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;




    public ApiResponse addInputProduct(InputProductDTO inputProductDto){
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());

        if (optionalInput.isEmpty()){
            return new ApiResponse("Such input doesn't exist",false);
        }

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new ApiResponse("Such product doesn't exist",false);
        }
        InputProduct inputProduct=new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());

        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        if (optionalProduct.get().getActive()) {
            inputProduct.setProduct(optionalProduct.get());
        }else {
            return new ApiResponse("Bu product id active emas iltimos boshqa iddan foydalaning",false);
        }
        Optional<Input> byId = inputRepository.findById(inputProductDto.getInputId());
        if (byId.isPresent()){
            Input input = byId.get();
            inputProduct.setInput(input);
        }

        inputProductRepository.save(inputProduct);
        return new ApiResponse("Input Product added",true);
    }

    public ApiResponse getInputProducts(){
        List<InputProduct> inputProductList = inputProductRepository.findAll();
        return new ApiResponse("allInputProduct",true,inputProductList);
    }

    public InputProduct getInputProductById(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return optionalInputProduct.orElse(null);
    }

    public ApiResponse deleteInputProduct(Integer id){
        inputProductRepository.deleteById(id);
        return new ApiResponse("Input-Product deleted",true);
    }

    public ApiResponse editInputProduct(Integer id,InputProductDTO inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty()){
            return new ApiResponse("Such Input-Product doesn't exist",false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty()){
            return new ApiResponse("Such input doesn't exist",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new ApiResponse("Such product doesn't exist",false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        if (optionalProduct.get().getActive()) {
            inputProduct.setProduct(optionalProduct.get());
        }else {
            return new ApiResponse("Bu product id active emas iltimos boshqa iddan foydalaning",false);
        }

        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("Input Product edited",true);
    }
}
