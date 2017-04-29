package com.thoughtworks.gaia.student.dao;

import com.thoughtworks.gaia.student.model.KclassModel;
import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import org.springframework.stereotype.Component;

@Component
public class KclassDao extends BaseDaoWrapper<KclassModel> {
    public KclassDao() {
        super(KclassModel.class);
    }
}
