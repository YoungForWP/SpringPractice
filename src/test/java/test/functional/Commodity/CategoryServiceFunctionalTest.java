package test.functional.Commodity;

import com.thoughtworks.gaia.Commodity.CategoryMapper;
import com.thoughtworks.gaia.Commodity.dao.CategoryDao;
import com.thoughtworks.gaia.Commodity.entity.Category;
import com.thoughtworks.gaia.Commodity.model.CategoryModel;
import com.thoughtworks.gaia.Commodity.service.CategoryService;
import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wangpu on 17-4-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class CategoryServiceFunctionalTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void should_get_category_with_id() throws Exception {
        CategoryModel categoryModel = dummyProductModel();
        categoryDao.save(categoryModel);
        Long categoryId = categoryModel.getId();

        Category category = categoryService.getCategories(categoryId);

        assertThat(category.getId()).isEqualTo(categoryId);
    }



    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_not_found() {
        categoryService.getCategories(-1L);
    }

    private CategoryModel dummyProductModel() {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("computer");
        return categoryModel;
    }

    @Test
    public void shoule_get_all_categories() throws Exception{
        CategoryModel categoryModelOne = dummyProductModel();
        categoryDao.save(categoryModelOne);
        CategoryModel categoryModelTwo = dummyProductModel();
        categoryDao.save(categoryModelTwo);

        List<CategoryModel> categoryModelListDao = categoryDao.where().queryList();
        List<Category> categoryListDao = categoryMapper.mapList(categoryModelListDao,Category.class);
        List<Category> categoryList = categoryService.getAllCategories();

        for(int i = 0; i<categoryListDao.size();i++){
            assertThat(categoryListDao.get(i).getId()).isEqualTo(categoryList.get(i).getId());

        }
    }
}


