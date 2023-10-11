jQuery.noConflict(true);
$(function(){
	$('.popup .filePrew').hover(function(){
		$(this).parent('.btn_addPic').addClass('btn_addPic_hover');
	},function(){
		$(this).parent('.btn_addPic').removeClass('btn_addPic_hover');
	});
	
	/*** check Mobile***********************/ 	
	jQuery.validator.addMethod("isMobile", function(value, element,param) {
		var pattern=/(^0{0,1}[13|15|18|14]{2}[0-9]{9}$)/;
		if(pattern.test(value)){ 
			return true; 
		}else{ 
			return false; 
		} 
	}, "Xin vui lòng nhập một số điện thoại hợp lệ");
	
	/*** check Ký tự đặc biệt ***********************/
	jQuery.validator.addMethod("specialchar", function(value, element) {
		var reg = /^([`~!@$^&':;,?~！……&；：。，、？=])/;
		return this.optional(element) || !reg.test(value);
		}, "Vui lòng nhập chữ viết tắt của công ty hợp lệ");
	
	/*** Không thể nhập số**********************/
	jQuery.validator.addMethod("checkNum",function(value, element) {
		var reg = /^[0-9]*$/;//Chỉ có thể nhập số
		return this.optional(element) || !reg.test(value);
		}, "Vui lòng nhập giới thiệu câu hợp lệ"); 
	
	/************************
	 * Chỉnh sửa nhãn
	 */
	$('#hasLabels').on('click','.link',function(){
		
        	$('#addLabel').show();
	        $(this).remove();
			$('#hasLabels li').each(function(){
				$(this).append('<i>x</i>');
			});
			$('#hasLabels').on('mouseenter','li',function(){
				$(this).css({marginRight:'6px',cursor:'pointer'});
			});
			$('#hasLabels').on('mouseleave','li',function(){
				$(this).css({marginRight:'18px',cursor:'pointer'});
			});
		
	});
	
	
	/************************
	 * Nhãn
	 */
	$('#hasLabels').on('click','li i',function(){
		var labelVal = $(this).prev('span').text();
		var _this = $(this);
		var companyid = $('#companyid').val();
		$.ajax({
			type:'post',
			url: ctx+'/companyLabel/removeOneLabelToCompany.json',
			data:{companyId:companyid,label:labelVal},
			dataType: 'json'
		}).success(function (result) {
			if(result.success){
				_this.parent().remove();
			}else{
				alert(result.msg);
			}
		});
	});


	$('#add').bind('click',function(){
		var _label = $('#label');
		var labelVal = $.trim(_label.val());
		var companyid = $('#companyid').val();
		var judge = true;
		if( labelVal.length<=6 && labelVal.length > 0){
			$('#hasLabels li').each(function(){
				if($('span',this).text() == labelVal){
					judge = false;
				}
			});
			if(judge){
				$.ajax({
					type:'post',
					url: ctx+'/companyLabel/pasteOneLabelToCompany.json',
					data:{companyId:companyid,label:labelVal},
					dataType: 'json'
				}).success(function (result) {
					if(result.success){
						$('#hasLabels').append('<li><span>'+labelVal+'</span><i>x</i></li>');
					}else{
						alert(result.msg);
					}
				});
				
			}else{
				alert('Nhãn này đã tồn tại，Vui lòng nhập từ đầu vào mới');
			}
			_label.val('');
		}else{
			alert("Vui lòng nhập thẻ tùy chỉnh 1-6 từ");
		}
	});


	$('#label').keydown(function(e){
		if(e.which == 13){
			$('#add').trigger('click');
		}
	});
	
	//2013/9/27
	$('#resumeSetForm a.reUpload').bind('click',function(){
		$('#resumeSetForm span.error').hide();
		$('#reUploadResume1').click();
	});
	
	$('#resumeSendForm a.reUpload').bind('click',function(){
		$('#resumeSendForm span.error').hide();
		$('#reUploadResume2').click();
	});
	
	//Thay đổi bản gửi biểu mẫu sơ yếu lý lịch
	$('#resumeSetForm').validate({
        rules: {
        	resumeName: {
        		required: true
    	   	}
        },
    	messages: {
    		resumeName: {
        		required: "Vui lòng chọn sơ yếu lý lịch giao hàng mặc định"
    	   	}
    	},
    	errorPlacement:function(label, element){
    		label.insertBefore($(element).parent().siblings('.bgPink'));
    	},
    	submitHandler:function(form){
    		var resumeName = $('input[name="resumeName"]:checked',form).val();
    		$(form).find(":submit").attr("disabled", true);
    		$.ajax({
				url:ctx+'/mycenter/resume/setDefaultResume.json',
				type:'POST',
	        	data: {type:resumeName}
			}).done(function(result) {
	        	if(result.success){
	        		top.location.reload();
	        	}else{
					console.log(result.msg);
	        	}
	        	$(form).find(":submit").attr("disabled", false);
			});
    	}
	});
	
	$('#resumeSendForm input[type="checkbox"]').bind('click',function(){
		if($(this).attr('checked')){
			$(this).attr('checked',false);
		}else{
			$(this).attr('checked',true);
		}
	});
	
	$('#resumeSendForm').validate({
        rules: {
        	resumeName: {
        		required: true
    	   	}
        },
    	messages: {
    		resumeName: {
        		required: "Vui lòng chọn sơ yếu lý lịch giao hàng mặc định"
    	   	}
    	},
    	errorPlacement:function(label, element){
    		label.insertBefore($(element).parent().siblings('.bgPink'));
    	},
    	submitHandler:function(form){
			var userid = $('#userid').val();
    		var jobid = $('#jobid').val();
    		var resumeName = $('input[name="resumeName"]:checked',form).val();
    		var checkDefault = -1;
    		if($('input[type="checkbox"]',form).attr('checked')){
    			checkDefault = 1;
    		}
    		var resubmitToken = $('#resubmitToken').val();
    		$(form).find(":submit").attr("disabled", true);
    		$.ajax({
    			url: ctx+'/mycenter/deliverResumeBeforce.json',
    			data:{
    				userId:userid,
    				positionId:jobid,
    				type:resumeName,
    				remember:checkDefault,
    				resubmitToken:resubmitToken
    			},
				type:'POST',
            	dataType:'json'
			}).done(function(r) {
        		$('#resubmitToken').val(r.resubmitToken);
	        	if(r.success){
	        		$("#deliverResumesSuccess p.count").html(r.msg);
	        		if(r.code == 10){
	        			$("#deliverResumesSuccess p.share").removeClass('dn');
	        			$("#deliverResumesSuccess .drawGGJ").siblings('tr.drawQD').hide();
	        		}
	        		$.colorbox({inline:true, href:$("#deliverResumesSuccess"),title:"Bản tóm tắt"});
	        	}else{
	        		if(r.code ==7){
						errorTipsSet("giao hàng thất bại：" + r.msg,"Bản tóm tắt");
					}else if(r.code ==20){
						$('#deliverResumeConfirm input[name="type"]').val(0);
						$('#deliverResumeConfirm .msg').html(r.msg);
						$.colorbox({inline:true, href:$("#deliverResumeConfirm"),title:"Gửi xác nhận sơ yếu lý lịch của bạn"});
					}else if(r.code ==21){
						$('#deliverResumeConfirm input[name="type"]').val(1);
						$('#deliverResumeConfirm .msg').html(r.msg);
						$.colorbox({inline:true, href:$("#deliverResumeConfirm"),title:"Gửi xác nhận sơ yếu lý lịch của bạn"});
					}else if(r.code ==22){//Cần bật lên hộp xác nhận thông tin -đã đưa ra sơ yếu lý lịch - type=0
						openProfileBox(0);
					}else if(r.code ==23){//Cần bật lên Hộp xác nhận thông tin -Sơ yếu lý lịch trực tuyến được cung cấp - type=1
						openProfileBox(1);
					}else{
						errorTips("giao hàng thất bại：" + r.msg,"Bản tóm tắt");
					}
	        	}
	        	$(form).find(":submit").attr("disabled", false);
			});
    	}
	});
	
	$('#resumeSendForm label.radio').bind('click',function(){
		if( $(this).children('span').hasClass('red')){
			$('#resumeSendForm .btn_profile_save').attr('disabled','disabled').css('backgroundColor','#a3a3a3');
		}else{
			$('#resumeSendForm .btn_profile_save').removeAttr('disabled').css('backgroundColor','#019875');
		}
	});
	
	$('#resumeSetForm label.radio').bind('click',function(){
		if( $(this).children('span').hasClass('red')){
			$('#resumeSetForm .btn_profile_save').attr('disabled','disabled').css('backgroundColor','#a3a3a3');
		}else{
			$('#resumeSetForm .btn_profile_save').removeAttr('disabled').css('backgroundColor','#019875');
		}
	});
	
	$('.resumeShowAll .setDefault').bind('click',function(){
		var type= $(this).attr('rel');
		$.ajax({
			url:ctx+'/mycenter/resume/setDefaultResume.json',
			type:'POST',
        	data: {type:type},
        	dataType:'json'
		}).done(function(result) {
        	if(result.success){
        		top.location.reload();
        	}else{
				console.log(result.msg);
        	}
		});
	});
	
	$(document).click(function(){
		$('.boxUpDown').hide().prev('input').removeClass('select_focus');
	});
	
	$('#select_degree').bind('click',function(e){
		e.stopPropagation();
		$('#select_workyear').removeClass('select_focus');
		$('#select_expectCity').removeClass('select_focus');
		$('#box_workyear').hide();
		$('#box_expectCity').hide();
		$(this).addClass('select_focus');
		$('#box_degree').show();
	});
	$('#box_degree').on('click','ul li',function(e){
		e.stopPropagation();
		var val = $(this).text();
		$('#select_degree').val(val).css('color','#333').removeClass('select_focus');
		$('#degree').val(val);
		$('#box_degree').hide(); 
		$(this).parents("#basicInfoForm").validate().element( $('#degree') ); 
	});
	
	$('#select_workyear').bind('click',function(e){
		e.stopPropagation();
		$('#select_degree').removeClass('select_focus');
		$('#select_expectCity').removeClass('select_focus');
		$('#box_degree').hide();
		$('#box_expectCity').hide();
		$(this).addClass('select_focus');
		$('#box_workyear').show();
	});
	$('#box_workyear').on('click','ul li',function(e){
		e.stopPropagation();
		var val = $(this).text();
		$('#select_workyear').val(val).css('color','#333').removeClass('select_focus');
		$('#workyear').val(val);
		$('#box_workyear').hide(); 
		$(this).parents("#basicInfoForm").validate().element( $('#workyear') ); 
	});
	
	$('#select_expectCity').bind('click',function(e){
		e.stopPropagation();
		$('#select_workyear').removeClass('select_focus');
		$('#select_degree').removeClass('select_focus');
		$('#box_workyear').hide();
		$('#box_degree').hide();
		$(this).addClass('select_focus');
		$('#box_expectCity').show();
	});
	$('#box_expectCity').on('click','dl dd span',function(e){
		e.stopPropagation();
		var val = $(this).text();
		$('#select_expectCity').val(val).css('color','#333').removeClass('select_focus');
		$('#expectCity').val(val);
		$('#box_expectCity').hide(); 
		$(this).parents("#basicInfoForm").validate().element( $('#expectCity') ); 
	});
	
	$('#basicInfoForm').validate({
		/*debug: true,*/
        rules: {
        	name: {
        		required: true,
        		specialchar:true,
        		checkNum:true,
        		rangelength:[2,60]
    	   	},
    	   	degree: {
        		required: true
    	   	},
    	   	workyear: {
        		required: true
    	   	},
    	   	expectCity: {
        		required: true
    	   	},
    	   	tel: {
        		required: true,
        		isMobile:true,
    	    	maxlength:30
    	   	},
    	   	email: {
        		required: true,
        		email:true,
    	    	maxlength:80
    	   	}
        },
    	messages: {
    		name: {
        		required: "Vui lòng nhập tên thật của bạn",
        		specialchar:"Vui lòng nhập tên thật của bạn",
        		checkNum:"Vui lòng nhập tên thật của bạn",
        		rangelength:"Vui lòng nhập tên thật của bạn"
    	   	},
    	   	degree: {
        		required: "Vui lòng chọn mức độ cao nhất"
    	   	},
    	   	workyear: {
        		required: "Vui lòng chọn thời gian làm việc"
    	   	},
    	   	expectCity: {
        		required: "Vui lòng chọn thành phố làm việc dự kiến"
    	   	},
    	   	tel: {
    	   		required: "Vui lòng nhập số điện thoại",
        		isMobile:"Xin vui lòng nhập một số điện thoại hợp lệ",
    	    	maxlength:"Xin vui lòng nhập một số điện thoại hợp lệ"
    	   	},
    	   	email: {
        		required: "Vui lòng nhập hộp thư thông báo phỏng vấn nhận",
        		email:"Vui lòng nhập các hộp thư thường được sử dụng hợp lệ",
    	    	maxlength:"Vui lòng nhập các hộp thư thường được sử dụng hợp lệ"
    	   	}
    	},
    	errorPlacement:function(label, element){
    		if(element.attr("type") == "hidden"){
    			label.appendTo($(element).parent());
    		}else{
    			label.insertAfter(element);
    		}
    	},
    	submitHandler:function(form){
    		var name = $('input[name="name"]',form).val();
    		var degree = $('input[name="degree"]',form).val();
    		var workyear = $('input[name="workyear"]',form).val();
    		var expectCity = $('input[name="expectCity"]',form).val();
    		var tel = $('input[name="tel"]',form).val();
    		var email = $('input[name="email"]',form).val();
    		var type = $('input[name="type"]',form).val();
    		var jobid = $('#jobid').val();
    		var resubmitToken = $('#resubmitToken').val();
    		$(form).find(":submit").attr("disabled", true);
    		$.ajax({
				url:ctx+'/resume/saveConfirmAndDeliver.json',
				type:'POST',
	        	data: {
	        		name:name,
	        		highestEducation:degree,
	        		workYear:workyear,
	        		city:expectCity,
	        		phone:tel,
	        		email:email,
	        		type:type,
	        		positionId:jobid,
	        		deliver:true,
					resubmitToken:resubmitToken
	        	},
            	dataType:'json'
			}).done(function(r) {
	    		$('#resubmitToken').val(r.resubmitToken);
	        	if(r.success){
	        		$("div#deliverResumesSuccess p.count").html(r.msg);
					if(r.code == 10){
	        			$("#deliverResumesSuccess p.share").removeClass('dn');
	        			$("#deliverResumesSuccess .drawGGJ").siblings('tr.drawQD').hide();
	        		}
					$.colorbox({inline:true, href:$("div#deliverResumesSuccess"),title:"Bản tóm tắt"});
	        	}else{
	        		if(r.code ==7){
						errorTipsSet("giao hàng thất bại：" + r.msg,"Bản tóm tắt");
					}else if(r.code ==20){
						$('#deliverResumeConfirm input[name="type"]').val(0);
						$('#deliverResumeConfirm .msg').html(r.msg);
						$.colorbox({inline:true, href:$("#deliverResumeConfirm"),title:"Gửi xác nhận sơ yếu lý lịch của bạn"});
					}else if(r.code ==21){
						$('#deliverResumeConfirm input[name="type"]').val(1);
						$('#deliverResumeConfirm .msg').html(r.msg);
						$.colorbox({inline:true, href:$("#deliverResumeConfirm"),title:"Gửi xác nhận sơ yếu lý lịch của bạn"});
					}else if(r.code ==22){
						openProfileBox(0);
					}else if(r.code ==23){
						openProfileBox(1);
					}else{
						errorTips("giao hàng thất bại：" + r.msg,"Bản tóm tắt");
					}
	        	}
	        	$(form).find(":submit").attr("disabled", false);
			});
    	}
	});
	
	
	$('#deliverResumeConfirm .edit_field').bind('click',function(){
		var type = $('#deliverResumeConfirm input[name="type"]').val();
		 openProfileBox(type);
	})
	

	$('.saoma').hover(function(){
		$(this).addClass('open');
	},function(){
		$(this).removeClass('open');
	});
	

	$('.like_record').click(function(){
		var _this = $(this);
		var id = _this.attr('data-id');
		var num = _this.attr('data-no');
		$.ajax({
			url:ctx+'/mycenter/expeUsefulIncrement.json',
			type:'POST',
        	data: {expeId:id,type:1},
        	dataType:'json'
		}).done(function(result) {
        	if(result.success){
        		num++;
        		_this.children('span').text(num);
        	}else{
        		_this.children('i').html(result.msg).fadeIn(200).delay(1500).fadeOut(200);
        	}
		});
	});
	

	$('.remarkMore').click(function(){
		if($(this).hasClass('toggle_expand')){
			$(this).text('Hơn').removeClass('toggle_expand').siblings('.remark_content').addClass('dn').siblings('.remark_content_short').removeClass('dn');
		}else{
			$(this).text('Cất đi').addClass('toggle_expand').siblings('.remark_content_short').addClass('dn').siblings('.remark_content').removeClass('dn');
		}
	});
	

	var jobCollection = false;
	$('#jobCollection').click(function(e){
		if(e.target == this){
			var _this = $(this);
			if(_this.hasClass('collected') && jobCollection){
				_this.children('.jd_collection_success').stop(true,true).fadeIn(200).delay(3000).fadeOut(200);
			}else{
				var id = $('#jobid').val();
				var resubmitToken = $('#resubmitToken').val();
				$.ajax({
					url:ctx+'/mycenter/collectPositoin.json',
					type:'POST',
					data:{
						positionId:id,
						type:1,
						resubmitToken:resubmitToken
					},
					dataType:'json'
				}).done(function(result){
					if(result.success){
			    		$('#resubmitToken').val(result.resubmitToken);
						_this.addClass('collected');
						_this.children('.jd_collection_success').stop(true,true).fadeIn(200).delay(3000).fadeOut(200);
						jobCollection = true;
					}else{
						alert(result.msg);
					}
				});
			}
		}
	});
	
	//close collection success tip
	$('.jd_collection_x,.jd_collection_close').click(function(){
		$('#tipOverlay').hide();
		$(this).parent('.jd_collection_success').hide();
		return false;
	});
	
	//close collection tip 
	$('.jd_collection_tip .jd_collection_tip_x').click(function(){
		$('#tipOverlay').hide();
		$(this).parent('.jd_collection_tip').hide();
		return false;
	});
});

function popQR(){
	$.ajax({
		url:ctx+"/mycenter/showQRCode",
		type:"GET"
		/*async:false*/
	}).done(function(data){
		if(data.success){
			$('.saoma .drop_l img').attr("src",data.content);
			$('#deliverResumesSuccess .weixinQR .qr img').attr("src",data.content);
			$('#deliverResumesSuccess .weixinQR').removeClass('dn');
		}
	});	
}


 function sendResume(userId,jobId,force,typeDefault){
	 var resubmitToken = $('#resubmitToken').val();
	 var type = null;
	 if(typeDefault){
		 type = $('#deliverResumeConfirm input[name="type"]').val();
	 }
		$.ajax({
			url: ctx+'/mycenter/deliverResumeBeforce.json',
			type:'POST',
			async:false,
			data:{
				userId:userId,
				positionId:jobId,
				force:force,
				type:type,
				resubmitToken:resubmitToken
			},
        	dataType:'json'
		}).done(function (r) {
    		$('#resubmitToken').val(r.resubmitToken);
			if(r.success){
				$("#deliverResumesSuccess p.count").html(r.msg);
				if(r.code == 10){
        			$("#deliverResumesSuccess p.share").removeClass('dn');
        			$("#deliverResumesSuccess .drawGGJ").siblings('tr.drawQD').hide();
        		}
				$.colorbox({inline:true, href:$("#deliverResumesSuccess"),title:"Bản tóm tắt"});
			}else{
				if(r.code ==7){
					errorTipsSet("giao hàng thất bại：" + r.msg,"Bản tóm tắt");
				}else if(r.code ==20){
					$('#deliverResumeConfirm input[name="type"]').val(0);
					$('#deliverResumeConfirm .msg').html(r.msg);
					$.colorbox({inline:true, href:$("#deliverResumeConfirm"),title:"Gửi xác nhận sơ yếu lý lịch của bạn"});
				}else if(r.code ==21){
					$('#deliverResumeConfirm input[name="type"]').val(1);
					$('#deliverResumeConfirm .msg').html(r.msg);
					$.colorbox({inline:true, href:$("#deliverResumeConfirm"),title:"Gửi xác nhận sơ yếu lý lịch của bạn"});
				}else if(r.code ==22){
					openProfileBox(0);
				}else if(r.code ==23){
					openProfileBox(1);
				}else{
					errorTips("giao hàng thất bại：" + r.msg,"Bản tóm tắt");
				}
			}
		});
	
}

function openProfileBox(type){
	$.ajax({
		url: ctx+'/resume/queryConfirmInfo.json',
		data:{type:type},
		dataType: 'json'
	}).done(function (data) {
		if(data.success){
			$('#basicInfoForm input[name="name"]').val(data.content.name);
			if(data.content.highestEducation){
				$('#basicInfoForm input[name="degree"]').val(data.content.highestEducation);
				$('#basicInfoForm #select_degree').val(data.content.highestEducation).css('color','#333');
			}
			if(data.content.workYear){
				$('#basicInfoForm input[name="workyear"]').val(data.content.workYear);
				$('#basicInfoForm #select_workyear').val(data.content.workYear).css('color','#333');
			}
			if(data.content.city){
				$('#basicInfoForm input[name="expectCity"]').val(data.content.city);
				$('#basicInfoForm #select_expectCity').val(data.content.city).css('color','#333');
			}
			$('#basicInfoForm input[name="tel"]').val(data.content.phone);
			$('#basicInfoForm input[name="email"]').val(data.content.email);
			$('#basicInfoForm input[name="type"]').val(data.content.type);

			var workTimes = '<ul>';
			for(var i=0; i<data.content.workTimes.length; i++){
				workTimes += '<li>'+data.content.workTimes[i]+'</li>';
			}
			workTimes += '</ul>';
			$('#basicInfoForm #box_workyear').html(workTimes);
		
			var citys = '';
			for(var i=0; i<data.content.citys.length; i++){
				citys += '<dl><dt>'+data.content.citys[i].nameStr+'</dt><dd>';
				for(var j=0; j<data.content.citys[i].cityList.length; j++){
					 citys += '<span>'+data.content.citys[i].cityList[j]+'</span>';
				}
				citys += '</dd></dl>';      			
			}
			$('#basicInfoForm #box_expectCity').html(citys);
	
			var degreeList = '<ul>';
			for(var i=0; i<data.content.degrees.length; i++){
				degreeList += '<li>'+data.content.degrees[i]+'</li>';
			}
			degreeList += '</ul>';
			$('#basicInfoForm #box_degree').html(degreeList);
			
			$("#basicInfoForm").find('span.error').hide(); 
			

			$.colorbox({inline:true, href:$("#infoBeforeDeliverResume"),title:"Xác nhận thông tin cá nhân"});	 
		}else{
			alert(data.msg);
		}
	});
 }
 
 //Đặt sơ yếu lý lịch trực tuyến, nhưng sơ yếu lý lịch trực tuyến là các mẹo không đầy đủ
function errorTipsSet(msg){
	$.colorbox({
		html:'<div class="popup" style="width:460px;">'+
				'<table width="100%">'+
					'<tr>'+
						'<td align="center"><h4 class="error_msg" style="width:400px;">'+msg+'</h4></td>'+
					'</tr>'+
					'<tr>'+
						'<td align="center"><a href="'+ctx+'/resume/myresume.html" target="_blank" class="btn_s">Gửi nó ngay lập tức</a></td>'+
					'</tr>'+
				'</table>'+
			'</div>',
		title:'Thông báo lỗi'
	});

}
 

function file_check(obj,action_url,id)
{
	$('#loadingImg').css("visibility","visible");
	var obj = $('#' + id);
	var userId = $('#userid').val();
	
	this.AllowExt='.doc,.docx,.pdf,.ppt,.pptx,.txt,.wps';
	this.FileExt=obj.val().substr(obj.val().lastIndexOf(".")).toLowerCase();

	if(this.AllowExt != 0 && this.AllowExt.indexOf(this.FileExt)==-1)
	{
		if(id == 'reUploadResume1'){
			$('#setResume span.error').show();
		}else if(id == 'reUploadResume2'){
			$('#setResumeApply span.error').show();
		}else{
			errorTips("Chỉ hỗ trợ word、pdf、ppt、txt、wpsTệp định dạng，Vui lòng tải lên");
			$('#loadingImg').css("visibility","hidden");
		}
	}else if(this.FileExt == ''){
	 	return false;
	}else{
		$.ajaxFileUpload ({
			type:'POST',
			url: action_url,
			secureuri:false,
			fileElementId:id,
			data:{userId:userId},
			dataType: 'text',
			success: function (jsonStr) {
				var json = eval('(' + jsonStr + ')');
				$('#loadingImg').css("visibility","hidden");
				if(json.success){
					var nearbyName = '';
					if(json.content.nearbyName.length>18){
						nearbyName = json.content.nearbyName.substring(0,15)+'...';
					}else{
						nearbyName = json.content.nearbyName;
					}
					$('#resumeSendForm .btn_profile_save').removeAttr('disabled').css('backgroundColor','#019875');
					$('#resumeSetForm .btn_profile_save').removeAttr('disabled').css('backgroundColor','#019875');
					if(id == 'reUploadResume1'){
						$('#setResume .uploadedResume').text(nearbyName).attr('title',json.content.nearbyName).removeClass('red');
						$('#setResume .downloadResume').removeClass('dn').siblings('span').removeClass('dn');
						$('#setResume .reUpload').text('Tải lại lại');
					}else if(id == 'reUploadResume2'){
						$('#setResumeApply .uploadedResume').text(nearbyName).attr('title',json.content.nearbyName).removeClass('red');
						$('#setResumeApply .downloadResume').removeClass('dn').siblings('span').removeClass('dn');
						$('#setResumeApply .reUpload').text('Tải lại lại');
					}else{
						$.colorbox({inline:true, href:$("div#uploadFileSuccess"),title:"Tải lên sơ yếu lý lịch"});
					}
				}else{

					
					if(json.code==-1){
						$.colorbox({inline:true, href:$("div#fileResumeUpload"),title:"Tải lên sơ yếu lý lịch tải lên không thành công"});
					}else if(json.code==-2){
						$.colorbox({inline:true, href:$("div#fileResumeUploadSize"),title:"Tải lên sơ yếu lý lịch tải lên không thành công"});
					}else{
						errorTips("Tiếp tục tải lên không thành công，Vui lòng tải lên");
					}				
				} 
			},
			error:function(err){
				errorTips("Tiếp tục tải lên không thành công，Vui lòng tải lên");
			}
		})//end of ajax
		
	}
} 