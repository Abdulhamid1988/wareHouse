package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Output;
import ecma.ai.warehouseapp.entity.OutputProduct;
import ecma.ai.warehouseapp.entity.Product;
import ecma.ai.warehouseapp.model.OutputProductDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.OutputProductRepository;
import ecma.ai.warehouseapp.repository.OutputRepository;
import ecma.ai.warehouseapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse addOutputProduct(OutputProductDTO outputProductDto){
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new ApiResponse("Such output doesn't exist",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new ApiResponse("Such product doesn't exist",false);
        }
        OutputProduct outputProduct=new OutputProduct();

        outputProduct.setAmount(outputProductDto.getAmount());

        outputProduct.setPrice(outputProductDto.getPrice());

        outputProduct.setOutput(optionalOutput.get());

        if (optionalProduct.get().getActive()){
            outputProduct.setProduct(optionalProduct.get());
        }else{
            return new ApiResponse("bu product foal emas",false);
        }

        outputProductRepository.save(outputProduct);
        return new ApiResponse("Output Product added",true);
    }

    public ApiResponse getOutputProducts(){
        List<OutputProduct> outputProductList = outputProductRepository.findAll();
        return new ApiResponse("output productLists",true,outputProductList);
    }


    public ApiResponse getOutputProductByProductId(Integer productId){
        List<OutputProduct> byProductId = outputProductRepository.findAllByProduct_Id(productId);
        return new ApiResponse("product by id",true,byProductId);
    }

    public ApiResponse deleteOutputProduct(Integer id){
        outputProductRepository.deleteById(id);
        return new ApiResponse("Output-Product deleted",true);
    }

    public ApiResponse editOutputProduct(Integer id,OutputProductDTO outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isEmpty()){
            return new ApiResponse("Such Output-Product doesn't exist",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()){
            return new ApiResponse("Such output doesn't exist",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()){
            return new ApiResponse("Such product doesn't exist",false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());

        if (optionalProduct.get().getActive()){
            outputProduct.setProduct(optionalProduct.get());
        }else{
            return new ApiResponse("bu product foal emas",false);
        }
        outputProductRepository.save(outputProduct);
        return new ApiResponse("Output Product edited",true);
    }

}
