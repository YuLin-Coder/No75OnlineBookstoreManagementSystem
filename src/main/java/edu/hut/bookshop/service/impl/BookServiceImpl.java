package edu.hut.bookshop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import edu.hut.bookshop.dao.BookMapper;
import edu.hut.bookshop.pojo.Book;
import edu.hut.bookshop.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
	@Override
	public Book bookSearchById(Integer bookId) {
		// TODO Auto-generated method stub
		Book books=bookMapper.selectByBookId(bookId);
		return books;
	}

	//(修改成分页查询)
	@Override
	public List<Book> bookSearchByCode(String catrgoryCode,Integer page,Integer limit) {
			PageHelper.startPage(page, limit);
	      List<Book> books = bookMapper.selectAllByCategoryCode(catrgoryCode);
	   
	      return books;
		// TODO Auto-generated method stub

	}

	@Override
	public int bookDeleteSearchById(Integer bookId) {
		// TODO Auto-generated method stub
		int books=bookMapper.deleteByBookId(bookId);
		return books;
	}

	@Override
	public int bookInsert(Book record) {
		// TODO Auto-generated method stub
		int books=bookMapper.insert(record);
                return books;
	}

	@Override
	public int bookUpdate(Book record) {
		// TODO Auto-generated method stub
		int books=bookMapper.updateByBookId(record);
		return books;

	}


	/**
	 * 多条件查询书籍 
	 * @param book
	 * @return
	 */
	@Override
	public List<Book> searchBooks(Book book, Integer page, Integer limit) {
		PageHelper.startPage(page,limit);
		List<Book> books = bookMapper.selectByBooks(book);
		return books;
	}
}
