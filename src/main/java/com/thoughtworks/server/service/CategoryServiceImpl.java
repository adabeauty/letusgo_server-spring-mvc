package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.CategoryDao;
import com.thoughtworks.server.dao.ItemDao;
import com.thoughtworks.server.model.Category;
import com.thoughtworks.server.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDaoImpl;

    @Autowired
    private ItemDao itemDaoImpl;

    public void setCategoryDaoImpl(CategoryDao categoryDaoImpl) {
        this.categoryDaoImpl = categoryDaoImpl;
    }

    @Override
    public Category getCategoryById(int id) {
        List<Item> item= itemDaoImpl.getItemsByCategoryId(id);
        Category category = categoryDaoImpl.getCategoryById(id);
        System.out.println(new Category(category.getId(), category.getName(), item.size()));
        return new Category(category.getId(), category.getName(), item.size());
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
        List<Category> categoryList = new ArrayList<Category>();
        List<Category> categories = categoryDaoImpl.getCategories();
        for(int i = 0; i<categories.size(); i++){
            List<Item> items = itemDaoImpl.getItemsByCategoryId(categories.get(i).getId());
            categoryList.add(new Category(categories.get(i).getId(), categories.get(i).getName(), items.size()));
        }
        return categoryList;
    }
}
