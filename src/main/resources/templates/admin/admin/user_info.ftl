
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Trang chủ</title>
<link rel="stylesheet" type="text/css" href="/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/admin/easyui/css/wu.css" />
<link rel="stylesheet" type="text/css" href="/admin/easyui/css/icon.css" />
<script type="text/javascript" src="/admin/easyui/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<style>
.dialog-button { padding: 5px; text-align: center; };
</style>

<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
	       <a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-cross" onclick="remove();" plain="true">xóa bỏ</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->

</div>
<#include "../common/footer.ftl"/>
<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	//Xóa người dùng
	function remove(){
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length != 1){
			$.messager.alert('Lời nhắc thông tin ',' Vui lòng chọn dữ liệu người dùng bạn muốn xóa！','info');
			return;
		}
		$.messager.confirm('Thông báo tin nhắn','Bạn có chắc là bạn muốn xóa người dùng?Nếu bị xóa, tất cả thông tin về người dùng sẽ bị xóa!', function(result){
			if(result){
				$.ajax({
					url:'/admin/user/delete',
					dataType:'json',
					type:'post',
					data:{id:item[0].id},
					success:function(data){
						if(data.code == 0){
							$.messager.alert('Thông báo tin nhắn','đã xóa thành công！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('Thông báo tin nhắn',data.msg,'warning');
						}
						$("#data-datagrid").datagrid('clearSelections');
					}
				});
			}	
		});
	}
	

	//Tải dữ liệu
	$('#data-datagrid').datagrid({
		url:'/admin/user/user_info_list',
		rownumbers:true,
		singleSelect:false,
		pageSize:10,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    fit:true,
		columns:[[
			{ field:'chk',checkbox:true},

			{ field:'username',title:'tên tài khoản',width:100,sortable:true},
			{ field:'email',title:'Thư',width:100},
			//{ field:'password',title:'mật khẩu',width:100},
			{ field:'sex',title:'giới tính',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return 'LGBT';
					}
					case 1:{
						return 'nam';
					}
					case 2:{
						return 'nữ';
					}
				}
				return value;
			}},
			{ field:'type',title:'Tên người dùng',width:100,formatter:function(value,row,index){
				switch(value){
					case 0:{
						return 'Người nộp đơn';
					}
					case 1:{
						return 'Nhà tuyển dụng';
					}
				
				}
				return value;
			}},
			{ field:'mobile',title:'Số điện thoại',width:100},
			{ field:'updateTime',title:'Thời gian cập nhật lần cuối cùng của người dùng',width:100}
		]]
	});
</script>