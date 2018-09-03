package com.crystal.blog.common.bean.response.base;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfo<T> implements Serializable {

	private static final long serialVersionUID = -3548815939187415015L;

	/**
	 * 页面大小
	 */
	private int pageSize;

	/**
	 * 当前页
	 */
	private int pageNum;

	/**
	 * 总页数
	 */
	private int pages;

	/**
	 * 数据结果
	 */
	private List<T> result = new ArrayList<>();

	/**
	 * 总数量
	 */
	private long total;

	@Override
	public String toString() {
		return "Page{" +
				"pageSize=" + pageSize +
				", pageNum=" + pageNum +
				", pages=" + pages +
				", result=" + result +
				", total=" + total +
				'}';
	}
}
