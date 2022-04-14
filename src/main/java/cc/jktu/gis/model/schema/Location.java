package cc.jktu.gis.model.schema;

import lombok.Data;
import org.postgis.Point;

@Data
public class Location {

    private Integer id;
    private Double x;
    private Double y;

    public Point toPoint() {
        return new Point(x, y);
    }

}
