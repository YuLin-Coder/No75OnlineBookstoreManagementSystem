package edu.hut.bookshop.controller;

import edu.hut.bookshop.exception.CustomizeException;
import edu.hut.bookshop.pojo.ShoppingCart;
import edu.hut.bookshop.pojo.User;
import edu.hut.bookshop.service.ShoppingCartService;
import edu.hut.bookshop.util.ResultCode;
import edu.hut.bookshop.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 购物城模块控制器
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    /**
     * 根据用户ID获取该用户的购物车
     * @param session
     * @return
     */
    @GetMapping("/list")
    public ResultVO getCartByUserId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<ShoppingCart> carts = shoppingCartService.getShoppingCartsByUserId(user.getUserId());
        return new ResultVO(ResultCode.SUCCESS, carts);
    }

    /**
     * 更新购物车的商品数量
     * @param cartId  购物车Id
     * @param quantity  商品购买数量
     * @return
     */
    @PutMapping("/list/{cartId}")
    public ResultVO updateCartItem(@PathVariable("cartId") Integer cartId,Integer quantity) {
        if(quantity<=0){
            throw new CustomizeException(ResultCode.FAILED,"购物数量必须大于0");
        }
        if(quantity>10){
            throw new CustomizeException(ResultCode.FAILED,"每件商品限购10件");
        }
        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(cartId);
        cart.setQuantity(quantity);
        shoppingCartService.updateShoppingCart(cart);
        return new ResultVO(ResultCode.SUCCESS);
    }

    /**
     * 添加到购物车请求处理
     *
     * @param cart 接收前台传来的参数
     * @return
     */
    @PostMapping("/list")
    public ResultVO addToShoppingCart(@Valid ShoppingCart cart, HttpSession session) {
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getUserId());
        shoppingCartService.addToShoppingCart(cart);
        return new ResultVO(ResultCode.SUCCESS);
    }

    /**
     * 删除前台传来的cartId的购物车项
     * @param cartId
     * @return
     */
    @DeleteMapping("/list/{cartId}")
    public ResultVO deleteCartItem(@PathVariable("cartId") Integer cartId) {
        shoppingCartService.deleteShoppingCartByCartId(cartId);
        return new ResultVO(ResultCode.SUCCESS);
    }

    /**
     * 批量删除购物车
     * @param cartIds
     * @return
     */
    @DeleteMapping("/list")
    public ResultVO deleteCartItem(@RequestBody int[] cartIds) {
        shoppingCartService.deleteShoppingCarts(cartIds);
        return new ResultVO(ResultCode.SUCCESS);
    }


}
