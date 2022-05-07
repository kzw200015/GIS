package cc.jktu.gis.model.schema;

import cc.jktu.gis.model.entity.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class PageResp<T extends BaseEntity> {

    private Long total;
    private Long page;
    private Long size;
    private List<T> values;

}
