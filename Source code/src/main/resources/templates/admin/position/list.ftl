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
        	Truy vấn trạng thái công việc:
	  		<select id="search-state" name="search-state" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:150px">
	                <option value="0">xin vui lòng chọn</option>
	    			<option value="1">Trực tuyến</option>
	    			<option value="2">Đang xem xét</option>
	    			<option value="3">Ngoại tuyến</option>
	        </select>
	         <a href="javascript:void(0);" id="search-btn" class="easyui-linkbutton" iconCls="icon-search" plain="true">tìm kiếm</a>&nbsp;&nbsp;
	         <a href="javascript:void(0);" id="update-state-btn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="changeState();">Thay đổi trạng thái</a>
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
                <td width="60" align="right">Danh mục Công việc:</td>
                <td><input type="text" id="edit-type" name="type"  readonly="readonly" class="wu-text " /></td>
            </tr>
            <tr>
                <td width="60" align="right">Tiêu đề công việc:</td>
                <td><input type="text" id="edit-name" name="name"  readonly="readonly" class="wu-text " /></td>
            </tr>
            <tr>
                <td width="60" align="right">Trạng thái vị trí:</td>
                <td>
                	<select id="edit-state" name="state" class="easyui-combobox" data-options="editable:false" panelHeight="auto" style="width:268px">
		    			 <option value="effective">Trực tuyến</option>
		    			 <option value="wait">Đang xem xét</option>
		    			 <option value="out">Ngoại tuyến</option>
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
			$.messager.alert('Thông tin nhắc nhở ',' Vui lòng chọn dữ liệu sẽ được sửa đổi!','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('Thông tin nhắc nhở ',' Vui lòng chọn một dữ liệu để sửa đổi!','info');
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
            	$("#edit-type").val(item.type);
            	$("#edit-name").val(item.name);
            }
        });
	}	
	
	
	//Thực hiện hoạt động của trạng thái vị trí
	function change(){
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'/admin/position/change_state',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.code == 0){
					$.messager.alert('Thông báo tin nhắn','Thay đổi vị trí thành công trong công việc!','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('Thông báo tin nhắn',data.msg,'warning');
				}
				$("#data-datagrid").datagrid('clearSelections');
			}
		});
	}

	
	//Màn hình nút tìm kiếm
	$("#search-btn").click(function(){
	    var search_value = $('#search-state').combobox('getValues');
	    var state = "";
	    if(search_value == "1"){
			state = "effective";
		}
		if(search_value == "2"){
			state = "wait";
		}
		if(search_value == "3"){
			state = "out";
		}
		$('#data-datagrid').datagrid('reload',{
			state:state
		});
		$("#data-datagrid").datagrid('clearSelections');
	});
	
	
	
	//Tải dữ liệu
	$('#data-datagrid').datagrid({
		url:'/admin/position/list',
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

			{ field:'company.name',title:'Tên công ty',width:100,formatter:function(value,row,index){
				return row.company.name;
			}},
			{ field:'name',title:'Tiêu đề',width:100},
			{ field:'type',title:'Công việc',width:100},
			{ field:'workType',title:'Loại hình',width:50},
			{ field:'address',title:'địa chỉ làm việc',width:150},
			{ field:'degree',title:'Yêu cầu trình độ',width:70},
			{ field:'minMoney',title:'Mức lương công việc',width:80,formatter:function(value,row,index){
				var money_range = "";
				money_range += row.minMoney;
				money_range += " triệu-";
				money_range += row.maxMoney;
				money_range += " triệu";
				return money_range;
			}},
			{ field:'state',title:'Trạng thái vị trí',width:80,formatter:function(value,row,index){
				if(value == 'wait'){
					return 'Đang xem xét';
				}
				if(value == 'out'){
					return 'Ngoại tuyến';
				}
				if(value == 'effective'){
					return 'Trực tuyến';
				}
				return value;
			}},
			{ field:'updateTime',title:'Lần cuối cập nhật',width:100}
		]]
	});
</script>