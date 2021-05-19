package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Client;
import ecma.ai.warehouseapp.entity.Currency;
import ecma.ai.warehouseapp.entity.Output;
import ecma.ai.warehouseapp.entity.Warehouse;
import ecma.ai.warehouseapp.model.OutputDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.ClientRepository;
import ecma.ai.warehouseapp.repository.CurrencyRepository;
import ecma.ai.warehouseapp.repository.OutputRepository;
import ecma.ai.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CurrencyRepository currencyRepository;


    public ApiResponse addOutput(OutputDTO outputDTO){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouse_id());
        if (optionalWarehouse.isEmpty()){
            return new ApiResponse("Such warehouse doesn't exist",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        if (optionalClient.isEmpty()){
            return new ApiResponse("Such client doesn't exist",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrency_id());
        if (optionalCurrency.isEmpty()){
            return new ApiResponse("Such currency doesn't exist",false);
        }
        Output output = new Output();
        output.setDate(output.getDate());
        output.setFactureNumber(outputDTO.getFactureNumber());
        if (optionalWarehouse.get().getActive()) {
            output.setWarehouse(optionalWarehouse.get());
        }else {
            return new ApiResponse("bu ombor foal emas",false);
        }
        if(optionalCurrency.get().getActive()){
            output.setCurrency(optionalCurrency.get());
        }else {
            return new ApiResponse("bu currency foal emas",false);
        }
        output.setClient(optionalClient.get());
        int code=outputRepository.findAll().size()+1;
        output.setCode("I"+code);
        outputRepository.save(output);
        return new ApiResponse("Output added!!!",true);


    }

    public ApiResponse getOutputs(){
        List<Output> all = outputRepository.findAll();
        return new ApiResponse("allOutputList",true,all);
    }

    public ApiResponse getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            return new ApiResponse("output by id",true,optionalOutput.get());
        }

        return new ApiResponse("id not fount please try again later",false);

    }

    public ApiResponse deleteOutput(Integer id){
        Optional<Output> byId = outputRepository.findById(id);
        if (byId.isPresent()) {
            outputRepository.deleteById(id);
            return new ApiResponse("Output deleted", true);
        }
        return new ApiResponse("output id not fount",false);
    }

    public ApiResponse editOutput(Integer id,OutputDTO outputdto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()){
            return new ApiResponse("Such output doesn't exist",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputdto.getWarehouse_id());
        if (optionalWarehouse.isEmpty()){
            return new ApiResponse("Such warehouse doesn't exist",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputdto.getClientId());
        if (optionalClient.isEmpty()){
            return new ApiResponse("Such client doesn't exist",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputdto.getCurrency_id());
        if (optionalCurrency.isEmpty()){
            return new ApiResponse("Such currency doesn't exist",false);
        }

        Output output = optionalOutput.get();
        output.setDate(outputdto.getDate());
        output.setFactureNumber(outputdto.getFactureNumber());
        if (optionalWarehouse.get().getActive()) {
            output.setWarehouse(optionalWarehouse.get());
        }else {
            return new ApiResponse("bu ombor foal emas",false);
        }
        if(optionalCurrency.get().getActive()){
            output.setCurrency(optionalCurrency.get());
        }else {
            return new ApiResponse("bu currency foal emas",false);
        }

        output.setClient(optionalClient.get());
        int code=outputRepository.findAll().size()+1;
        output.setCode("I"+code);
        outputRepository.save(output);
        return new ApiResponse("Output edited!!!",true);
    }
}
