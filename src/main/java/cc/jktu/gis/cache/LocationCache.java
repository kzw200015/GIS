package cc.jktu.gis.cache;

import cc.jktu.gis.model.schema.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LocationCache {

    private final AtomicInteger onlineCount = new AtomicInteger();
    private final Map<Integer, Location> locationMap = new ConcurrentHashMap<>() {
        {
            final Random random = new Random();
            for (int i = 1; i <= 8; i++) {
                int id = i;
                put(i, new Location() {
                    {
                        setId(id);
                        setX(120.5D + (121D - 120.5D) * random.nextDouble());
                        setY(31D + (31.7D - 31D) * random.nextDouble());
                    }
                });
            }
        }
    };

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
