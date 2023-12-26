package edu.hut.bookshop.service;

import edu.hut.bookshop.pojo.Book;
import edu.hut.bookshop.pojo.Category;

import java.util.List;

/**
 * @Description: 首页书籍书籍展示模块接口
 */
public interface BookDisplayService {

    List<Category> getAllCategories();

    List<Book> getAllBooks(Integer page,Integer limit);

    List<Book> getBooksByCategoryCode(Integer page, Integer limit,String categoryCode);

    Book getBookDetailsByBookId(Integer bookId);

    List<Book> searchBooksByBookName(Integer page, Integer limit,String bookName);
}

