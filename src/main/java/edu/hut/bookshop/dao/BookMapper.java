package edu.hut.bookshop.dao;

import edu.hut.bookshop.pojo.Book;

import java.util.List;

public interface BookMapper {
    int deleteByBookId(Integer bookId);

    int insert(Book record);

    Book selectByBookId(Integer bookId);

    int updateByBookId(Book record);

    List<Book> selectAllByCategoryCode(String categoryCode);

    List<Book> selectByBooks(Book book);
}