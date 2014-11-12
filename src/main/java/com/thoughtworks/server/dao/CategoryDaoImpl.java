package com.thoughtworks.server.dao;

import com.thoughtworks.server.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int i) throws SQLException {
                return new Category(rs.getInt("category_id"), rs.getString("category_name"));
            }
        }, id);
    }

    @Override
    public void insertCategory(Category category) {
        String sql = "INSERT INTO categories VALUES(null, ?)";
        jdbcTemplate.update(sql, category.getName());
    }

    @Override
    public void deleteCategoryById(int id) {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateCategoryById(Category category) {
        String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";
        jdbcTemplate.update(sql, category.getName(), category.getId());
    }

    @Override
    public List<Category> getCategories() {
        String sql = "SELECT * FROM categories";
        return jdbcTemplate.query(sql, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int i) throws SQLException {
                return new Category(rs.getInt("category_id"), rs.getString("category_name"));
            }
        });
    }
}
