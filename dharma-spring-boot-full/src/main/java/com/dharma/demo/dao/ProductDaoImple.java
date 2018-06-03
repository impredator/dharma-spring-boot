package com.dharma.demo.dao;

import com.dharma.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class ProductDaoImple implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Product product) {
        String sql = "insert into product(name, price) values(?,?)";
        return jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from product where id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateById(Product product) {
        String sql = "update product set name=?, price=? where id=? ";
        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());
    }

    @Override
    public Product getById(int id) {
        String sql = "select * from product where id=?";
        return queryForObject(sql, Product.class, id);
    }

    @Override
    public Product getByName(String name) {
        String sql = "select * from product where name=?";
        return queryForObject(sql, Product.class, name);
    }

    private <T> T queryForObject(String sql, Class<T> cls, Object... args) {
        Assert.hasText(sql, "sql not empty");
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(cls), args);
    }

}
