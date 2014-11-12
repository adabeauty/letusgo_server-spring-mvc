package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.CategoryDao;
import com.thoughtworks.server.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDaoImpl;

    public void setCategoryDaoImpl(CategoryDao categoryDaoImpl) {
        this.categoryDaoImpl = categoryDaoImpl;
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDaoImpl.getCategoryById(id);
    }

    @Override
    public void insertCategory(Category category) {
        categoryDaoImpl.insertCategory(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryDaoImpl.deleteCategoryById(id);
    }

    @Override
    public void updateCategoryById(Category category) {
        categoryDaoImpl.updateCategoryById(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDaoImpl.getCategories();
    }
}
