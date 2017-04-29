package com.thoughtworks.gaia.Commodity.service;

import com.thoughtworks.gaia.Commodity.CategoryMapper;
import com.thoughtworks.gaia.Commodity.dao.CategoryDao;
import com.thoughtworks.gaia.Commodity.entity.Category;
import com.thoughtworks.gaia.Commodity.model.CategoryModel;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangpu on 17-4-28.
 */
@Component
@Transactional
public class CategoryService implements Loggable {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryMapper categoryMapper;

    public Category getCategories(Long categoryId) {
        CategoryModel categoryModel = categoryDao.idEquals(categoryId).querySingle();
        if (categoryModel == null) {
            error("not found with id:" + categoryId);
            throw new NotFoundException();
        }
        return categoryMapper.map(categoryModel, Category.class);
    }

    public List<Category> getAllCategories() {
        List<CategoryModel> categoryModelList = categoryDao.where().queryList();
        if (categoryModelList == null) {
            throw new NotFoundException();
        }
        return categoryMapper.mapList(categoryModelList, Category.class);
    }
}