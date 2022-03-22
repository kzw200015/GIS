package cc.jktu.gis.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("polices")
public class PoliceEntity extends BaseEntity {

    private String name;
    private String phone;

}
