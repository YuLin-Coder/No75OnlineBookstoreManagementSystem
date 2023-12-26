layui.use(['element', 'jquery', 'layer', 'laytpl','laypage'], function() {
	var $ = layui.jquery,
		layer = layui.layer,
		laytpl = layui.laytpl,
		laypage=layui.laypage,
		element = layui.element;

		
	//请求加载分类信息，渲染选项卡
	$.getJSON("/index/category",function(res){
		if(res.code!=0){
			$("#content").html(res.msg);
			return ;
		}
		$.each(res.data,function(index,item){
			let str='<li c-code="'+item.categoryCode+'">'+item.categoryName+'</li>';
			$("#category_tag").append(str);
		});
		//请求全部书籍
		getBooksByPage('/index/books',{page:1,limit:10});
	});
	
	

	/**
	 * 监听切换分类选项卡的切换
	 */
	element.on('tab(categoryTabBrief)', function(data) {
		// console.log($(this).attr("c-code")); //当前Tab标题所在的原始DOM元素
		if(!($(this).attr('lay-id')==='search')){
			//获取分类代码
			let code=$(this).attr("c-code");
			getBooksByPage('/index/books',{page:1,limit:10,categoryCode:code});
		}else {
			$("#content").html('');
		}
	});
	
	//搜索
	$("#search-btn").click(function(){
		element.tabChange('categoryTabBrief', 'search');
		let bookName=$("#keyword-input").val().trim();
		if(bookName.length==0){
			return;
		}
		getBooksByPage('/index/books/search',{page:1,limit:10,bookName:bookName});
	});

	/**
	 * 请求分页查询
	 * @param url
	 * @param param
	 */
	function getBooksByPage(url,param) {
		param['page']=param.page||1;
		param['limit']=param.limit||10;
		$.getJSON(url,param,function (result) {
			if(result.code!=0){
				$("#content").html('<div style="text-align: center;font-size: 20px;">'+result.msg+'</div>');
				return ;
			}
			if(result.data.length==0){
				$("#content").html('<div style="text-align: center;font-size: 20px;">暂时没有数据</div>');
				return ;
			}
			laytpl($("#book-card-tpl").html()).render(result.data,function(html){
				$("#content").html(html);
			});
			//调用分页
			laypage.render({
				elem: 'page-util'
				,count: result.count
				,curr: param.page||1
				,limit: param.limit||10
				,jump: function(obj,first){
					if(!first){
						// console.log(obj);
						param.page=obj.curr;
						param.limit=obj.limit;
						getBooksByPage(url, param);
					}
				}
			});
		})
	}



});
