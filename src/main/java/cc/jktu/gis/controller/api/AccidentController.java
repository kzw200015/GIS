package cc.jktu.gis.controller.api;

import cc.jktu.gis.model.entity.AccidentEntity;
import cc.jktu.gis.model.schema.PageResp;
import cc.jktu.gis.service.AccidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accidents")
public class AccidentController {

    private final AccidentService accidentService;

    @GetMapping("/{id}")
    public AccidentEntity getAccidentById(@PathVariable("id") Integer id) {
        final AccidentEntity accident = accidentService.getAccidentById(id);
        System.out.println(accident);
        return accident;
    }

    @GetMapping("")
    public PageResp<AccidentEntity> getAccidentsByPage(@RequestParam("page") Long page, @RequestParam("size") Long size) {
        return accidentService.getUsersByPage(page, size);
    }

    @PostMapping("")
    public void addAccident(@RequestBody AccidentEntity accident) {
        accidentService.addAccident(accident);
    }

    @PatchMapping("/{id}")
    public void updateAccidentById(@PathVariable("id") Integer id, @RequestBody AccidentEntity accident) {
        accidentService.updateAccidentById(id, accident);
    }

    @DeleteMapping("/{id}")
    public void deleteAccidentById(@PathVariable("id") Integer id) {
        accidentService.deleteAccidentById(id);
    }

}