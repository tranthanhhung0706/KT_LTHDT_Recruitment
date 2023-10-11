//Thông tin cảnh báo nhanh chóng
function showWarningMsg(msg)
{
	$.messager.alert("Thông báo tin nhắn", msg, "warning");
}
//Thông báo lỗi nhanh chóng
function showErrorMsg(msg)
{
	$.messager.alert("Thông báo tin nhắn", msg, "error");
}
//Mẫu xác minh phương thức công khai, ID của biểu mẫu biểu mẫu có thể xuất hiện
function checkForm(formId){
	var flag = true;
	$("#"+formId).find(".required").each(function(i,e){
		if($(e).val() == ''){
			showWarningMsg($(e).attr('tips')); 
			flag = false;
			return false;
		}
	});
	return flag;  

}