package cc.jktu.gis.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.postgis.Point;

import java.io.IOException;
import java.util.HashMap;

public class PointSerializer extends StdSerializer<Point> {

    public PointSerializer() {
        this(null);
    }

    protected PointSerializer(Class<Point> t) {
        super(t);
    }

    @Override
    public void serialize(Point value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        final HashMap<String, Double> map = new HashMap<>();
        map.put("x", value.getX());
        map.put("y", value.getY());
        gen.writeObject(map);
    }

}
