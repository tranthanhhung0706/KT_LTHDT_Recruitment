if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'Đầu tiên';
	$.fn.pagination.defaults.afterPageText = 'Tổng {pages} trang';
	$.fn.pagination.defaults.displayMsg = 'Đang {from} đến {to}, Tổng {total} dòng';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Nó đang đối phó với nó, xin vui lòng đợi một chút.。。';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'OK';
	$.messager.defaults.cancel = 'Hủy bỏ';
}
$.map(['validatebox','textbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = '';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = 'Vui lòng nhập địa chỉ email hợp lệ';
	$.fn.validatebox.defaults.rules.url.message = 'Vui lòng nhập địa chỉ URL hợp lệ';
	$.fn.validatebox.defaults.rules.length.message = 'Độ dài của nội dung đầu vào';
	$.fn.validatebox.defaults.rules.remote.message = 'vui lòng sửa phạm vi này';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
	$.fn.calendar.defaults.months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'Hiện Tại';
	$.fn.datebox.defaults.closeText = 'Đóng';
	$.fn.datebox.defaults.okText = 'Chắc chắn';
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
		okText: $.fn.datebox.defaults.okText
	});
}
if ($.fn.datetimespinner){
	$.fn.datetimespinner.defaults.selections = [[0,4],[5,7],[8,10],[11,13],[14,16],[17,19]]
}
