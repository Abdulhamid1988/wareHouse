package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.Currency;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.CurrencyRepository;
import ecma.ai.warehouseapp.service.CurrnecyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrnecyService currnecyService;

    @PostMapping("/add")
    public ApiResponse addCurrency(@RequestBody Currency currency){
        return currnecyService.addCurrency(currency);
    }


    @PutMapping("/edit/{id}")
    public ApiResponse editCurrency(@PathVariable Integer id,@RequestBody Currency currency){
        return currnecyService.editCurrency(id,currency);
    }

    @GetMapping("/getOne/{id}")
    public ApiResponse getOne(@PathVariable Integer id){
        return currnecyService.getOne(id);
    }

    @GetMapping("/all")
    public ApiResponse getAll(){
        return currnecyService.allCurrency();
    }

    @DeleteMapping("/deleteCurrency/{id}")
    public ApiResponse deleteCurrencyById(@PathVariable Integer id){
        return currnecyService.deleteCurrency(id);
    }


}
