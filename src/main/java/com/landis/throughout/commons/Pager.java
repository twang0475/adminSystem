/**
 * 
 */
package com.landis.throughout.commons;

public class Pager {

	/*当前页数，页数从1开始*/
	private int page = 1;

	/*总页数,页数从1开始*/
	private int pageCount = 1;

	/*总记录数*/
	private int count = 0;

	/*每页显示多少记录*/
	private int pageSize;

	/*默认每页显示多少记录*/
	public static final Integer DEFAULT_PAGE_SIZE = 20;

	/**
	 * 默认构造函数
	 */
	public Pager() {
	}

	/**
	 * 
	 * @param count 总记录数
	 * @param page  当前第几页
	 * 每页显示默认的记录数
	 */
	public Pager(int count, Integer page) {
		this(count, page, DEFAULT_PAGE_SIZE.intValue());
	}

	/**
	 * 
	 * @param count  总数
	 * @param page   当前第几页
	 * @param pageSize 每页显示多少条
	 */
	public Pager(int count, Integer page, int pageSize) {

		if (page == null || page.intValue() < 1) {
			page = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (count < 0) {
			count = 0;
		}
		this.page = page;
		this.pageSize = pageSize;
		this.count = count;
		calPages();
	}

	/**
	 * 重新计算分页
	 */
	public void refresh() {
		if (page < 1) {
			page = 1;
		}
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		if (count < 0) {
			count = 0;
		}
		calPages();
	}

	/**
	 * 计算分页
	 */
	private void calPages() {
		int rem = count % pageSize;
		if (rem == 0) {
			pageCount = count == 0 ? 1 : count / pageSize;
		} else {
			pageCount = (count - rem) / pageSize + 1;
		}
		/*		if (page > pageCount) {
					page = pageCount;
				}*/
	}

	/**
	 * 是否存在上一页
	 * @return
	 */
	public boolean hasPrevious() {
		return (pageCount > 1 && page > 1);
	}

	/**
	 * 是否存在下一页
	 * @return
	 */
	public boolean hasNext() {
		return (pageCount >= 1 && page < pageCount);
	}

	/**
	 * 判断是否为当前页
	 * @return
	 */
	public boolean isCurrentPage(int i) {
		if (i < 1) {
			i = 1;
		}
		return page == i;
	}

	/**
	 * 判断是否为首页
	 * @return
	 */
	public boolean isHead() {
		return page == 1;
	}

	/**
	 * 判断是否为尾页
	 * @return
	 */
	public boolean isTail() {
		return page == pageCount;
	}

	/**
	 * 得到当前页的起始位置(对应查询的offset)
	 * @return
	 */
	public int getCurrentPageOffset() {
		return (page - 1) * getPageSize();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	/**
	 * 每页显示多少条记录(对应查询的size)
	 */
	public int getPageSize() {
		if (pageSize == 0) {
			this.pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pager pager = new Pager(2, 2);
		int pages = pager.getPageCount();
		int start = pager.getCurrentPageOffset();
		System.out.println("offset:" + start);
		System.out.println("pages:" + pages);
		System.out.println("page:" + pager.getPage());
		System.out.println("size:" + pager.getPageSize());
		System.out.println("isHead:" + pager.isHead());
		System.out.println("isTail:" + pager.isTail());
		System.out.println("hasePrevious():" + pager.hasPrevious());
		System.out.println("haseNext():" + pager.hasNext());
	}
}
