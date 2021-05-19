package ecma.ai.warehouseapp.entity;

import ecma.ai.warehouseapp.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class
User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String code = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String password;

    private Boolean active = true;

    @ManyToMany
    private Set<Warehouse> warehouses;

}
