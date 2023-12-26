layui.use(['element', 'jquery', 'layer', 'laytpl', 'laypage', 'form'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        laytpl = layui.laytpl,
        laypage = layui.laypage,
        form = layui.form,
        element = layui.element;


    getShoppingCart();

    /**
     * 异步获取用户购物车信息
     */
    function getShoppingCart() {
        $("#shopping-carts").html("");  //重新渲染前清空
        var queryStr = window.location.search;
        var userId = queryStr.substring(queryStr.lastIndexOf("=") - 1);
        //获取用户购物车信息
        $.getJSON("/cart/list", function (res) {
            if(res.code!=0){
               return layer.msg(res.msg,{icon:2});
            }
            if (res.data.length== 0) {
                $("#shopping-carts").html('<div class="cart-tip">你的购物车空空如也~快去剁剁手吧</div>');
                $("#buyBtn").hide();
                $("#deleteSelectedBtn").hide();
                return;
            }
            laytpl($("#shopping-carts-tpl").html()).render(res.data, function (html) {
                $("#shopping-carts").html(html);
            });
            form.render('checkbox');
        });
    }


    //监听全选复选
    form.on('checkbox(selectAll)', function (data) {

        if (data.elem.checked) {
            //全选
            $.each($("input[name='cartId']"), function (index, item) {
                $(item)[0].checked = true;
            });
            form.render('checkbox');
        } else {
            //取消全选
            $.each($("input[name='cartId']"), function (index, item) {
                $(item)[0].checked = false;
            });
            form.render('checkbox');
        }
    });
    //监听除全选以外的复选框，当所有非全选框复选框选中时，将全选框也选中
    form.on('checkbox(selectOne)', function (data) {
        var flag = true;
        $.each($("input[name='cartId']"), function (index, item) {
            if (!$(item)[0].checked) {
                flag = false;
            }
        });
        if (flag) {
            $("input[name='selectAll']")[0].checked = true;
        } else {
            $("input[name='selectAll']")[0].checked = false;
        }
        form.render('checkbox');
    });


    /**
     * 立即购买
     */
    $("#buyBtn").click(function () {
        let data = [];
        //获取选中的商品
        $.each($("input[name='cartId']"), function (index, item) {
            let cartItem = {};  //构造数据
            if ($(item)[0].checked) {
                //获取选中复选框的属性
                let cartId = $(item).val();
                cartItem['cartId'] = cartId;
                //书籍ID
                cartItem['bookId'] = $(item).attr("c-bookId");
                //价格
                cartItem['price'] = $(item).attr("c-price");
                //购买数量
                cartItem['quantity'] = $("#quantity-" + cartId).val();
                data.push(cartItem);
            }
        });
        if(data.length==0){
           return  layer.msg("至少要选中一项才能购买哦",{icon: 2});
        }
        //弹出填写订单信息框
        order_submit_popup(data);
    });


    /**
     * 删除单个购物车
     * @param cartId
     */
    window.deleteByCartId = function (cartId) {
        $.ajax({
            url:'/cart/list/'+cartId,
            type:'delete',
            dataType:'json',
            success:function (res) {
                if (res.code != 0) {
                    return layer.msg(res.msg,{icon:2});
                }
                return layer.msg("删除成功", {icon: 1, time: 1300}, function () {
                    //重新获取数据
                    getShoppingCart();
                });
            },
            error:function () {
                return layer.msg("服务器出错啦",{icon:2});
            }
        });
    }


    /**
     * 清空选中购物车
     */
    $("#deleteSelectedBtn").click(function () {
        let data = [];
        $.each($("input[name='cartId']"), function (index, item) {
            if ($(item)[0].checked) {
                //获取选中复选框的属性
                data.push($(item).val());
            }
        });
        $.ajax({
            url:'/cart/list',
            type:'delete',
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType:'json',
            success:function (res) {
                if (res.code != 0) {
                    return layer.msg(res.msg,{icon:2});
                }
                return layer.msg("删除成功", {icon: 1, time: 1300}, function () {
                    //重新获取数据
                    getShoppingCart();
                });
            },
            error:function () {
                return layer.msg("服务器出错啦",{icon:2});
            }
        });
    });

    /**
     * 购物车输入框的输入验证和计算总金额，请求更新购物车
     * @param {Object} obj 输入框对象
     * @param {Object} cartId
     * @param {Object} price
     */
    window.checkAndCalculate = function (obj, cartId, price) {
        var qty = $(obj).val();
        if (qty == '') {
            $(obj).val(1);
            qty=1;
        }
        $("#total-" + cartId).text(calculate(price, qty));
        $.ajax({
            url:'/cart/list/'+cartId,
            type:'put',
            data:{quantity:qty},
            dataType:'json',
            success:function (res) {
                if(res.code!=0){
                    return layer.msg(res.msg,{icon:2});
                }
            },
            error:function () {
                return layer.msg("服务器出错啦",{icon:2});
            }
        });
    }

});