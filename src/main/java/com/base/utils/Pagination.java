package com.base.utils;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.StringUtils;

/**
 * 
 * 分页工具类
 * 
 */
public class Pagination extends JdbcDaoSupport {
	public static final int NUMBERS_PER_PAGE = 10;
	// 一页显示的记录数
	private int numPerPage;
	// 记录总数
	private int totalRows;
	// 总页数
	private int totalPages;
	// 当前页码
	private int currentPage;
	// 起始行数
	private int startIndex;
	// 结束行数
	private int lastIndex;
	// 结果集存放List
	private List resultList;
	// JdbcTemplate jTemplate
	private JdbcTemplate jTemplate;

	/**
	 * 每页显示10条记录的构造函数,使用该函数必须先给Pagination设置currentPage，jTemplate初值
	 * 
	 * @param sql
	 *            oracle语句
	 */
	public Pagination(String sql) {
		if (jTemplate == null) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");
		} else if (sql.equals("")) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");
		}
		new Pagination(sql, currentPage, NUMBERS_PER_PAGE, jTemplate);
	}

	/**
	 * 分页构造函数
	 * 
	 * @param sql
	 *            根据传入的sql语句得到一些基本分页信息
	 * @param currentPage
	 *            当前页
	 * @param numPerPage
	 *            每页记录数
	 * @param jTemplate
	 *            JdbcTemplate实例
	 */
	public Pagination(String sql, int currentPage, int numPerPage,
			JdbcTemplate jTemplate) {
		if (jTemplate == null) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");
		} else if (sql == null || sql.equals("")) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");
		}
		// 设置每页显示记录数
		setNumPerPage(numPerPage);
		// 设置要显示的页数
		setCurrentPage(currentPage);
		// 计算总记录数
		StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
		totalSQL.append(sql);
		totalSQL.append(" ) totalTable ");
		// 给JdbcTemplate赋值
		setJdbcTemplate(jTemplate);
		// 总记录数
		setTotalRows(getJdbcTemplate().queryForInt(totalSQL.toString()));
		// 计算总页数
		setTotalPages();
		// 计算起始行数
		setStartIndex();
		// 计算结束行数
		setLastIndex();

		// 装入结果集
		setResultList(getJdbcTemplate().queryForList(
				getOraclePageSQL(sql, startIndex, lastIndex)));
	}

	/**
	 * 构造 Oracle数据分页SQL
	 * 
	 * @param queryString
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	private String getOraclePageSQL(String queryString, Integer startIndex,
			Integer endIndex) {
		if (StringUtils.isEmpty(queryString)) {
			return null;
		}
		String endSql = "SELECT * FROM (SELECT rOraclePageSQL.*,ROWNUM RN FROM ("
				+ queryString
				+ ") rOraclePageSQL WHERE ROWNUM <="
				+ endIndex
				+ ") where RN>" + startIndex;
		return endSql;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	// 计算总页数
	public void setTotalPages() {
		if (totalRows % numPerPage == 0) {
			this.totalPages = totalRows / numPerPage;
		} else {
			this.totalPages = (totalRows / numPerPage) + 1;
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (currentPage - 1) * numPerPage;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public JdbcTemplate getJTemplate() {
		return jTemplate;
	}

	public void setJTemplate(JdbcTemplate template) {
		jTemplate = template;
	}

	// 计算结束时候的索引
	public void setLastIndex() {
		if (totalRows < numPerPage) {
			this.lastIndex = totalRows;
		} else if ((totalRows % numPerPage == 0)
				|| (totalRows % numPerPage != 0 && currentPage < totalPages)) {
			this.lastIndex = currentPage * numPerPage;
		} else if (totalRows % numPerPage != 0 && currentPage == totalPages) {// 最后一页
			this.lastIndex = totalRows;
		}
	}

}
