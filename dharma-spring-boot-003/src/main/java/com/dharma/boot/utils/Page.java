/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.dharma.boot.utils;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = -5764853545343945831L;

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int MAX_PAGE_SIZE = 1000;

	private List<T> list;

	private int totalCount;

	private int totalPage;

	private int pageCurrent;

	private int pageSize;

	private String orderField;

	private String orderDirection;

	public Page() {
	}

	public Page(int totalCount, int totalPage, int pageCurrent, int pageSize, List<T> list) {
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.pageCurrent = pageCurrent;
		this.pageSize = pageSize;
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

}
