
function showErrorMsg(msg,field)
{
	if(msg != 'Độ dài không quá 50 kí tự'){
		$('#'+field).css('display','block');
	}
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
		if($(e).val().length > 50){
			showErrorMsg("Độ dài không quá 50 kí tự",$(e).attr('attributes')); 
			flag = false;
			return false;
		}
	});
	return flag;  

}