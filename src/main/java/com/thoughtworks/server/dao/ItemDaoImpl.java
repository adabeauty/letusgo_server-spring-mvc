package com.thoughtworks.server.dao;

import com.thoughtworks.server.model.Category;
import com.thoughtworks.server.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Item getItemById(int id) {
        String sql = "SELECT items.*, categories.* FROM items, categories WHERE item_id = ? AND item_categoryId = category_id";

        return jdbcTemplate.queryForObject(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int i) throws SQLException {
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                return new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("item_price"),
                        rs.getString("item_unit"),category);
            }
        }, id);
    }

    @Override
    public void insertItem(Item item) {
        String sql = "INSERT INTO items VALUES(null, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getName(), item.getPrice(), item.getUnit(), item.getCategory().getId());
    }

    @Override
    public void updateItemById(Item item) {
        String sql = "UPDATE items SET item_name = ?, item_price = ?, item_unit = ?, item_categoryId = ? WHERE item_id = ?";
        jdbcTemplate.update(sql, item.getName(), item.getPrice(), item.getUnit(), item.getCategory().getId(), item.getId());
    }

    @Override
    public void deleteItemById(int id) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Item> getItems() {
        String sql = "SELECT items.*, categories.* FROM items, categories WHERE item_categoryId = category_id";

        return jdbcTemplate.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int i) throws SQLException {
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                return new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("item_price"),
                        rs.getString("item_unit"),category);
            }
        });
    }
}
