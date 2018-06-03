package com.dharma.boot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sql {
	private Sql() {
	}

	// 检测sql，防止sql注入
	public static String checkSql(String sql) {
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("\\|");
		for (String anInj_stra : inj_stra) {
			if (sql.indexOf(anInj_stra) >= 0) {
				return "";
			}
		}
		return sql;
	}

	public static int countTotalPage(final int totalCount, final int pageSize) {
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		} else {
			return totalCount / pageSize + 1;
		}
	}

	public static int checkPageCurrent(int totalCount, int pageSize, int pageCurrent) {
		int totalPage = countTotalPage(totalCount, pageSize); // 最大页数
		if (pageCurrent > totalPage) {
			if (totalPage < 1) {
				return 1;
			}
			return totalPage;
		} else if (pageCurrent < 1) {
			return 1;
		} else {
			return pageCurrent;
		}
	}

	public static int checkPageSize(int pageSize) {
		if (pageSize > Page.MAX_PAGE_SIZE) {
			return Page.MAX_PAGE_SIZE;
		} else if (pageSize < 1) {
			return Page.DEFAULT_PAGE_SIZE;
		} else {
			return pageSize;
		}
	}

	private static int countOffset(final int pageCurrent, final int pageSize) {
		return (pageCurrent - 1) * pageSize;
	}

//	SELECT column_name(s)
//	FROM table_name
//	WHERE condition
//	LIMIT number;
//  OFFSET offset #start from offset+1
	public static String limitSql(int totalCount, int pageCurrent, int pageSize) {
		pageCurrent = checkPageCurrent(totalCount, pageSize, pageCurrent);
		pageSize = checkPageSize(pageSize);
		return " limit " + countOffset(pageCurrent, pageSize) + "," + pageSize;
	}

	public static String countSql(String sql) {
		String countSql = sql.substring(sql.toLowerCase().indexOf("from")); // 去除第一个from前的内容
		return "select count(*) " + removeOrderBy(countSql);
	}

	private static String removeOrderBy(String sql) {
		Pattern pat = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher mc = pat.matcher(sql);
		StringBuffer strBuf = new StringBuffer();
		while (mc.find()) {
			mc.appendReplacement(strBuf, "");
		}
		mc.appendTail(strBuf);
		return strBuf.toString();
	}
}
