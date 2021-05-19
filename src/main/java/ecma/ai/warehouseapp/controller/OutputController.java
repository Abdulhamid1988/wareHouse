package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.model.OutputDTO;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public ApiResponse addOutput(@RequestBody OutputDTO outputDto) {
        return outputService.addOutput(outputDto);
    }

    @GetMapping("/getOutput")
    public ApiResponse getOutputs() {
        return outputService.getOutputs();
    }

    @GetMapping("/{id}")
    public ApiResponse getOutputById(@PathVariable Integer id) {
        return outputService.getOutputById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteOutput(@PathVariable Integer id) {
        return outputService.deleteOutput(id);
    }

    @PutMapping("/{id}")
    public ApiResponse editOutput(@PathVariable Integer id, @RequestBody OutputDTO outputDto) {
        return outputService.editOutput(id,outputDto);
    }
}
