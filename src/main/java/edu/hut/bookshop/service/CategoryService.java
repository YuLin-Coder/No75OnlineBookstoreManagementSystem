package edu.hut.bookshop.service;

import java.util.List;

import edu.hut.bookshop.pojo.Category;

public interface CategoryService {
	/**
	 * 通过分类Code删除分类
	 * @param categoryCode
	 */
	int deleteByByCategoryCode(String categoryCode);
	/**
     * 增加分类
     * @param record
     */
	int insert(Category record);
	 /**
     * 通过分类Code查找分类
     * @param categoryCode
     */
	Category selectByByCategoryCode(String categoryCode);
	 /**
     * 更新分类
     * @param record
     */
	int updateByCategoryCode(Category record);

	/**
	 * 打印全部分类
	 */
	List<Category> selectAll(Integer page, Integer limit);
}
