package cc.jktu.gis.cache;

import cc.jktu.gis.model.schema.Location;
import org.postgis.Point;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LocationCache {

    private final AtomicInteger onlineCount = new AtomicInteger();
    private final Map<Integer, Location> locationMap = new ConcurrentHashMap<>();

    public void setLocation(Integer id, Location location) {
        locationMap.put(id, location);
    }

    public Location getLocation(Integer id) {
        return locationMap.get(id);
    }

    public Integer getOnlineCount() {
        return onlineCount.get();
    }

    public void decrementOnlineCount() {
        onlineCount.incrementAndGet();
    }

    public void reduceOnlineCount() {
        onlineCount.decrementAndGet();
    }

    public List<Location> getAll() {
        return locationMap.values().stream().toList();
    }

}
