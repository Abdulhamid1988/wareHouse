package ecma.ai.warehouseapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String name;

    private Boolean active;

    private Integer parentCategoryId;
}
