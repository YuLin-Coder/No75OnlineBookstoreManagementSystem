layui.use(['element', 'jquery', 'layer', 'laytpl', 'laypage', 'form'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        laytpl = layui.laytpl,
        laypage = layui.laypage,
        form = layui.form,
        element = layui.element;


    //订单信息填写弹窗,并提交订单
    /**
     * 参数orderItemData的要求[{'cartId':'','bookId':'','price':'','quantity':''}]
     * @param {Object} orderItemData
     */
    window.order_submit_popup = function (orderItemData) {
        var form_str = '<form class="layui-form" lay-filter="orderVal" style="margin: 20px"> '
            + '<div class="layui-form-item"> '
            + '<label class="layui-form-label">收货人</label> '
            + ' <div class="layui-input-block"> '
            + '<input type="text" name="consigneeName" required  lay-verify="required" placeholder="请输入收货人姓名" autocomplete="off" class="layui-input"></div> '
            + '</div><div class="layui-form-item layui-form-text"> '
            + '<label class="layui-form-label">收货地址</label><div class="layui-input-block"> '
            + '<textarea name="address" placeholder="请输入收货地址" class="layui-textarea"></textarea>'
            + '</div></div><div class="layui-form-item"> '
            + '<label class="layui-form-label">邮政编号</label><div class="layui-input-block"> '
            + '<input type="text" name="zip" required  lay-verify="required" placeholder="请输入邮政编号" autocomplete="off" class="layui-input"> '
            + '</div></div><div class="layui-form-item"> '
            + '<label class="layui-form-label">手机号码</label><div class="layui-input-block"> '
            + '<input type="text" name="phoneNumber" required  lay-verify="required" placeholder="请输入手机号码" autocomplete="off" class="layui-input"> '
            + '</div></div>';

        $.getJSON("/checkLoggedIn", function (result) {
            if(result.code!=0){
                return layer.msg(result.msg, {icon: 2});
            }
            return layer.open({
                type:1,
                title: '订单信息提交',
                content: form_str,
                area: ['500px'],
                btn: ['提交订单', '取消'],
                btnAlign: 'c',
                yes: function (index) {
                    var orderData = form.val("orderVal");
                    console.log(orderItemData);
                    orderData['orderItems'] = orderItemData;
                    console.log(orderData);
                    $.ajax({
                        url: '/order/submit',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify(orderData),
                        dataType: 'json',
                        success: function (res) {
                            if (res.code != 0) {
                                return layer.msg(res.msg, {icon: 2});
                            }
                            return layer.msg("订单提交成功", {icon: 1, time: 1500}, function () {
                                window.location.href=res.data;
                            });
                        },
                        error: function () {
                            return layer.msg("服务器错误,请稍后再试", {icon: 2});
                        }
                    });
                },
                btn2: function (index) {
                    layer.close(index);
                },
                success: function () {
                    form.render();
                }
            });
        });
    }


    /**
     * 计算总价格
     * @param {Object} price
     * @param {Object} quantity
     */
    window.calculate = function (price, quantity) {
        return Math.floor(parseFloat(price * 100 * quantity)) / 100;
    }

});