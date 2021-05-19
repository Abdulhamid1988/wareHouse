package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Supplier;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.SuplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuplierService {

    @Autowired
    SuplierRepository suplierRepository;

    // Add
    public ApiResponse addSupplier(Supplier supplier){
        Supplier supplierGet = new Supplier();

        boolean checkNumber = suplierRepository.existsByPhoneNumber(supplier.getName());
        if (checkNumber){
            return new ApiResponse("bu raqam mavjud",false);
        }
        supplierGet.setName(supplier.getName());
        supplierGet.setPhoneNumber(supplier.getPhoneNumber());
        suplierRepository.save(supplierGet);
        return new ApiResponse("successfully added", true);
    }

    //edit
    public ApiResponse editSupplier(Supplier supplier,Integer id){
        Optional<Supplier> byId = suplierRepository.findById(id);
        if (byId.isPresent()){
            Supplier supplierGet = byId.get();
            supplierGet.setName(supplier.getName());
            boolean checkNumber = suplierRepository.existsByPhoneNumber(supplier.getName());
            if (checkNumber){
                return new ApiResponse("bu raqam mavjud",false);
            }
            supplierGet.setPhoneNumber(supplier.getPhoneNumber());
            suplierRepository.save(supplierGet);

            return  new ApiResponse("Edited successfully", true);
        }

        return new ApiResponse("Opps something went wrong please try again later", false);
    }

    //delete
    public ApiResponse deleteSupplier(Integer id){
        Optional<Supplier> byId = suplierRepository.findById(id);
        if (byId.isPresent()){
            suplierRepository.deleteById(id);
            return new ApiResponse("deleted successfully",true);
        }

        return new ApiResponse("id not fount",false);
    }
}
