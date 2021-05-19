package ecma.ai.warehouseapp.controller;

import ecma.ai.warehouseapp.entity.Input;
import ecma.ai.warehouseapp.entity.InputProduct;
import ecma.ai.warehouseapp.entity.OutputProduct;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.InputProductRepository;
import ecma.ai.warehouseapp.repository.InputRepository;
import ecma.ai.warehouseapp.repository.OutputProductRepository;
import ecma.ai.warehouseapp.repository.OutputRepository;
import ecma.ai.warehouseapp.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/getDailyInfo/{date}")
    public ApiResponse getDailyInfo(@PathVariable String date) {

        return dashboardService.getDailyInput(Date.valueOf(date));
    }


    @GetMapping("/getExpireDate/{date}")
    public ApiResponse getExpireDate(@PathVariable String date){
        return dashboardService.getWithExpireDate(Date.valueOf(date));
    }


}



