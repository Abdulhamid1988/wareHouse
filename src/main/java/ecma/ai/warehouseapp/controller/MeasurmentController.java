package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.Measurement;
import ecma.ai.warehouseapp.entity.Warehouse;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.MeasurmentRepository;
import ecma.ai.warehouseapp.service.MeasurmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurment")
public class MeasurmentController {

    @Autowired
    MeasurmentService measurmentService;

    @Autowired
    MeasurmentRepository measurmentRepository;

    @PostMapping("/addMeasurment")
    public ApiResponse addMeasurment(@RequestBody  Measurement measurement){
        return measurmentService.addMeasurment(measurement);
    }

    @GetMapping("/all")
    public ApiResponse getAll() {
        List<Measurement> all = measurmentRepository.findAll();
        return new ApiResponse("Xamma o'lchovlar ro'yhati!", true, all);
    }

    @GetMapping("/getOneList/{id}")
    public ApiResponse getOneList(@PathVariable Integer id){
        return measurmentService.getOne(id);
    }

    @PutMapping("/editMeasurment/{id}")
    public ApiResponse editMeasurment(@PathVariable Integer id,@RequestBody Measurement measurement){
        return measurmentService.editMeasurment(id,measurement);
    }

    @DeleteMapping("/deleteMeasurment/{id}")
    public ApiResponse deleteMeasurment(@PathVariable Integer id){
        return measurmentService.deleteMeasurment(id);
    }
}
