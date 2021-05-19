package ecma.ai.warehouseapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private Boolean active;

    private Set<Integer> ware_house_id;
}
