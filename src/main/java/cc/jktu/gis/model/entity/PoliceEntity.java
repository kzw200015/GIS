package cc.jktu.gis.model.entity;

import cc.jktu.gis.model.constant.Gender;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("polices")
public class PoliceEntity extends BaseEntity {

    private String phone;
    private Boolean isWorking;
    private String name;
    @EnumValue
    private Gender gender;
    private Integer userId;

}
