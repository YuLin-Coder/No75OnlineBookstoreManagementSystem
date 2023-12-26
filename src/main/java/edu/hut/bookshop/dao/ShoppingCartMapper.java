package edu.hut.bookshop.dao;

import edu.hut.bookshop.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper {
    int deleteByCartId(Integer cartId);

    int insert(ShoppingCart record);

    ShoppingCart selectByCartId(Integer cartId);

    int updateByByCartId(ShoppingCart record);

    int updateByUserIdAndBookId(ShoppingCart record);

    List<ShoppingCart> selectByUserId(Integer userId);

    ShoppingCart selectByUserIdAndBookId(Integer userId,Integer bookId);

    int deleteByUserIdAndBookId(Integer userId, Integer bookId);

}