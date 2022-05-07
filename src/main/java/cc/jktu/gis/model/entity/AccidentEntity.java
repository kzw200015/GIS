package cc.jktu.gis.model.entity;

import cc.jktu.gis.model.PointDeserializer;
import cc.jktu.gis.model.PointSerializer;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eyougo.mybatis.postgis.type.PointTypeHandler;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.postgis.Point;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "accidents", autoResultMap = true)
public class AccidentEntity extends BaseEntity {

    @JsonSerialize(using = PointSerializer.class)
    @JsonDeserialize(using = PointDeserializer.class)
    @TableField(typeHandler = PointTypeHandler.class)
    private Point coordinate;
    private String description;

    private Instant time;
    private Boolean isResolved;
    private String image;

}
