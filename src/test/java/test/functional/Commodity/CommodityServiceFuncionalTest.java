package test.functional.Commodity;


import com.thoughtworks.gaia.Commodity.dao.CategoryDao;
import com.thoughtworks.gaia.Commodity.dao.CommodityDao;
import com.thoughtworks.gaia.Commodity.entity.Commodity;
import com.thoughtworks.gaia.Commodity.model.CategoryModel;
import com.thoughtworks.gaia.Commodity.model.CommodityModel;
import com.thoughtworks.gaia.Commodity.service.CommodityService;
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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wangpu on 17-4-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class CommodityServiceFuncionalTest {
    @Autowired
    private CommodityService commidityService;
    @Autowired
    private CommodityDao commodityDao;
    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void should_get_commodity_with_id() throws Exception {
        CommodityModel commodityModel = dummyProductModel();
        commodityDao.save(commodityModel);
        Long commodityId = commodityModel.getId();

        Commodity commodity = commidityService.getCommodity(commodityId);

        assertThat(commodity.getId()).isEqualTo(commodityId);
    }

    private CommodityModel dummyProductModel() {
        CommodityModel commodityModel = new CommodityModel();
        commodityModel.setName("MacBook");
        commodityModel.setPrice(666d);
        commodityModel.setAmount((double) 50);
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("computer");
        categoryDao.save(categoryModel);
        commodityModel.setCategory_id(categoryModel.getId());
        return commodityModel;
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_not_found() {
        commidityService.getCommodity(-1L);
    }
}