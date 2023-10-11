
$(function(){
	

		jQuery.validator.addMethod("specialchar", function(value, element) {
			//var reg = /^([\u4e00-\u9fa5]+|[a-zA-Z0-9]+)/;
			var reg = /^([`~!@$^&':;,?~！……&；：。，、？=])/;
			return this.optional(element) || !reg.test(value);
			}, "Vui lòng nhập chữ viết tắt của công ty hợp lệ");
	
		jQuery.validator.addMethod("ievaluean", function(value, element) {
			var reg = new RegExp("Một mô tả đơn giản về cuộc phỏng vấn trong vòng 15 từ");
			return this.optional(element) || !reg.test(value);
			}, "Vui lòng điền vào các đánh giá ngắn");
	/*** check city**********************/
		jQuery.validator.addMethod("checkCity",function(value, element) {
			var reg =  /^[\u4e00-\u9fa5]{0,}$/;
			return this.optional(element) || reg.test(value);
			}, "Vui lòng vào thành phố nơi có công ty hợp lệ, chẳng hạn như Bắc Kinh"); 
		
	/*** Không thể nhập số**********************/
		jQuery.validator.addMethod("checkNum",function(value, element) {
			var reg = /^[0-9]*$/;//Chỉ có thể nhập số
			return this.optional(element) || !reg.test(value);
			}, "Vui lòng nhập giới thiệu câu hợp lệ"); 
	
	/*** check Phải có url ***********************/ 
		jQuery.validator.addMethod("checkUrl", function(value, element) {
			var reg = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
			if(value != $(element).attr('placeholder')){
				if(value.substring(0,7) != 'http://' && value.substring(0,8) != 'https://'){
					value = 'http://' + value;
				}
				return this.optional(element) || reg.test(value);
			}else{
				return false;
			}
		}, "Vui lòng nhập trang web của công ty hợp lệ");
	
	/*** check Không cần phải điền url ***********************/ 
		jQuery.validator.addMethod("checkUrlNot", function(value, element) {
			var reg = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i;
			if(value != $(element).attr('placeholder')){
				if(value.substring(0,7) != 'http://' && value.substring(0,8) != 'https://'){
					value = 'http://' + value;
				}
				return this.optional(element) || reg.test(value);
			}else{
				return true;
			}
		}, "Vui lòng nhập trang web của công ty hợp lệ");
		
	/*** check rangeLength ***********************/ 
		jQuery.validator.addMethod("rangeLen", function(value, element,param) {
			if(value != $(element).attr('placeholder')){
				var length = $.isArray( value ) ? value.length : this.getLength($.trim(value), element);
				return this.optional(element) || ( length >= param[0] && length <= param[1] );
			}else{
				return false;
			}
		}, "Vui lòng nhập phạm vi hiệu quả");
	

		jQuery.validator.addMethod("salaryRange", function(value, element,param) {
			if(($('#salaryMin').val() != '' && $('#salaryMin').val() != $('#salaryMin').attr('placeholder')) && ($('#salaryMax').val() != '' && $('#salaryMax').val() != $('#salaryMax').attr('placeholder'))){
				if(parseInt($.trim($('#salaryMax').val()))/parseInt($.trim($('#salaryMin').val())) > 2){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}, "Vui lòng nhập phạm vi lương hàng tháng hợp lệ");
	

		jQuery.validator.addMethod("Dvalue", function(value, element,param) {
			if(($('#salaryMin').val() != '' && $('#salaryMin').val() != $('#salaryMin').attr('placeholder')) && ($('#salaryMax').val() != '' && $('#salaryMax').val() != $('#salaryMax').attr('placeholder'))){
				if(parseInt($.trim($('#salaryMax').val())) > parseInt($.trim($('#salaryMin').val()))){
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}, "Vui lòng nhập phạm vi lương hàng tháng hợp lệ");
		
		/*** check tel***********************/ 	
		jQuery.validator.addMethod("isTel", function(value, element,param) {
			var valArray = value.split(',');
			var isTel = true;
			var pattern=/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^[0-9]{3,4}\-[0-9]{7,8}\-[0-9]{3,5}$)|(^[0-9]{7,8}\-[0-9]{3,5}$)|(^\([0-9]{3,4}\)[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{7,8}\-[0-9]{3,5}$)|(^0{0,1}[13|15|18|14]{2}[0-9]{9}$)/;
			for(var i=0;i<valArray.length;i++){
				if(!pattern.test(valArray[i])){ 
					isTel = false; 
				}
			}
			if(isTel){ 
				return true; 
			}else{ 
				return false; 
			} 
		}, "Vui lòng nhập đúng số điện thoại di động hoặc số máy đất. Định dạng điện thoại cố định là 010-62555255 hoặc 010-6255255-extension số, nhiều cuộc gọi điện thoại riêng biệt với dấu phẩy tiếng Anh");
	
		/*** check Mobile***********************/ 	
		jQuery.validator.addMethod("isMobile", function(value, element,param) {
			//var pattern=/(^0{0,1}[13|15|18|14]{2}[0-9]{9}$)/;
			var pattern = /(?:\(?[0\+]?\d{1,3}\)?)[\s-]?(?:0|\d{1,4})[\s-]?(?:(?:13\d{9})|(?:\d{7,8}))/;
			if(pattern.test(value)){ 
				return true; 
			}else{ 
				return false; 
			} 
		}, "Xin vui lòng nhập một số điện thoại hợp lệ");

		jQuery.validator.addMethod("maxlenStr", function(value, element,param) {
			var len = value.length;
			var reLen = 0; 
		    for (var i = 0; i < len; i++) {       
		        if (value.charCodeAt(i) < 27 || value.charCodeAt(i) > 126 ) {   
		            reLen += 2; 
		        } else { 
		            reLen++; 
		        } 
		    } 
			if(reLen <= 2*param){ 
				return true; 
			}else{ 
				return false; 
			} 
		}, "Vui lòng nhập các ký tự trong vòng 100 từ");
		

		jQuery.validator.addMethod("moreEmail", function(value, element,param) {
			value = value.replace('；',";").split(';');
			value = jQuery.grep(value, function(n, i){
				  		return ($.trim(n) !== "" && n != null);
					});
			var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
			var isTrue = true;
			if(value.length > 2){
				isTrue = false;
			}else{
				for(var i=0; i<value.length; i++){
					if(!reg.test($.trim(value[i]))){
						isTrue = false;
					}
				}
			}
			if(isTrue){ 
				return true; 
			}else{ 
				return false; 
			} 
		}, "Vui lòng nhập địa chỉ email hợp lệ, tối đa 2 và bằng tiếng Anh; riêng biệt");
});
