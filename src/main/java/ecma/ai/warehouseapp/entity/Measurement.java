package ecma.ai.warehouseapp.entity;

import ecma.ai.warehouseapp.entity.template.AbsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true) //Abs Entity
@Entity
@Data
public class Measurement extends AbsEntity {


}
