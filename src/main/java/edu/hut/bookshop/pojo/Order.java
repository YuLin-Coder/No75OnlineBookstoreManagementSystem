package edu.hut.bookshop.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;

    private Integer userId;

    @NotBlank(message = "收货人姓名不能为空")
    private String consigneeName;

    @NotBlank(message = "收货地址不能为空")
    private String address;

    @NotBlank(message = "邮政编码不能为空")
    @Pattern(regexp = "\\d{6}",message = "邮政编码格式错误")
    private String zip;

    @NotBlank(message = "联系方式不能为空")
    @Pattern(regexp = "1[3456789]\\d{9}",message = "手机号码格式错误")
    private String phoneNumber;

    private Boolean status;

    private Date createTime;

    private User user_info;
    private List<OrderItem> orderItems;
    private List<ShoppingCart> carts;

    public List<ShoppingCart> getCarts() {
        return carts;
    }

    public void setCarts(List<ShoppingCart> carts) {
        this.carts = carts;
    }

    public User getUser_info() {
        return user_info;
    }

    public void setUser_info(User user_info) {
        this.user_info = user_info;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", consigneeName='" + consigneeName + '\'' +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", user_info=" + user_info +
                ", orderItems=" + orderItems +
                '}';
    }
}