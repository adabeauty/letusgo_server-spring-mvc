package com.thoughtworks.server.service;

import com.thoughtworks.server.dao.CategoryDao;
import com.thoughtworks.server.dao.CategoryDaoImpl;
import com.thoughtworks.server.model.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {
    Category category;
    List<Category> categories = new ArrayList<Category>();

    CategoryDao categoryDaoImpl;
    CategoryService categoryServiceImpl;

    @Before
    public void mock_CategoryDaoImpl(){
        categoryDaoImpl = mock(CategoryDaoImpl.class);

        int id = 1;
        category = new Category(1, "水果");

        categories.add(category);

        when(categoryDaoImpl.getCategoryById(id)).thenReturn(category);
        when(categoryDaoImpl.getCategories()).thenReturn(categories);

        categoryServiceImpl = new CategoryServiceImpl();
        categoryServiceImpl.setCategoryDaoImpl(categoryDaoImpl);
    }

    @Test
    public void can_get_category_by_id(){
        assertThat(categoryServiceImpl.getCategoryById(1)).isEqualTo(category);
        verify(categoryDaoImpl).getCategoryById(1);
    }

    @Test
    public void can_insert_category(){
        categoryServiceImpl.insertCategory(category);
        verify(categoryDaoImpl).insertCategory(category);
    }

    @Test
    public void can_delete_category_by_id(){
        categoryServiceImpl.deleteCategoryById(9);
        verify(categoryDaoImpl).deleteCategoryById(9);
    }

    @Test
    public void can_update_category_by_id(){
        categoryServiceImpl.updateCategoryById(category);
        verify(categoryDaoImpl).updateCategoryById(category);
    }

    @Test
    public void can_get_all_categories(){
        assertThat(categoryServiceImpl.getCategories().size()).isEqualTo(1);
        verify(categoryDaoImpl).getCategories();
    }
}
