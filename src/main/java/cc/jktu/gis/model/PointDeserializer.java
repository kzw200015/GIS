package cc.jktu.gis.model;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import org.postgis.Point;

import java.io.IOException;

public class PointDeserializer extends StdDeserializer<Point> {

    public PointDeserializer() {
        this(null);
    }

    protected PointDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Point deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final TreeNode tree = p.readValueAsTree();
        final Double x = (Double) ((DoubleNode) tree.get("x")).numberValue();
        final Double y = (Double) ((DoubleNode) tree.get("y")).numberValue();
        return new Point(x, y);
    }

}
