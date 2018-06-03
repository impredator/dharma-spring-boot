package com.dharma.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

public abstract class JdbcDaoImpl {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public Long getLastId() {
		return jdbcTemplate.queryForObject("select last_insert_id() as id", Long.class);
	}

	public <T> T queryForObject(String sql, Class<T> clazz, Object... args) {
		Assert.hasText(sql, "sql not empty");
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), args);
	}

	public <T> List<T> queryForObjectList(String sql, Class<T> clazz, Object... args) {
		Assert.hasText(sql, "sql not empty");
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<>(clazz));
	}

	public Page<Map<String, Object>> queryForPage(String sql, int pageCurrent, int pageSize, Object... args) {
		Assert.hasText(sql, "sql not empty");
		Assert.isTrue(pageCurrent >= 1, "pageNo > 1");
		String sqlCount = Sql.countSql(sql);
		int count = jdbcTemplate.queryForObject(sqlCount, Integer.class, args);
		pageCurrent = Sql.checkPageCurrent(count, pageSize, pageCurrent);
		pageSize = Sql.checkPageSize(pageSize);
		int totalPage = Sql.countTotalPage(count, pageSize);
		String sqlList = sql + Sql.limitSql(count, pageCurrent, pageSize);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlList, args);
		return new Page<>(count, totalPage, pageCurrent, pageSize, list);
	}

	/**
	 * 分页，jdbcTemplate 不支持like是定义，只能拼装
	 */
	public <T> Page<T> queryForPage(String sql, int pageCurrent, int pageSize, Class<T> clazz, Object... args) {
		Assert.hasText(sql, "sql 语句不能为空");
		Assert.isTrue(pageCurrent >= 1, "pageNo 必须大于等于1");
		Assert.isTrue(clazz != null, "clazz 不能为空");
		String sqlCount = Sql.countSql(sql);
		int count = jdbcTemplate.queryForObject(sqlCount, Integer.class, args);
		pageCurrent = Sql.checkPageCurrent(count, pageSize, pageCurrent);
		pageSize = Sql.checkPageSize(pageSize);
		int totalPage = Sql.countTotalPage(count, pageSize);
		String sqlList = sql + Sql.limitSql(count, pageCurrent, pageSize);
		List<T> list = jdbcTemplate.query(sqlList, new BeanPropertyRowMapper<T>(clazz), args);
		return new Page<T>(count, totalPage, pageCurrent, pageSize, list);
	}

}
