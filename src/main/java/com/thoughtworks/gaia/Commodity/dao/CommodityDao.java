package com.thoughtworks.gaia.Commodity.dao;

import com.thoughtworks.gaia.Commodity.model.CommodityModel;
import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import org.springframework.stereotype.Component;

/**
 * Created by wangpu on 17-4-28.
 */
@Component
public class CommodityDao extends BaseDaoWrapper<CommodityModel> {
    public CommodityDao(){
        super(CommodityModel.class);
    }
}
