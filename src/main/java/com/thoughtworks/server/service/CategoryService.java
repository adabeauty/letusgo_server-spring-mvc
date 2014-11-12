package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.CategoryDao;
import com.thoughtworks.server.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(int id);

    void insertCategory(Category category);

    void deleteCategoryById(int id);

    void updateCategoryById(Category category);

    List<Category> getCategories();

    void setCategoryDaoImpl(CategoryDao categoryDaoImpl);
}
