layui.use(['element', 'jquery', 'layer', 'laytpl', 'laypage', 'form', 'table'], function() {
	var $ = layui.jquery,
		layer = layui.layer,
		laytpl = layui.laytpl,
		laypage = layui.laypage,
		form = layui.form,
		element = layui.element,
		table = layui.table;

	var orderTb=table.render({
		elem: '#order_tb',
		url: '/order/list',
		cols: [
			[{
				field: 'orderId',
				title: '订单编号',
				width: 120,
				fixed: 'left',
				unresize: true,
				sort: true
			}, {
				field: 'consigneeName',
				title: '收货人姓名',
				width: 120
			}, {
				field: 'address',
				title: '收货地址',
				width: 220
			}, {
				field: 'zip',
				title: '邮政编码',
				width: 100,
				sort: true
			}, {
				field: 'phoneNumber',
				title: '联系方式',
				width: 120
			}, {
				field: 'status',
				title: '订单状态',
				width: 90,
				templet: function(res) {
					if (res.status) {
						return '<span class="layui-badge layui-bg-blue">已发货</span>';
					}
					return '<span class="layui-badge">待发货</span>';
				}
			}, {
				field: 'createTime',
				title: '创建时间',
				width: 120
			}, {
				fixed: 'right',
				title: '操作',
				toolbar: '#order_tb_bar',
				width: 200
			}]
		],
		page: true,
		height: 450
	});


	//监听行工具事件
	table.on('tool(order_tb)', function(obj) {
		var data = obj.data;
		if (obj.event === 'del') {
			layer.confirm('真的删除行么', function(index) {
				$.ajax({
					url:'/order/list/'+data.orderId,
					type:'delete',
					dataType:'json',
					success:function (res) {
						if(res.code!=0){
							return layer.msg(res.msg, {icon: 2});
						}
						return layer.msg("删除成功", {icon: 1},function () {
							obj.del();
							$("#order-items").html("");
						});
					},
					error:function () {
						return layer.msg("服务器错误,请稍后再试", {icon: 2});
					}
				});
				layer.close(index);
			});
		} else if (obj.event === 'detail') {
			//渲染订单明细
			laytpl($("#order-item-tpl").html()).render(data.orderItems, function(html) {
				$("#order-items").html(html);
			});
		}else{
			layer.open({
				type: 1,
				title: '编辑订单',
				content: $("#order-edit-tpl").html(),
				area: ['350px'],
				btn: ['更新'],
				yes: function(index1) {
					let new_data=form.val("order-edit-form");
					if(new_data.status==null){
						new_data.status=false;
					}
					console.log(new_data);
					$.ajax({
						url: '/order/list/'+data.orderId,
						type: 'PUT',
						data: JSON.stringify(new_data),
						contentType: 'application/json',
						dataType: 'json',
						success: function(res) {
							if (res.code != 0) {
								return layer.msg(res.msg, {
									icon: 2
								});
							}
							return layer.msg("更新成功", {
								icon: 1,
								time: 1300
							}, function() {
								obj.update(new_data);
								layer.close(index1);
							});
						}
					});
				},
				success: function() {
					//填充表单（编辑状态）
					form.val("order-edit-form", data);
					form.render();
				}
			});
		}
	});

	//搜索
	var order_tb_this;
	form.on('submit(search_btn)', function(data) {
		if (order_tb_this != null) {
			order_tb_this.where = {};
		}
		orderTb.reload({
			url: '/order/search',
			where: data.field,
			page: {
				curr: 1
			},
			done: function() {
				order_tb_this = this;
			}
		});
		return false;
	});


});
