package com.thoughtworks.server.dao;

import com.thoughtworks.server.model.Category;

import java.util.List;

public interface CategoryDao {
    Category getCategoryById(int id);

    void insertCategory(Category category);

    void deleteCategoryById(int id);

    void updateCategoryById(Category category);

    List<Category> getCategories();
}
