package ecma.ai.warehouseapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDTO {

    private Date date;

    private Integer warehouse_id;

    private Integer clientId;

    private Integer currency_id;

    private String factureNumber;


}
