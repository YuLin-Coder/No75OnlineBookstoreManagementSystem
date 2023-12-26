package edu.hut.bookshop.service.impl;

import com.github.pagehelper.PageHelper;
import edu.hut.bookshop.dao.BookDisplayMapper;
import edu.hut.bookshop.dao.BookMapper;
import edu.hut.bookshop.dao.CategoryMapper;
import edu.hut.bookshop.pojo.Book;
import edu.hut.bookshop.pojo.Category;
import edu.hut.bookshop.service.BookDisplayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 首页书籍展示模块实现类
 */
@Service
public class BookDisplayServiceImpl implements BookDisplayService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private BookDisplayMapper bookDisplayMapper;


    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Book> getAllBooks(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Book> books = bookMapper.selectAllByCategoryCode(null);
        return books;
    }

    @Override
    public List<Book> getBooksByCategoryCode(Integer page, Integer limit,String categoryCode) {
        PageHelper.startPage(page,limit);
        List<Book> books = bookMapper.selectAllByCategoryCode(categoryCode);
        return books;
    }

    @Override
    public Book getBookDetailsByBookId(Integer bookId) {
        return bookMapper.selectByBookId(bookId);
    }

    @Override
    public List<Book> searchBooksByBookName(Integer page, Integer limit,String bookName) {
        PageHelper.startPage(page, limit);
        List<Book> books = bookDisplayMapper.fuzzyQueryByBookName(bookName);
        return books;
    }
}
