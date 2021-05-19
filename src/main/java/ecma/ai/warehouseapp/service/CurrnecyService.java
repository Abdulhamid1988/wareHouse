package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Currency;
import ecma.ai.warehouseapp.entity.Measurement;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrnecyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse addCurrency(Currency currency){
        Currency addingCurrency = new Currency();

        addingCurrency.setActive(currency.getActive());
        addingCurrency.setName(currency.getName());


        currencyRepository.save(addingCurrency);

        return new ApiResponse("Added successfully",true);// avval bazda bor yoki yoligiga tekshirish kerak


    }

    public ApiResponse allCurrency(){
        List<Currency> all = currencyRepository.findAll();
        return new ApiResponse("all currency List",true,all);
    }

    public ApiResponse getOne(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            Currency currency = byId.get();
            return new ApiResponse("successfuully found",true,currency);
        }

        return new ApiResponse("id not fount",false);

    }


    public ApiResponse editCurrency(Integer id,Currency currency){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            Currency editingCurrency = byId.get();
            editingCurrency.setActive(editingCurrency.getActive());
            editingCurrency.setName(currency.getName());
            currencyRepository.save(editingCurrency);
            return new ApiResponse("succeess",true);
        }
        return new ApiResponse("Error id not fount",false);
    }



    public ApiResponse deleteCurrency(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            currencyRepository.deleteById(id);
            return new ApiResponse("Deleted successfully",true);
        }

        return new ApiResponse("Id not fount ",false);
    }
}
