package edu.hut.bookshop.pojo;

import java.math.BigDecimal;

public class OrderItem {
    private Integer orderItemId;

    private Integer orderId;

    private Integer bookId;

    private BigDecimal price;

    private Integer quantity;
    private Book book_info;

    public Book getBook_info() {
        return book_info;
    }

    public void setBook_info(Book book_info) {
        this.book_info = book_info;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", bookId=" + bookId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", book_info=" + book_info +
                '}';
    }
}