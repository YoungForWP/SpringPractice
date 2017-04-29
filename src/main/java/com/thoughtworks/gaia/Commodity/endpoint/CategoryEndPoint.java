package com.thoughtworks.gaia.Commodity.endpoint;

import com.thoughtworks.gaia.Commodity.entity.Category;
import com.thoughtworks.gaia.Commodity.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by wangpu on 17-4-28.
 */

@Component
@Path("categories")
@Api(value = "category", description = "Access to category resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryEndPoint {
    @Autowired
    private CategoryService categoryService;

    @Path("/{category_id}")
    @ApiOperation(value = "Get category by id", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get category successfully"),
            @ApiResponse(code = 404, message = "No category matches given id")
    })
    @GET
    public Response getCategoryById(@PathParam("category_id") Long categoryId) {
        Category category = categoryService.getCategories(categoryId);
        return Response.ok().entity(category).build();
    }


    @ApiOperation(value = "Get categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get category successfully"),
            @ApiResponse(code = 404, message = "No category matches ")
    })
    @GET
    public List<Category> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        GenericEntity<List<Category>> entity = new GenericEntity<List<Category>>(categoryList){};
        return categoryList;
    }
}