
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Trang chủ</title>
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
<#include "../common/footer.ftl"/>
<!-- End of easyui-dialog -->
<script type="text/javascript">

	//Xóa nhật ký hoạt động
	function remove(){
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length <= 0){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn ít nhất một dữ liệu nhật ký hoạt động bạn muốn xóa！','info');
			return;
		}
		$.messager.confirm('Thông báo tin nhắn','Bạn có OK xóa các hồ sơ này?', function(result){
			if(result){
			var ids = "";
			for(var i=0;i<item.length;i++)
			{
				ids += item[i].id;
				ids += ",";
			}
			ids = ids.substring(0,ids.length-1);
			$.ajax({
					url:'/admin/operater_log/delete',
					dataType:'json',
					type:'post',
					data:{ids:ids},
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
	




	$('#data-datagrid').datagrid({
		url:'/admin/operater_log/list',
		rownumbers:true,
		singleSelect:false,
		pageSize:30,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'operator',title:'nhà điều hành',width:100},
			{ field:'content',title:'Nội dung hoạt động',width:300},
			{ field:'createTime',title:'Thời gian hoạt động',width:100}
		]]
	});
</script>