package ecma.ai.warehouseapp.entity;

import ecma.ai.warehouseapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true) //Abs Entity
@Entity
@Data
public class Supplier extends AbsEntity {

    @Column(unique = true, nullable = false)
    private String phoneNumber;

}
