if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'Đầu tiên';
	$.fn.pagination.defaults.afterPageText = 'Tổng {pages} trang';
	$.fn.pagination.defaults.displayMsg = 'Đang {from} đến {to}, Tổng {total} dòng';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Đối xử với nó, vui lòng đợi một chút。。。';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'OK';
	$.messager.defaults.cancel = 'Hủy bỏ';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = 'Mục đầu vào này là một truyền dịch cần thiết';
	$.fn.validatebox.defaults.rules.email.message = 'Vui lòng nhập địa chỉ email hợp lệ';
	$.fn.validatebox.defaults.rules.url.message = 'Vui lòng nhập địa chỉ URL hợp lệ';
	$.fn.validatebox.defaults.rules.length.message = 'Độ dài của nội dung đầu vào phải nằm giữa {0} và {1}';
	$.fn.validatebox.defaults.rules.remote.message = 'vui lòng sửa phạm vi này';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = 'Mục đầu vào này là một truyền dịch cần thiết';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = 'Mục đầu vào này là một truyền dịch cần thiết';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = 'Mục đầu vào này là một truyền dịch cần thiết';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = 'Mục đầu vào này là một truyền dịch cần thiết';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
	$.fn.calendar.defaults.months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'Hôm nay';
	$.fn.datebox.defaults.closeText = 'Khép kín';
	$.fn.datebox.defaults.okText = 'OK';
	$.fn.datebox.defaults.missingMessage = 'Mục đầu vào này là một truyền dịch cần thiết';
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	};
	$.fn.datebox.defaults.parser = function(s){
		if (!s) return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}
