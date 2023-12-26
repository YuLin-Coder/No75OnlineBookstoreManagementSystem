layui.use(['element', 'jquery', 'layer', 'laytpl','laypage','form','table'], function() {
	var $ = layui.jquery,
		layer = layui.layer,
		laytpl = layui.laytpl,
		laypage=layui.laypage,
		form=layui.form,
		element = layui.element,
		table = layui.table;
		
		//从url路径中获取书籍ID
		var str=window.location.pathname;
		var bookId=str.substring(str.lastIndexOf("/")+1);

		//立即购买提交按钮
		$("#buyNowBtn").click(function(){
			let data=[];
			let quantity=$("#quantity").val();
			if(quantity==0){
				return layer.msg('至少要一件才能购买哦',{icon:5});
			}
			let price=$("#price").text();
			data.push({
				'cartId':null,
				'bookId':bookId,
				'price':parseFloat(price),
				'quantity':quantity
			});
			console.log(data);
			order_submit_popup(data);
			
		});
		
		//添加购物车提交按钮
		$("#addCartBtn").click(function(){
			alert('点击了');
			let quantity=$("#quantity").val();
			if(quantity==0){
				return layer.msg('至少要购买一件才能添加购物车哦',{icon:5});
			}
			let price=$("#price").text();
			let data={
				'bookId':bookId,
				'price':parseFloat(price),
				'quantity':quantity
			};
			$.post('/cart/list', data, function (res) {
				if (res.code != 0) {
					return layer.msg(res.msg,{icon:2});
				}
				return layer.msg("添加成功,请前往购物车查看",{icon:1});
			});
		});
		
		//购买数量输入框监听
		window.check=function(obj,price){
			var qty=$(obj).val();
			if(qty<0||qty==''){
				layer.msg("输入必须大于或者等于0");
				$(obj).val(0);
				qty=0;
			}
			if(qty>10){
				layer.msg("每个用户限购10件");
				$(obj).val(10);
				qty=10;
			}
			$("#totalAccount").text("¥"+Math.floor(parseFloat(price*100 *qty))/100);
		}
});