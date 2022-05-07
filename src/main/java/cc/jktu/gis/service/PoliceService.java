package cc.jktu.gis.service;

import cc.jktu.gis.model.entity.PoliceEntity;
import cc.jktu.gis.model.exception.EntityNotFoundException;
import cc.jktu.gis.model.mapper.PoliceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final PoliceMapper policeMapper;

    public PoliceEntity getPoliceById(Integer id) {
        final PoliceEntity police = policeMapper.selectById(id);
        if (police == null) {
            throw new EntityNotFoundException("police", id);
        }

        return police;
    }

    public void addPolice(PoliceEntity policeEntity) {
        policeMapper.insert(policeEntity);
    }

    public void removePolice(Integer id) {
        policeMapper.deleteById(id);
    }

    public void updatePolice(Integer id, PoliceEntity police) {
        police.setId(id);
        policeMapper.updateById(police);
    }

}
