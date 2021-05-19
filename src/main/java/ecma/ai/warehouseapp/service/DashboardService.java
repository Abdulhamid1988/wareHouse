package ecma.ai.warehouseapp.service;

import ecma.ai.warehouseapp.entity.InputProduct;
import ecma.ai.warehouseapp.entity.OutputProduct;
import ecma.ai.warehouseapp.payload.ApiResponse;
import ecma.ai.warehouseapp.repository.InputProductRepository;
import ecma.ai.warehouseapp.repository.InputRepository;
import ecma.ai.warehouseapp.repository.OutputProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    OutputProductRepository outputProductRepository;


    public ApiResponse getDailyInput(Date date) {
        List<InputProduct> allByInputDate = inputProductRepository.findByDate(date);
        if (!allByInputDate.isEmpty()) {

            Double sum = 0D;

            for (InputProduct inputProduct : allByInputDate) {

                sum += inputProduct.getPrice();
            }
            return new ApiResponse("Success", true, sum);
        }
        return new ApiResponse("Kunlik kirim mavjud emas", false);
    }

    public ApiResponse getWithExpireDate(Date date) {
        List<InputProduct> byExpireDate = inputProductRepository.getByExpireDate(date);
        if (!byExpireDate.isEmpty()) return new ApiResponse("Success", true, byExpireDate);
        return new ApiResponse("Bu Sanada yaroqlik muddati tugeydigan mahsulotlar mavjud emas", true);
    }

    public ApiResponse getWithOutputAmount(Date date) {
        List<OutputProduct> byAmountDate = outputProductRepository.getByAmountDate(date);
        if (byAmountDate.isEmpty()) return new ApiResponse("Kunlik chiqim mavjud emas.", false);
        return new ApiResponse("Kunlik eng ko'p qilingan chiqimlar.", true, byAmountDate);
    }


}
