package com.dharma.boot.dao.impl;

import com.dharma.boot.bean.Product;
import com.dharma.boot.dao.ProductDao;
import com.dharma.boot.utils.JdbcDaoImpl;
import com.dharma.boot.utils.Page;
import com.dharma.boot.utils.Sql;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProductDaoImpl extends JdbcDaoImpl implements ProductDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(Product product) {
		String sql = "insert into product (name, price) values (?, ?)";
		return jdbcTemplate.update(sql, product.getName(), product.getPrice());
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from product where id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updateById(Product product) {
		String sql = "update product set name=?, price=? where id=?";
		return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getId());
	}

	@Override
	public Product selectById(int id) {
		String sql = "select * from product where id=?";
//		return jdbcTemplate.queryForObject(sql, new RowMapper<Product>() {
//			@Override
//			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Product product = new Product();
//				product.setId(rs.getInt("id"));
//				product.setName(rs.getString("name"));
//				product.setPrice(rs.getDouble("price"));
//				return product;
//			}
//		}, id);
		return queryForObject(sql, Product.class, id);
	}

	@Override
	public Product selectByName(String name) {
		String sql = "select * from product where name=?";
		return queryForObject(sql, Product.class, name);
	}

	@Override
	public Page<Product> queryForPage(int pageCurrent, int pageSize, String name){
		// 确定参数
		/*String sql = "select * from product where name=?";
		return queryForPage(sql.toString(), pageCurrent, pageSize, Product.class, name);*/

		// 若name可能为空，则要进行判定，如下
		/*StringBuffer sql = new StringBuffer("select * from product where 1");
		if(!StringUtils.isNullOrEmpty(name)){
			// Sql.checkSql 的作用是防止sql注入
			sql.append(" and name = '").append(Sql.checkSql(name)).append("' ");
		}
		return queryForPage(sql.toString(), pageCurrent, pageSize, Product.class);*/

		// 若要like查询，如下
		StringBuffer sql = new StringBuffer("select * from product where 1");
		if(!StringUtils.isNullOrEmpty(name)){
			sql.append(" and name like '%").append(Sql.checkSql(name)).append("%' ");
		}
		return queryForPage(sql.toString(), pageCurrent, pageSize, Product.class);
	}
}
