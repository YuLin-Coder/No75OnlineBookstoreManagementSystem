package edu.hut.bookshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import edu.hut.bookshop.dao.CategoryMapper;
import edu.hut.bookshop.pojo.Category;
import edu.hut.bookshop.service.CategoryService;
@Service
public class CategoryServiceImpl implements  CategoryService{
    @Resource
    private CategoryMapper categoryMapper;
	@Override
	public int deleteByByCategoryCode(String categoryCode) {
		// TODO Auto-generated method stub
		int categories = categoryMapper.deleteByByCategoryCode(categoryCode);
		return categories;
	}

	@Override
	public int insert(Category record) {
		// TODO Auto-generated method stub
		int categories = categoryMapper.insert(record);
		return categories;
	}

	@Override
	public Category selectByByCategoryCode(String categoryCode) {
		// TODO Auto-generated method stub
		Category category = categoryMapper.selectByByCategoryCode(categoryCode);
		return category;
	}

	@Override
	public int updateByCategoryCode(Category record) {
		// TODO Auto-generated method stub
		int category = categoryMapper.updateByCategoryCode(record);
		return category;
	}

	@Override
	public List<Category> selectAll(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, limit);
		List<Category> categories = categoryMapper.selectAll();
		return categories;
	}

}
