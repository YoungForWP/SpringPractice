package com.thoughtworks.gaia.Commodity.service;

import com.thoughtworks.gaia.Commodity.CommodityMapper;
import com.thoughtworks.gaia.Commodity.dao.CommodityDao;
import com.thoughtworks.gaia.Commodity.entity.Commodity;
import com.thoughtworks.gaia.Commodity.model.CommodityModel;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by wangpu on 17-4-28.
 */
@Component
@Transactional
public class CommodityService implements Loggable {

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private CommodityMapper commodityMapper;

    public Commodity getCommodity(Long commodityId) {
        CommodityModel commodityModel = commodityDao.idEquals(commodityId).querySingle();
        if (commodityModel == null) {
            error("not found with id:" + commodityId);
            throw new NotFoundException();
        }
        return commodityMapper.map(commodityModel, Commodity.class);
    }
}