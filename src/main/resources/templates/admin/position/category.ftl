
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
		    <a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-add"  onclick="openAdd();" plain="true">Thêm các loại nghề nghiệp</a>
		    <a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-add"  onclick="openThirdAdd();" plain="true">Tăng danh mục nghệ nghiệp thứ ba</a>
		    <a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit();" plain="true">Chỉnh sửa</a>
		    <a href="javascript:void(0);"  class="easyui-linkbutton" iconCls="icon-cross" onclick="remove();" plain="true">xóa bỏ</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td align="right">Menu vượt trội:</td>
                <td>
                	<select name="parent_id" class="easyui-combobox" panelHeight="auto" style="width:268px">
                		 <option value="0">Phân loại hàng đầu</option>
		                <#if topPositionCategoryList?size gt 0>
							 <#list topPositionCategoryList as topPositionCategory>
		               			 <option value="${topPositionCategory.id!""}">${topPositionCategory.name!""}</option>
		                	 </#list>
		                </#if>
		            </select>
                </td>
            </tr>
            <tr>
                <td width="100" align="right">Tên loại công việc:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'Vui lòng điền vào tên của danh mục vị trí thứ nhất hoặc thứ hai'" /></td>
            </tr>
            <tr>
                <td  width="100" align="right">URL loại công việc:</td>
                <td><input type="text" name="url" class="wu-text" /></td>
            </tr>
        </table>
    </form>
</div>
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table>
             <tr>
                <td width="100" align="right">Menu vượt trội:</td>
                <td>
                	<input type="text" readonly="readonly" id="edit-parent" class="wu-text easyui-validatebox" />
                </td>
            </tr>
            <tr>
                <td width="100" align="right">Tên loại công việc:</td>
                <td><input type="text" name="name" id="edit-name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写职位类别名称'" /></td>
            </tr>
            <tr>
                <td width="100" align="right">URL loại công việc:</td>
                <td><input type="text" name="url" id="edit-url" class="wu-text" /></td>
            </tr>
        </table>
    </form>
</div>
<!-- Thêm một cửa sổ pop -up chuyên nghiệp thứ ba -->
<div id="add-third-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-third-form" method="post">
        <table>
        		<tr>
                <td width="100" align="right">Menu vượt trội:</td>
                <td>
                	<input type="hidden" name="parent_id" id="add-third-parent-id">
                	<input type="text" readonly="readonly" id="parent-third" class="wu-text easyui-validatebox" />
                </td>
            </tr>
            <tr>
                <td width="100" align="right">Tên loại công việc:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:''" /></td>
            </tr>
            <tr>
                <td width="100" align="right">URL loại công việc:</td>
                <td><input type="text" name="url" class="wu-text" /></td>
            </tr>
           
        </table>
    </form>
</div>
<#include "../common/footer.ftl"/>

<!-- End of easyui-dialog -->
<script type="text/javascript">
	
	//Xóa danh mục công việc
	function remove(){
		var item = $('#data-datagrid').treegrid('getSelections');
		if(item == null || item.length != 1){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn dữ liệu danh mục chuyên nghiệp mà bạn muốn xóa！','info');
			return;
		}
		$.messager.confirm('Thông báo tin nhắn','Xác định xóa bản ghi？', function(result){
			if(result){
				$.ajax({
					url:'/admin/position_category/delete',
					dataType:'json',
					type:'post',
					data:{id:item[0].id},
					success:function(data){
						if(data.code == 0){
							$.messager.alert('Thông báo tin nhắn','đã xóa thành công！','info');
							$('#data-datagrid').treegrid('reload');
						}else{
							$.messager.alert('Thông báo tin nhắn',data.msg,'warning');
						}
						$("#data-datagrid").treegrid('clearSelections');
					}
				});
			}	
		});
	}
	
	
	
	//Mở một cửa sổ tăng cấp đầu tiên và thứ cấp
	function openAdd(){
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "Thêm thông tin danh mục công việc thứ nhất và thứ hai",
            buttons: [{
                text: 'OK',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: 'Hủy bỏ',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }]
        });
	}
	
	//Thêm hồ sơ
	function add(){
		var validate = $("#add-form").form("validate");
		if(!validate){
			$.messager.alert("Thông báo tin nhắn","Vui lòng kiểm tra dữ liệu bạn nhập!","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'/admin/position_category/add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.code == 0){
					$.messager.alert('Thông báo tin nhắn','Thêm thành công！','info');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').treegrid('reload');
				}else{
					$.messager.alert('Thông báo tin nhắn',data.msg,'warning');
				}
			},
			error:function(data){
				 $.messager.alert("Thông báo tin nhắn", 'Lỗi mạng！', "error");
			}
		});
	}
	
	
	
	
	//Sửa đổi hồ sơ
	function edit(){
		var validate = $("#edit-form").form("validate");
		if(!validate){
			$.messager.alert("Thông báo tin nhắn","Vui lòng kiểm tra dữ liệu bạn nhập!","warning");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'/admin/position_category/edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.code == 0){
					$.messager.alert('Thông báo tin nhắn','Sửa đổi thành công！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').treegrid('reload');
				}else{
					$.messager.alert('Thông báo tin nhắn',data.msg,'warning');
				}
				$("#data-datagrid").treegrid('clearSelections');
			}
		});
	}
	
	
	
	//Thêm danh mục công việc thứ ba
	function openThirdAdd(){
		var item = $('#data-datagrid').treegrid('getSelections');
		if(item == null || item.length != 1){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn một dữ liệu danh mục chuyên nghiệp thứ cấp!','info');
			return;
		}
		if(item[0]._parentId == null){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn menu phụ!','info');
			return;
		}
		var parent = $('#data-datagrid').treegrid('getParent',item[0].id);
		if(parent._parentId != null){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn menu phụ!','info');
			return;
		}
		$('#add-third-dialog').dialog({
			closed: false,
			modal:true,
            title: "Thêm thông tin danh mục công việc thứ ba",
            buttons: [{
                text: 'OK',
                iconCls: 'icon-ok',
                handler: function(){
                	var validate = $("#add-third-form").form("validate");
            		if(!validate){
            			$.messager.alert("Thông báo tin nhắn","Vui lòng kiểm tra dữ liệu bạn nhập!","warning");
            			return;
            		}
            		var data = $("#add-third-form").serialize();
            		$.ajax({
            			url:'/admin/position_category/add',
            			dataType:'json',
            			type:'post',
            			data:data,
            			success:function(data){
            				if(data.code == 0){
            					$.messager.alert('Thông báo tin nhắn','Thêm thành công!','info');
            					$('#add-third-dialog').dialog('close');
            					$('#data-datagrid').treegrid('reload');
            				}else{
            					$.messager.alert('Thông báo tin nhắn',data.msg,'warning');
            				}
            			}
            		});
                }
            }, {
                text: 'Hủy bỏ',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-third-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#parent-third").val(item[0].name);
            	$("#add-third-parent-id").val(item[0].id);
            }
        });
		
	}
	
	

	
	//Mở cửa sổ sửa đổi
	function openEdit(){
		var item = $('#data-datagrid').datagrid('getSelections');
		if(item == null || item.length == 0){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn dữ liệu sẽ được sửa đổi！','info');
			return;
		}
		if(item.length > 1){
			$.messager.alert('Thông báo tin nhắn','Vui lòng chọn dữ liệu để sửa đổi！','info');
			return;
		}
		item = item[0];
		var name = item.name;
		var parent = item;
		if(item._parentId == null)
		{
			parent.name = "Phân loại hàng đầu";
		}else{
		 	parent = $('#data-datagrid').treegrid('getParent',item.id);
		}
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "Sửa đổi thông tin danh mục công việc",
            buttons: [{
                text: 'OK',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: 'Hủy bỏ',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(){
            	$("#edit-id").val(item.id);
            	$("#edit-name").val(name);
            	$("#edit-parent").val(parent.name);
            	$("#edit-url").val(item.url);
            }
        });
	}	
	
	//Tải dữ liệu
	$('#data-datagrid').treegrid({
		url:'/admin/position_category/list',
		rownumbers:true,
		singleSelect:true,
		pageSize:300,           
		pagination:true,
		multiSort:true,  //Xác định có bật nhiều cột sắp xếp
		fitColumns:true,
		pageList:[300],
		idField:'id',    //Nó thường được định nghĩa là khóa chính
	    treeField:'name',  //Nó được chỉ định rằng cột sẽ hiển thị nó thành một hình dạng cây
		fit:true,
		columns:[[
			{ field:'name',title:'Tên loại công việc',width:100},
			{ field:'url',title:'URL loại công việc',width:100}
		]]
	});
</script>