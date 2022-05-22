package cc.jktu.gis.controller.api;

import cc.jktu.gis.model.entity.PoliceEntity;
import cc.jktu.gis.model.schema.PageResp;
import cc.jktu.gis.service.PoliceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polices")
@RequiredArgsConstructor
public class PoliceController {

    private final PoliceService policeService;

    @GetMapping("/{id}")
    public PoliceEntity getPoliceById(@PathVariable("id") Integer id) {
        final PoliceEntity police = policeService.getPoliceById(id);
        System.out.println(police);
        return police;
    }

    @GetMapping("")
    public PageResp<PoliceEntity> getPolicesByPage(@RequestParam("page") Long page, @RequestParam("size") Long size) {
        return policeService.getPolicesByPage(page, size);
    }

    @PostMapping("")
    public void addPolice(@RequestBody PoliceEntity police) {
        policeService.addPolice(police);
    }

    @PatchMapping("/{id}")
    public void updatePoliceById(@PathVariable("id") Integer id, @RequestBody PoliceEntity police) {
        policeService.updatePoliceById(id, police);
    }

    @DeleteMapping("/{id}")
    public void deletePoliceById(@PathVariable("id") Integer id) {
        policeService.deletePoliceById(id);
    }

}
