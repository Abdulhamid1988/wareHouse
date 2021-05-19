package ecma.ai.warehouseapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDTO {

    private Integer productId;

    private Double amount;
    private double price;

    private Date expireDate;

    private Integer inputId;

}
