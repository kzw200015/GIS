package cc.jktu.gis.controller.ws;

import cc.jktu.gis.cache.LocationCache;
import cc.jktu.gis.model.schema.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LocationController {

    private final LocationCache locationCache;

    @MessageMapping("/report")
    @SendTo("/topic/locations/update")
    public Location receiveLocation(Location location) {
        locationCache.setLocation(location.getId(), location);
        return location;
    }

    @MessageMapping("/all")
    @SendToUser("/topic/locations/all")
    public List<Location> getAllLocations() {
        return locationCache.getAll();
    }

}
