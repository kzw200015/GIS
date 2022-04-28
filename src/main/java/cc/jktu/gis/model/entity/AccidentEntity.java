package cc.jktu.gis.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eyougo.mybatis.postgis.type.PointTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.postgis.Point;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "accidents", autoResultMap = true)
public class AccidentEntity extends BaseEntity {

    @TableField(typeHandler = PointTypeHandler.class)
    private Point coordinate;
    private String description;
    private Instant time;
    private Boolean isResolved;
    private String image;

}
