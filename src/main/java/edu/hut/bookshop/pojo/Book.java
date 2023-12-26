package edu.hut.bookshop.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private Integer bookId;

    @NotBlank(message = "分类不能为空")
    private String categoryCode;

    @NotBlank(message = "分类不能为空")
    private String bookName;

    @NotBlank(message = "ISBN不能为空")
    private String isbn;

    @NotBlank(message = "作者不能为空")
    private String author;

    @NotBlank(message = "出版社不能为空")
    private String press;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Past(message = "出版日期只能在当前时间之前")
    private Date pubDate;

    private String image;

    private String description;

    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @NotNull(message = "库存不能为空")
    private Integer stock;

    private Date createTime;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", categoryCode='" + categoryCode + '\'' +
                ", bookName='" + bookName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", pubDate=" + pubDate +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", createTime=" + createTime +
                ", category=" + category +
                '}';
    }
}