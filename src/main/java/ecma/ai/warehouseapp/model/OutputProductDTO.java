package ecma.ai.warehouseapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProductDTO {

    private Integer productId;


    private Double amount;

    private double price;

    private Integer outputId;
}
