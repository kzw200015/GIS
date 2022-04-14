package cc.jktu.gis.service;

import cc.jktu.gis.model.entity.AccidentEntity;
import cc.jktu.gis.model.exception.EntityNotFoundException;
import cc.jktu.gis.model.mapper.AccidentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccidentService {

    private AccidentMapper accidentMapper;

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

}
