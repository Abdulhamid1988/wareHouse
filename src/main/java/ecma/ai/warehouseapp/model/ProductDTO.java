package ecma.ai.warehouseapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;

    private Integer categoryId;

    private Integer attachment_id;

    private String code;

    private Integer measurement_id;

}
