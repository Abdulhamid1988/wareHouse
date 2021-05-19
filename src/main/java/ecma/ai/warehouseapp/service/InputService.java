package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Currency;
import ecma.ai.warehouseapp.entity.Input;
import ecma.ai.warehouseapp.entity.Supplier;
import ecma.ai.warehouseapp.entity.Warehouse;
import ecma.ai.warehouseapp.model.InputDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.CurrencyRepository;
import ecma.ai.warehouseapp.repository.InputRepository;
import ecma.ai.warehouseapp.repository.SuplierRepository;
import ecma.ai.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    SuplierRepository suplierRepository;

    @Autowired
    WarehouseRepository warehouseRepository;



    public ApiResponse addInput(InputDTO inputDTO){
        Input input = new Input();



        input.setFactureNumber(inputDTO.getFactureNumber());

        int code=inputRepository.findAll().size()+1;

        input.setCode("Num"+code);

        input.setDate(inputDTO.getDate());
        Optional<Currency> byId = currencyRepository.findById(inputDTO.getCurrencyId());

       if (byId.isPresent()){

           Currency currency = byId.get();

           if (currency.getActive()){

               input.setCurrency(currency);
           }
           else {
               return new ApiResponse("valyuta id faol emas iltimos boshqa valyuta turidan foydalaning",false);
           }
       }

        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(inputDTO.getWarehouseId());
        warehouseRepositoryById.ifPresent(input::setWarehouse);



        Optional<Supplier> supplier = suplierRepository.findById(inputDTO.getSupplierId());
        if (supplier.isPresent()){
            input.setSupplier(supplier.get());
        } else {
            return new ApiResponse("Supplier id not fount",false);
        }

        inputRepository.save(input);
        return new ApiResponse("Added successfully",true);

    }

    public ApiResponse getAllList(){
        List<Input> all = inputRepository.findAll();
        return new ApiResponse("Inputs List",true,all);
    }

    public ApiResponse editInput(Integer id,InputDTO inputDTO){
        Optional<Input> byId = inputRepository.findById(id);
        Optional<Currency> currencyRepositoryById = currencyRepository.findById(inputDTO.getCurrencyId());
        Optional<Supplier> supplier = suplierRepository.findById(inputDTO.getSupplierId());
        Optional<Warehouse> warehouseRepositoryById = warehouseRepository.findById(inputDTO.getWarehouseId());

        if (byId.isPresent()){
            Input input = byId.get();
            if (currencyRepositoryById.isPresent()){
                    Currency currency = currencyRepositoryById.get();
                    if (currency.getActive()){
                        input.setCurrency(currency);
                    }
                    else {
                        return new ApiResponse("valyuta id faol emas iltimos boshqa valyuta turidan foydalaning",false);
                    }

            }else {
                return new ApiResponse("Id not fount",false);
            }
            if (supplier.isPresent()){
                input.setSupplier(supplier.get());
            }else {
                return new ApiResponse("Id not fount",false);
            }

            if (warehouseRepositoryById.isPresent()){
                input.setWarehouse(warehouseRepositoryById.get());
            }else {
                return new ApiResponse("Id not fount",false);
            }
            int code=inputRepository.findAll().size()+1;
            input.setCode("Num"+code);

            input.setFactureNumber(inputDTO.getFactureNumber());

            inputRepository.save(input);
            return new ApiResponse("edited successfully",true);
        }
        return new ApiResponse("Id not fount",false);
    }

    public ApiResponse delete(Integer id){
        Optional<Input> byId = inputRepository.findById(id);
        if (byId.isPresent()){
            inputRepository.deleteById(id);
            return new ApiResponse("Deleted successfully",true);
        }
        return new ApiResponse("Something went wrong",false);
    }

}
