package cc.jktu.gis.service;

import cc.jktu.gis.model.entity.PoliceEntity;
import cc.jktu.gis.model.exception.EntityNotFoundException;
import cc.jktu.gis.model.mapper.PoliceMapper;
import cc.jktu.gis.model.schema.PageResp;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public void deletePoliceById(Integer id) {
        policeMapper.deleteById(id);
    }

    public void updatePoliceById(Integer id, PoliceEntity police) {
        police.setId(id);
        policeMapper.updateById(police);
    }

    public PageResp<PoliceEntity> getPolicesByPage(Long page, Long size) {
        final Page<PoliceEntity> polices = policeMapper.selectPage(new Page<>(page, size), null);
        final PageResp<PoliceEntity> pageResp = new PageResp<>();
        pageResp.setTotal(polices.getTotal());
        pageResp.setPage(polices.getCurrent());
        pageResp.setSize(polices.getSize());
        pageResp.setValues(polices.getRecords());
        return pageResp;
    }

}
