package edu.hut.bookshop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.hut.bookshop.pojo.Book;
import edu.hut.bookshop.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Description:
 * @Author: guozongchao
 * @Date: 2020/6/4 15:37
 */
@SpringBootTest
public class BookDisplayServiceTest {
    @Autowired
    private BookDisplayService  bookDisplayService;

    @Test
    public void testGetAllCategories() {
        List<Category> allCategories = bookDisplayService.getAllCategories();
        allCategories.stream().forEach(category -> {
            System.out.println(category);
        });
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = bookDisplayService.getAllBooks(1, 5);
        books.stream().forEach(book -> {
            System.out.println(book);
        });
        PageInfo pageInfo = new PageInfo(books);
        System.out.println(pageInfo.getTotal());
    }

    @Test
    public void testGetBooksByCategoryCode() {
        List<Book> books = bookDisplayService.getBooksByCategoryCode(1, 5,"novel");
        books.stream().forEach(book -> {
            System.out.println(book);
        });
        PageInfo pageInfo = new PageInfo(books);
        System.out.println(pageInfo.getTotal());
    }

    @Test
    public void testGetBookById() {
        Book book = bookDisplayService.getBookDetailsByBookId(5);
        System.out.println(book);
    }

    @Test
    public void testSearchBooksByName() {
        List<Book> books = bookDisplayService.searchBooksByBookName(1, 5, "光");
        books.stream().forEach(book -> {
            System.out.println(book);
        });
    }
}
