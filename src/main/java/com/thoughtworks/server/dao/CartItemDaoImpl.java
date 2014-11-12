package com.thoughtworks.server.dao;

import com.thoughtworks.server.model.CartItem;
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
public class CartItemDaoImpl implements CartItemDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CartItem getCartItemById(int id) {
        String sql = "SELECT cartItems.*,  items.*, categories.* FROM categories, cartItems, items WHERE cartItems.cartItem_id = ? " +
                "AND items.item_id = cartItems.cartItem_itemId AND categories.category_id = items.item_categoryId";
        return jdbcTemplate.queryForObject(sql, new RowMapper<CartItem>() {
            @Override
            public CartItem mapRow(ResultSet rs, int i) throws SQLException {
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                Item item = new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("item_price"),
                        rs.getString("item_unit"), category);

                return new CartItem(rs.getInt("cartItem_id"), item, rs.getInt("cartItem_num"));
            }
        }, id);
    }

    @Override
    public List<CartItem> getCartItems() {
        String sql = "SELECT cartItems.*,  items.*, categories.* FROM categories, cartItems, items WHERE " +
                "items.item_id = cartItems.cartItem_itemId AND categories.category_id = items.item_categoryId";
        return jdbcTemplate.query(sql, new RowMapper<CartItem>() {
            @Override
            public CartItem mapRow(ResultSet rs, int i) throws SQLException {
                Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                Item item = new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("item_price"),
                        rs.getString("item_unit"), category);

                return new CartItem(rs.getInt("cartItem_id"), item, rs.getInt("cartItem_num"));
            }
        });
    }

    @Override
    public void insertCartItem(CartItem cartItem) {
        String sql = "INSERT INTO cartItems VALUES(null, ?, ?)";
        jdbcTemplate.update(sql, cartItem.getItem().getId(), cartItem.getNum());
    }

    @Override
    public void deleteCartItemById(int id) {
        String sql = "DELETE FROM cartItems WHERE cartItem_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateCartItemById(CartItem cartItem) {
        String sql = "UPDATE cartItems SET cartItem_itemId = ?, cartItem_num = ? WHERE cartItem_id = ?";
        jdbcTemplate.update(sql, cartItem.getItem().getId(), cartItem.getNum(), cartItem.getId());
    }
}
