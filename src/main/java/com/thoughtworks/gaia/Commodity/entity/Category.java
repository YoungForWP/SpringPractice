package com.thoughtworks.gaia.Commodity.entity;

import com.thoughtworks.gaia.Commodity.model.CommodityModel;

import java.util.List;

/**
 * Created by wangpu on 17-4-28.
 */
public class Category {

    private Long id;

    private String name;

    private List<CommodityModel> commodityModelList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CommodityModel> getCommodityModelList() {
        return commodityModelList;
    }

    public void setCommodityModelList(List<CommodityModel> commodityModelList) {
        this.commodityModelList = commodityModelList;
    }
}
