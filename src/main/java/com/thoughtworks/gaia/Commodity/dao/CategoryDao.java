package com.thoughtworks.gaia.Commodity.dao;

import com.thoughtworks.gaia.Commodity.model.CategoryModel;
import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import org.springframework.stereotype.Component;

/**
 * Created by wangpu on 17-4-28.
 */
@Component
public class CategoryDao extends BaseDaoWrapper<CategoryModel> {
    public CategoryDao(){
        super(CategoryModel.class);
    }
}
