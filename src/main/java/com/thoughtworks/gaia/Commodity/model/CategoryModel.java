package com.thoughtworks.gaia.Commodity.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by wangpu on 17-4-28.
 */
@Entity
@Table(name = "CATEGORY")
public class CategoryModel extends IdBaseModel {
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    @Cascade(CascadeType.ALL)
    private List<CommodityModel> commodityModelList;

    public List<CommodityModel> getCommodityModelList() {
        return commodityModelList;
    }

    public void setCommodityModelList(List<CommodityModel> commodityModelList) {
        this.commodityModelList = commodityModelList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
