$(function(){
	/***************************
	 * Đăng ký Trang Kích hoạt: Email xác minh lại
	 */
	$('#thirdBind_resend').click(function(){
		var recordCode = $('#recordCode').val(); 
		$.ajax({
			data:{recordCode:recordCode},
        	type:'POST',        	
        	url:ctx+'/user/resendActive.json'
        }).done(function(result) {
        	if(result.success){
        		$.colorbox({inline:true, href:$("#resend_success"),title:"Xác minh email gửi thành công"});
        	}else{
        		alert(result.msg);
        	}
        });
	});
	
	/*bindEmail*/
	/**Khi không có ràng buộc tài khoản, nếu bạn nhấp vào logo để rời đi, sẽ có các cửa sổ pop -up**/
	var isLeave=true;
	window.onbeforeunload = function(){
		if(isLeave){
			return;
		}else{
			return "Nội dung chưa được bảo tồn，Bạn có xác nhận rằng bạn rời khỏi trang này không ？ ";	
		}
	 }
	$('#confirmLeave').click(function(){
		isLeave = false;
	})
	
	/*Xác định xem có tài khoản nào không*/
	$('.user_firstMainContent').css({"padding-bottom":"10px"});
	$('#user_selectAccount label').not('.agreeNotice').click(function(){		
		$(this).addClass('checked').parent('form').siblings('form').children('label').removeClass('checked');		
		$(this).siblings('div').show();
		$(this).parent('form').siblings('form').children('div').hide();
		if($(this).siblings('div:has("ul")').length>0){
			$('.user_warn').hide();
		}else{
			$('.user_warn').show();
		}
	});	
	/*Giải quyết vấn đề màu sắc của trình giữ chỗ và giá trị (mặc định là nhất quán và nhu cầu không nhất quán)*/
	function placeholderSupportUserBind() {
	    return 'placeholder' in document.createElement('input');
	}
	function placeholderFnUserBindFn(){
		if(!placeholderSupportUserBind()){   
		    $('[placeholder]').focus(function() {
		        var input = $(this);
		        if (input.val() == input.attr('placeholder')) {
		            input.val('');
		            input.removeClass('placeholder');
		            input.css('color','#333');
		        }else{
		        	input.css('color','#333');
		        }
		    }).blur(function() {
		        var input = $(this);
		        if (input.val() == '' || input.val() == input.attr('placeholder')) {
		            input.addClass('placeholder');
		            input.val(input.attr('placeholder'));
		            input.css('color','#999');
		        }
		    }).blur();
		}
		
		 //Phong cách khởi tạo
	    if ($('[placeholder]').value === "") {
	    	$('[placeholder]').value = $('[placeholder]').attr('placeholder'); 
	    }
	}
	placeholderFnUserBindFn();
	/*Nút radio chọn người người dùng tuyển dụng người hoặc tìm một công việc*/
	$('.register_radio li').click(function(){		
		$(this).children('input').attr('checked',true);			
		$(this).siblings().children('input').removeAttr('checked');	
		$(this).addClass('current').append('<em></em>').siblings().removeClass('current').find('em').remove();	
		$("#user_regBindForm").validate().element($(this).find('input'));		
	});
	/*Giải quyết hai lời nhắc lỗi cùng một lúc*/
	$('#user_LoginEmail,#user_hasLoginEmail,#user_hasLoginPassword,#user_LoginPassword').focus(function(){
		$('#noLagouAccount_beError').hide();
		$('#hasLagouAccount_EmailBeError').hide();
		$('#hasLagouAccount_beError').hide();
		$('#hasNoAccount_beError').hide();
	});	
	/*Xác minh biểu mẫu tài khoản ràng buộc đã có tài khoản*/
	$('#user_bindForm').validate({
		/*onkeyup: false,
    	focusCleanup:true,*/
    	rules: {
    	   	email: {
    	    	required: true,
    	    	email: true
    	   	},
    	   	password: {
    	    	required: true,
    	    	rangelength: [6,16]
    	   	}
    	},
    	messages: {
    	   	email: {
    	    	required: "Vui lòng nhập địa chỉ email đăng nhập",
    	    	email: "Vui lòng nhập địa chỉ email hợp lệ"
    	   	},
    	   	password: {
    	    	required: "Xin vui lòng nhập mật khẩu",
    	    	rangelength: "Vui lòng nhập mật khẩu 6-16 bit, để các chữ cái phân biệt trường hợp"
    	   	}
    	},
    	errorPlacement:function(label, element){
    		if(element.attr("type") == "radio"){
    			label.insertAfter($(element).parents('ul')).css('marginTop','-20px');
    		}else if(element.attr("type") == "checkbox"){
    			label.insertAfter($(element).parent()).css('clear','left');
    			$('#user_saveRegAccount').css('margin-top','20px');
    		}else{
    			label.insertAfter(element);
    		};		    		   		
    	},
    	submitHandler:function(form){
    		isLeave = true;
    		var email = $('#user_hasLoginEmail').val();    		 
    		var password = $('#user_hasLoginPassword').val();
    		var type=$('#user_HasAccount').attr('value');
    		var resubmitToken = $('#resubmitToken').val();
    		var idCode = $('#idCode').val();   
    		$(form).find(":submit").attr("disabled", true);
    		$.ajax({
    			type:'post',
    			data:{idCode:idCode,resubmitToken:resubmitToken,type:type,confirm:false,choseUser:1,email:email,password:password},
    			url:ctx+'/user/bindEmail.json',	   
    			dataType:'json',
    			success:function(result){
    				$('#resubmitToken').val(result.resubmitToken);	    				
    				if(result.success){
    					if(result.code==5 || result.code ==8 || result.code ==11 || result.code ==14 ){
    						window.location.href = ctx+'/user/registerSuccess.html';
    					}else{
    						//result.content == recordCode  == idCode
    						window.location.href = ctx+'/user/bindAccountActive.html?recordCode=' + result.content;	
    					}  					
    				}else if(result.success == false && result.code ==0 ){
    					/*Lỗi mật khẩu và thời gian chờ trang thông tin nhanh chóng*/
						$('#hasLagouAccount_beError').html(result.msg).show();   					
    				}else if(result.success == false && result.code ==100){
    					//Lỗi của dấu nhắc không đăng ký hộp thư
    					$('#hasLagouAccount_EmailBeError').html(result.msg).show(); 
    				}else if(result.success == false && result.code ==1){
    					$('#accountBindType').html('Thư');
    					$('#user_hasBindAccount').html(result.msg);
    					$('#user_loginCurrentAccount').html(result.content);
    					$('#user_confirmBindTips').html('Thay đổi hộp thư');
    					$.colorbox({inline:true, href:$("#bindTips"),title:"Tài khoản ràng buộc"});
    					$('#user_confirmBindTips').on('click',function(){
    						isLeave = true;
    						$.colorbox.close();
    						parent.jQuery.colorbox.close();
    					});	    					
    				}else if(result.success == false && result.code == 2){
    					$.colorbox({inline:true, href:$("#bindReplace"),title:"Tài khoản ràng buộc"});
    					$('#user_oldAccount').html(result.content[1]);
    					$('#user_typeAccount').html(result.content[2]);    					
    					$('#user_replaceAccount').html(result.content[3]);
    					//$('#user_name').html(result.content[1]);
    					$('#user_resumeScore').html(result.content[0][0]["resumeStatus"]);
    					$('#user_jobCount').html(result.content[0][0]["deliverCount"]);
    					$('#user_publishJob').html(result.content[0][0]["publishCount"]);
    					$('#user_receiveCount').html(result.content[0][0]["receiveCount"]);   					
    					$('#user_replaceResumeScore').html(result.content[0][1]["resumeStatus"]);
    					$('#user_replaceJobCount').html(result.content[0][1]["deliverCount"]);
    					$('#user_replacePublishJob').html(result.content[0][1]["publishCount"]);
    					$('#user_replaceReceiveCount').html(result.content[0][1]["receiveCount"]);   					   					 					
    				}
    				$(form).find(":submit").attr("disabled", false);
    			},
    			error:function(){
    				$(form).find(":submit").attr("disabled", false);
    			}			
    		});    		
        }  
	});	
	
	var userChoose = $('.chooseAccount label');
	var choseUser = $('.chooseAccount label.current').find('span.c3').attr('value');
	
	/*Chọn nút OK để giữ lại tài khoản*/
	$('#user_confirmReplace').on('click' , function(){
		var isOldChecked = $('#user_HasAccount').attr('class') == 'checked';
		if(userChoose.hasClass('current')){  							
			$('#chooseRemainError').html('Vui lòng chọn một tài khoản bạn cần giữ').hide();    							
			$('#user_confirmReplace').attr("disabled", false); 		
			if($('#hasSidebar').val() == 1){
				$.colorbox({inline:true, href:$("#confirmBind2"),title:"Tài khoản ràng buộc"});
			}else if(isOldChecked){
				$.colorbox({inline:true, href:$("#confirmBind1"),title:"Tài khoản ràng buộc"});	
			}else{
				$.colorbox({inline:true, href:$("#confirmBind3"),title:"Tài khoản ràng buộc"});	
			}
		}else{
			$('#chooseRemainError').html('Vui lòng chọn một tài khoản bạn cần giữ').show();    							
		}					
	} );	
	/*Chọn giữ tài khoản*/
	$('.chooseAccount label em,.chooseAccount .c7').click(function(){
		$('#chooseRemainError').html('Vui lòng chọn một tài khoản bạn cần giữ').hide(); 
		$(this).parents('label').addClass('current').siblings('label').removeClass('current'); 
	})
	
	/*Quay lại nút sửa đổi*/
	$('.user_backReplace').on('click',function(){					
		$.colorbox({inline:true, href:$("#bindReplace"),title:"Tài khoản ràng buộc"});
	});	
	/*Xác nhận nút Binding ajax*/
	$('#user_confirmBind1').on('click',function(){
		var email = $('#user_hasLoginEmail').val();    		 
		var password = $('#user_hasLoginPassword').val();
		var type=$('#user_HasAccount').attr('value');//Mặc định 0 tài khoản mới 1 tài khoản hiện có null là 0 là 0
		var userType = $('.register_radio li.current input').val();//Người dùng 0 cá nhân
		var idCode = $('#idCode').val();   
		var resubmitToken = $('#resubmitToken').val();
		var choseUser = $('.chooseAccount label.current').find('span.c3').attr('value');
		$.ajax({
			type:'post',
			data:{idCode:idCode, resubmitToken:resubmitToken, type:type,comfirm:true,choseUser:choseUser,userType:userType,email:email,password:password},
			url:ctx+'/user/bindEmail.json',	
			success:function(result){
				isLeave = true;
				$('#resubmitToken').val(result.resubmitToken);
				if(result.success){
					if(result.code==5 || result.code ==8 || result.code ==11 || result.code ==14 ){
						window.location.href = ctx+'/user/registerSuccess.html';
					}else{
						window.location.href = ctx+'/user/bindAccountActive.html?recordCode=' + result.content;
					} 				    					
				}else{	
					if(result.msg == undefined){
						result = eval('(' + result + ')');
					}
					if(result.msg == '' || result.msg == undefined || result.msg == null ){
						alert('Sự thất bại ràng buộc');
					}else if(result.msg =='Thời gian chờ trang，Vui lòng làm mới trang để tái sử dụng'){
						alert('Bạn đã nhấp thành công "để xác nhận ràng buộc”，Đừng lặp lại nhấp chuột');
					}else{
						errorTips("Sự thất bại ràng buộc：" + result.msg,"Tài khoản ràng buộc");
					}					
				}    									
			}
		});
	});	
	

	$("#user_regBindForm").validate({  
		/*onkeyup: false,
    	focusCleanup:true,*/
		rules: {
        	type:{
        		required: true
        	},
	   	   	email: {
	   	    	required: true,
	   	    	email: true
	   	   	},
	   	   	password: {
	   	    	required: true,
	   	    	rangelength: [6,16]
	   	   	},
	   	   	checkbox:{required:true}
    	},
    	messages: {
    		type:{
        		required:"Vui lòng chọn mục đích sử dụng"
        	},
    	 	email: {
    	    	required: "Vui lòng nhập địa chỉ hộp thư thường được sử dụng",
    	    	email: "Vui lòng nhập địa chỉ email hợp lệ"
    	   	},
    	   	password: {
    	    	required: "Xin vui lòng nhập mật khẩu",
    	    	rangelength: "Vui lòng nhập mật khẩu 6-16 bit, để các chữ cái phân biệt trường hợp"
    	   	},
    	   	checkbox: {
    	    	required: "Vui lòng chấp nhận giao thức người dùng"
    	   	}
    	},
    	errorPlacement:function(label, element){
    		if(element.attr("type") == "radio"){
    			label.insertAfter($(element).parents('ul')).css('marginTop','-20px');
				$('.user_registerRadio').css({'margin-top':'-10px','margin-bottom':'5px;'});
				$('.agreeNotice').css('margin-top','14px')
    		}else if(element.attr("type") == "checkbox"){
    			label.insertAfter($(element).parent()).css('clear','left');
    			$('#user_saveRegAccount').css('margin-top','20px');
    		}else{
    			label.insertAfter(element);
    		};	   		
    	},
    	submitHandler:function(form){    
    		var email = $('#user_LoginEmail').val();    		 
    		var password = $('#user_LoginPassword').val();
    		var type=$('#user_NoAccount').attr('value');
    		var resubmitToken = $('#resubmitToken').val();
    		var userType = $('.register_radio li.current input').val();
    		var idCode = $('#idCode').val();   
    		$(form).find(":submit").attr("disabled", true);
    		$.ajax({
    			type:'post',
    			data:{idCode:idCode,resubmitToken:resubmitToken,type:type,confirm:false,choseUser:1,userType:userType,email:email,password:password},
    			url:ctx+'/user/bindEmail.json',	   
    			dataType:'json',
    			success:function(result){
    				$('#resubmitToken').val(result.resubmitToken);	    				
    				if(result.success){
    					if(result.code==5 || result.code ==8 || result.code ==11 || result.code ==14 ){
    						window.location.href = ctx+'/user/registerSuccess.html';
    					}else{
    						//result.content == recordCode  == idCode
    						window.location.href = ctx+'/user/bindAccountActive.html?recordCode=' + result.content;	
    					}  					
    				}else if(result.success == false && result.code ==0 ){
    					/*Lỗi mật khẩu và thời gian chờ trang thông tin nhanh chóng*/
						//$('#hasNoAccount_beError').html(result.msg).show();
    					$('#noLagouAccount_beError').html(result.msg).show();
    				}else if(result.success == false && result.code ==100){
    					//Lỗi của dấu nhắc không đăng ký hộp thư
    					//$('#noLagouAccount_beError').html(result.msg).show(); 
    				}else if(result.success == false && result.code ==1){
    					$('#accountBindType').html('Thư');
    					$('#user_hasBindAccount').html(result.msg);
    					$('#user_loginCurrentAccount').html(result.content);
    					$('#user_confirmBindTips').html('Thay đổi hộp thư');
    					$.colorbox({inline:true, href:$("#bindTips"),title:"Tài khoản ràng buộc"});
    					$('#user_confirmBindTips').on('click',function(){
    						isLeave = true;
    						$.colorbox.close();
    						parent.jQuery.colorbox.close();
    					});	    					
    				}else if(result.success == false && result.code == 2){
    					$.colorbox({inline:true, href:$("#bindReplace"),title:"Tài khoản ràng buộc"});
    					$('#user_oldAccount').html(result.content[1]);
    					$('#user_typeAccount').html(result.content[2]);    					
    					$('#user_replaceAccount').html(result.content[3]);
    					//$('#user_name').html(result.content[1]);
    					$('#user_resumeScore').html(result.content[0][0]["resumeStatus"]);
    					$('#user_jobCount').html(result.content[0][0]["deliverCount"]);
    					$('#user_publishJob').html(result.content[0][0]["publishCount"]);
    					$('#user_receiveCount').html(result.content[0][0]["receiveCount"]);   					
    					$('#user_replaceResumeScore').html(result.content[0][1]["resumeStatus"]);
    					$('#user_replaceJobCount').html(result.content[0][1]["deliverCount"]);
    					$('#user_replacePublishJob').html(result.content[0][1]["publishCount"]);
    					$('#user_replaceReceiveCount').html(result.content[0][1]["receiveCount"]);   					   					 					
    				}
    				$(form).find(":submit").attr("disabled", false);
    			},
    			error:function(){
    				$(form).find(":submit").attr("disabled", false);
    			}			
    		});    
    	}
	});

	
	/**Nút xác nhận thứ hai trong tài khoản mới**/
	$('#newAccount_confirmBind1').click(function(){
		var email = $('#user_LoginEmail').val();    		 
		var password = $('#user_LoginPassword').val();
		var type=$('#user_NoAccount').attr('value');
		var userType = $('.register_radio li.current input').val();
		var idCode = $('#idCode').val();   
		var resubmitToken = $('#resubmitToken').val();
		var choseUser = $('.chooseAccount label.current').find('span.c3').attr('value');
		$.ajax({
			type:'post',
			data:{idCode:idCode, resubmitToken:resubmitToken, type:type,comfirm:true,choseUser:choseUser,userType:userType,email:email,password:password},
			url:ctx+'/user/bindEmail.json',	
			success:function(result){
				isLeave = true;
				$('#resubmitToken').val(result.resubmitToken);
				if(result.success){
					if(result.code==5 || result.code ==8 || result.code ==11 || result.code ==14 ){
						window.location.href = ctx+'/user/registerSuccess.html';
					}else{
						window.location.href = ctx+'/user/bindAccountActive.html?recordCode=' + result.content;
					} 				    					
				}else{	
					if(result.msg == undefined){
						result = eval('(' + result + ')');
					}
					if(result.msg == '' || result.msg == undefined || result.msg == null ){
						alert('Sự thất bại ràng buộc');
					}else if(result.msg =='Thời gian chờ trang，Vui lòng làm mới trang để tái sử dụng'){
						alert('Bạn đã nhấp thành công "để xác nhận ràng buộc”，Đừng lặp lại nhấp chuột');
					}else{
						errorTips("Sự thất bại ràng buộc：" + result.msg,"Tài khoản ràng buộc");
					}					
				}    									
			}
		});
	})
	/*** accountBind.html | wrote by vee ***/
	/*Xác nhận dân số để hủy cửa sổ Pop -up ràng buộc */
	$('#cancelBind').click(function(){
		$.colorbox({inline:true, href:$("#confirmUnbind"),title:"Tài khoản ràng buộc"});		
	});
	
	$('#cancleSina').click(function(){
		$.colorbox({inline:true, href:$("#confirmUnbindSina"),title:"Tài khoản ràng buộc"});
	});
	
	
	
	/*Xác nhận để hủy nút được xác định sau khi ràng buộc cửa sổ QQ Pop -up*/
	$('#user_confirmUnbind').click(function(){
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	})
	/*Xác nhận hủy nút xác định sau cửa sổ Pop -up Weibo ràng buộc*/
	$('#user_confirmUnbindSina').click(function(){
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	})
	/*Đúng -Plick gửi một hộp phiên thành công để tắt*/
	$('#confirmSuccess').click(function(){
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	})
	/*Tắt định dạng email không chính xác*/
	$('#confirmError').click(function(){
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	})
	/*Xác nhận rằng nút hủy bỏ bị hủy, không làm mới, đóng cửa sổ pop -up trực tiếp*/
	$('#confirmUnbind .cancel,#confirmUnbindSina .cancel , #confirm_unbindService .cancel').click(function(){
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	});
	
	$('#bindTips .btn,#bindTips .cancel').bind('click',function(){
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	});
	$('#bindReplace .cancel').bind('click',function(){
		$('#chooseRemainError').html('Vui lòng chọn một tài khoản bạn cần giữ').hide();    
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	})
	/*$('#bindReplaceForm .chooseAccount label').click(function(){
		if($(this).hasClass('current')){	
			$('#userIdCode').addClass('replaceChecked');			
			return;
		}else{		
			$('#userIdCode').removeClass('replaceChecked');
			$(this).removeClass('current').siblings('label').addClass('current');			
		}
	});*/

	//Sửa đổi trang mật khẩu
	$('#oldpassword').focus(function(){
		$('#updatePwd_beError').hide();
	})
	$('#updatePswForm').validate({
		/*onkeyup: false,
    	focusCleanup:false,*/
        rules: {
        	oldpassword: {
        		required:true,
    	    	rangelength: [6,16]
    	   	}, 
    	   	newpassword: {
    	   		required: true,
    	    	rangelength: [6,16]
    	   	},
    	   	comfirmpassword: {
    	   		required: true,
    	   	    equalTo: "#newpassword"
    	   	}
    	},
    	messages: {
        	oldpassword: {
        		required:"Vui lòng nhập mật khẩu hiện tại",
    	    	rangelength: "Vui lòng nhập mật khẩu 6-16 bit, để các chữ cái phân biệt trường hợp"
    	   	}, 
    	   	newpassword: {
    	   		required: "Vui lòng nhập mật khẩu mới",
    	    	rangelength: "Vui lòng nhập mật khẩu 6-16 bit, để các chữ cái phân biệt trường hợp"
    	   	},
    	   	comfirmpassword: {
    	   		required: "Vui lòng nhập lại mật khẩu mới",
    	    	equalTo: "Mật khẩu trong hai đầu vào không nhất quán, vui lòng nhập lại"
    	   	}
    	},
    	submitHandler:function(form){
    		var oldpassword = $('#oldpassword').val();
    		var newpassword = $('#newpassword').val();
    		var comfirmpassword = $('#comfirmpassword').val();
    		var resubmitToken = $('#resubmitToken').val();
    		$.ajax({
    			url:ctx+'/user/updatePwd.json',
    			type:'POST',
    			data:{
    				oldPassword:oldpassword,
    				newPassword:newpassword,
    				newPassword2:comfirmpassword,
    				resubmitToken:resubmitToken
    			},
            	dataType:'json'
    		}).done(function(result){
				$('#resubmitToken').val(result.resubmitToken);
    			if(result.success){
    				$.colorbox({inline:true, href:$("#updatePassword"),title:"mật khẩu đã được cập nhật"});
    				setCountdown(4,'updatePassword h4 span',ctx+"/user/logout.html");	
    			}else{
    				$('#updatePwd_beError').html(result.msg).show();
    			}
    		});
        }  
    });
	

	$('.user_confirmDel').click(function(){
		$.colorbox({inline:true, href:$("#confirm_unbindService"),title:"Nâng dịch vụ tuyển dụng"});
	});
	$('#confirm_unbind').click(function(){
		$.ajax({
			url:ctx+'/user/closeService.json',
			type:'POST',
			dataType:'json'
		}).done(function(result){
			if(result.success){
				$.colorbox({inline:true, href:$("#unbindService"),title:"Nâng dịch vụ tuyển dụng"});
				setCountdown(4,'unbindService h4 span',ctx+"/corpCenter/bindStep1.html");	
			}else{
				alert(result.msg);
			}
		});
	})
});

function setCountdown(time,id,url){
	var count = setTimeout(function(){$("#"+id).html(time);setCountdown(time-1,id,url)},1000);
	if(time==0){
		clearTimeout(count);
		top.location.href=url;
	}
}