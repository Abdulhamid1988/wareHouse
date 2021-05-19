package ecma.ai.warehouseapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDTO {

    private Date date;

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private String factureNumber;
}
