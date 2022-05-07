package cc.jktu.gis.service;

import cc.jktu.gis.model.entity.AccidentEntity;
import cc.jktu.gis.model.exception.EntityNotFoundException;
import cc.jktu.gis.model.mapper.AccidentMapper;
import cc.jktu.gis.model.schema.PageResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentMapper accidentMapper;

    public AccidentEntity getAccidentById(Integer id) {
        final AccidentEntity accident = accidentMapper.selectById(id);
        if (accident == null) {
            throw new EntityNotFoundException("accident", id);
        }

        return accident;
    }

    public void addAccident(AccidentEntity accident) {
        accidentMapper.insert(accident);
    }

    public void updateAccidentById(Integer id, AccidentEntity accident) {
        accident.setId(id);
        accidentMapper.updateById(accident);
    }

    public void deleteAccidentById(Integer id) {
        accidentMapper.deleteById(id);
    }

    public PageResp<AccidentEntity> getUsersByPage(Long page, Long size) {
        final Page<AccidentEntity> accidents = accidentMapper.selectPage(new Page<>(page, size), null);
        final PageResp<AccidentEntity> pageResp = new PageResp<>();
        pageResp.setTotal(accidents.getTotal());
        pageResp.setPage(accidents.getCurrent());
        pageResp.setSize(accidents.getSize());
        pageResp.setValues(accidents.getRecords());
        return pageResp;
    }

}
