
function showErrorMsg(msg,field)
{
	$("#errorMsg").html(msg);
	$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
}

function checkForm(formId){
	var flag = true;
	$("#"+formId).find(".require").each(function(i,e){
		if($(e).val() == ''){
			showErrorMsg($(e).attr('tips'),$(e).attr('attributes')); 
			flag = false;
			return false;
		}
	});
	return flag;  

}