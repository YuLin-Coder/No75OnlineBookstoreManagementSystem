package edu.hut.bookshop.dao;

import edu.hut.bookshop.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByByCategoryCode(String categoryCode);

    int insert(Category record);

    Category selectByByCategoryCode(String categoryCode);

    int updateByCategoryCode(Category record);

    List<Category> selectAll();
}