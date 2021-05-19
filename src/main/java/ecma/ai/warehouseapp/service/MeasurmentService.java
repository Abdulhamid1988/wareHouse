package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.Measurement;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.MeasurmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class MeasurmentService {

   @Autowired
    MeasurmentRepository measurmentRepository;

    public ApiResponse addMeasurment(Measurement measurement){
        Measurement measurementGet = new Measurement();

        boolean checkMeasurement = measurmentRepository.existsByName(measurement.getName());
        if (checkMeasurement){
            return new ApiResponse("this measurement has already exist",false);
        }

        measurementGet.setName(measurement.getName());
        measurementGet.setActive(measurement.getActive());
        measurmentRepository.save(measurementGet);
        return new ApiResponse("Measurment added successfully",true);
    }

    public ApiResponse getOne(Integer id){
        Optional<Measurement> byId = measurmentRepository.findById(id);
       if (byId.isPresent()){
           Measurement measurement = byId.get();
           return new ApiResponse("successfuully found",true,measurement);
       }

       return new ApiResponse("id not fount",false);

    }

    public ApiResponse editMeasurment(Integer id,Measurement measurement){
        Optional<Measurement> byId = measurmentRepository.findById(id);
        if (byId.isPresent()){
            Measurement measurement1 = byId.get();
            measurement1.setName(measurement.getName());
            measurmentRepository.save(measurement1);
            return new ApiResponse("succeess",true);
        }
        return new ApiResponse("Error id not fount",false);
    }

    public ApiResponse deleteMeasurment(Integer id){
        Optional<Measurement> byId = measurmentRepository.findById(id);
        if (byId.isPresent()){
            measurmentRepository.deleteById(id);
            return new ApiResponse("Deleted successfully",true);
        }

        return new ApiResponse("Id not fount ",false);
    }
}
