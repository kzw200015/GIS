package cc.jktu.gis.model.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum Gender implements IEnum<Integer> {
    MALE, FEMALE;

    @Override
    public Integer getValue() {
        return ordinal();
    }
}
