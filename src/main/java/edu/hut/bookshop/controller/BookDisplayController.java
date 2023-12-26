package edu.hut.bookshop.controller;

import com.github.pagehelper.PageInfo;
import edu.hut.bookshop.pojo.Book;
import edu.hut.bookshop.pojo.Category;
import edu.hut.bookshop.service.BookDisplayService;
import edu.hut.bookshop.util.ResultCode;
import edu.hut.bookshop.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 首页商品展示模块控制器
 */
@Controller
@RequestMapping("/index")
public class BookDisplayController {

    @Autowired
    private BookDisplayService bookDisplayService;

    /**
     * 处理前台获取所有分类的请求
     * @return
     */
    @GetMapping("/category")
    @ResponseBody
    public ResultVO getCategories() {
        List<Category> categories = bookDisplayService.getAllCategories();
        return new ResultVO(ResultCode.SUCCESS,categories);
    }

    /**
     * 根据前台传来的categoryCode，响应对应的分类的书籍
     * 需要进行分页响应
     * 如果categoryCode为null,则响应所有书籍
     * @param categoryCode   分类代码
     * @param page           页码
     * @param limit          每页的数量
     * @return
     */
    @GetMapping("/books")
    @ResponseBody
    public ResultVO getBooksByCategoryCode(@RequestParam(required = false) String categoryCode, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        List<Book> books = bookDisplayService.getBooksByCategoryCode(page==null?1:page, limit==null?10:limit, categoryCode);
        PageInfo pageInfo = new PageInfo(books);  //获得分页信息
        return new ResultVO(ResultCode.SUCCESS, (int) pageInfo.getTotal(),books);
    }

    /**
     * 根据请求的bookId,返回该Id的书籍信息，响应Html视图
     * @param bookId
     * @return
     */
    @GetMapping("/books/details/{bookId}")
    public String bookDetailsView(@PathVariable("bookId") Integer bookId, Model model) {
        Book book = bookDisplayService.getBookDetailsByBookId(bookId);
        model.addAttribute("book", book);
        return "details";
    }

    /**
     * 首页根据书名搜索书籍
     * @param bookName
     * @return
     */
    @GetMapping("/books/search")
    @ResponseBody
    public ResultVO searchBook(@RequestParam(required = true) String bookName) {
        List<Book> books = bookDisplayService.searchBooksByBookName(1, 10, bookName);
        PageInfo pageInfo = new PageInfo(books);
        return new ResultVO(ResultCode.SUCCESS, (int) pageInfo.getTotal(), books);
    }



}
