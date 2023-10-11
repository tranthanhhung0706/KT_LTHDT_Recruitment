
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
        	Truy vấn trạng thái công ty:
	  		<select id="search-state" name="search-state" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:150px">
	                <option value="0">xin vui lòng chọn</option>
	    			<option value="1">đã xác minh</option>
	    			<option value="2">Đang xem xét</option>
	    			<option value="3">Chưa Xác Minh</option>
	        </select>
	         <a href="javascript:void(0);" id="search-btn" class="easyui-linkbutton" iconCls="icon-search" plain="true">tìm kiếm</a>&nbsp;&nbsp;
	         <a href="javascript:void(0);" id="update-state-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="changeState();">Thay đổi trạng thái vị trí</a>&nbsp;&nbsp;
	         <a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-cross" onclick="remove();" plain="true">xóa bỏ</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">Tên công ty:</td>
                <td><input type="text" id="edit-name" name="name"  readonly="readonly" class="wu-text " /></td>
            </tr>
            <tr>
                <td width="60" align="right">Tình trạng công ty:</td>
                <td>
                	<select id="edit-state" name="state" class="easyui-combobox" data-options="editable:false" panelHeight="auto" style="width:268px">
			    		  <option value="đã xác minh">đã xác minh</option>
			    		  <option value="Đang xem xét">Đang xem xét</option>
			    		  <option value="Chưa Xác Minh">Chưa Xác Minh</option>
		            </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<#include "../common/footer.ftl"/>
<!-- End of easyui-dialog -->
<script type="text/javascript">

	//Mở vị trí của trạng thái vị trí
	function changeState(){
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn dữ liệu sẽ được sửa đổi!','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn một dữ liệu để sửa đổi!','info');
			return;
		}
		item = item[0];
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "Thay đổi trạng thái vị trí",
            buttons: [{
                text: 'OK',
                iconCls: 'icon-ok',
                handler: change
            }, {
                text: 'Hủy bỏ',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
           		$("#edit-id").val(item.id);
            	$("#edit-state").combobox('setValue',item.state);
            	$("#edit-name").val(item.name);
            }
        });
	}	
	
	
	//Xóa công ty
	function remove(){
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length != 1){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn một dữ liệu công ty bạn muốn xóa!','info');
			return;
		}
		$.messager.confirm('Thông báo tin nhắn','Bạn có chắc là bạn muốn xóa công ty?Nếu bị xóa, tất cả thông tin về công ty sẽ bị xóa!', function(result){
			if(result){
				$.ajax({
					url:'/admin/company/delete',
					dataType:'json',
					type:'post',
					data:{id:item[0].id},
					success:function(data){
						if(data.code == 0){
							$.messager.alert('Thông báo tin nhắn','đã xóa thành công!','info');
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
	
	
	
	//Thực hiện hoạt động của trạng thái vị trí
	function change(){
		var data = $("#edit-form").serialize();
		console.log('data', data);
		$.ajax({
			url:'/admin/company/change_state',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.code == 0){
					$.messager.alert('Thông báo tin nhắn','Thay đổi thành công của công ty!','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('Thông báo tin nhắn',data.msg,'warning');
				}
			}
		});
	}

	
	//Màn hình nút tìm kiếm
	$("#search-btn").click(function(){
	    var search_value = $('#search-state').combobox('getValues');
	    var state = "";
	    if(search_value == "1"){
			state = "đã xác minh";
		}
		if(search_value == "2"){
			state = "Đang xem xét";
		}
		if(search_value == "3"){
			state = "Chưa Xác Minh";
		}
		$('#data-datagrid').datagrid('reload',{
			state:state
		});
		$("#data-datagrid").datagrid('clearSelections');
	});
	
	
	
	//Tải dữ liệu
	$('#data-datagrid').datagrid({
		url:'/admin/company/list',
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

			{ field:'name',title:'Tên công ty',width:150},
			{ field:'locale',title:'Vị trí công ty',width:150},
			{ field:'territory',title:'Lĩnh vực công ty',width:100},
			{ field:'scale',title:'Quy mô công ty',width:50},
			{ field:'finance',title:'Loại hình',width:50},
			{ field:'founderName',title:'Tên của người sáng lập',width:50},

			{ field:'state',title:'Tình trạng công ty',width:60},
			{ field:'updateTime',title:'Thời gian cập nhật cuối cùng của công ty',width:100}
		]]
	});
</script>