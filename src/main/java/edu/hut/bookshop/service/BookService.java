package edu.hut.bookshop.service;

import java.util.List;

import edu.hut.bookshop.pojo.Book;

/**
 * @Description: 书籍管理模块接口
 */
   public interface BookService {
	/**
	 * 通过书籍ID获得书籍
	 * @param bookId
	 */
    Book bookSearchById(Integer bookId);
    /**
     * 通过书籍分类查找全部分类书籍  (修改成分页查询)
     * @param catrgoryCode
     */
    List<Book> bookSearchByCode(String catrgoryCode ,Integer page,Integer limit);
    /**
     * 通过书籍ID删除书籍
     * @param bookId
     */
    int bookDeleteSearchById(Integer bookId);
    /**
     * 增加书籍
     * @param record
     */
    int bookInsert(Book record);
    /**
     * 更新书籍
     * @param record
     */
    int bookUpdate(Book record);


    /**
     * 多条件查询书籍  
     * @param book
     * @return
     */
    List<Book> searchBooks(Book book,Integer page,Integer limit);
}
