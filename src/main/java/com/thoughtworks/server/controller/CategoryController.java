package com.thoughtworks.server.controller;

import com.thoughtworks.server.model.Category;
import com.thoughtworks.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CategoryController {
    @Autowired
    private CategoryService categoryServiceImpl;

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Category getCategoryById(@PathVariable int id){
        return categoryServiceImpl.getCategoryById(id);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public void insertCategory(@RequestBody Category category){
        categoryServiceImpl.insertCategory(category);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public void deleteCategoryById(@PathVariable int id){
        categoryServiceImpl.deleteCategoryById(id);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
    public void updateCategory(@RequestBody Category category){
        categoryServiceImpl.updateCategoryById(category);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategories(){
        return categoryServiceImpl.getCategories();
    }
}
