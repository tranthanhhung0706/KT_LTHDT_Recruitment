
<!DOCTYPE HTML>
<html>
<head>
<script id="allmobilize" charset="utf-8" src="/home/style/js/allmobilize.min.js"></script>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate" media="handheld"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Web tuyển dụng</title>

<meta content="" name="description">
<meta content="" name="keywords">



<!-- <div class="web_root"  style="display:none">h</div> -->
<script type="text/javascript">
</script>

<link href="THUCTAPTOTNGHIEP_N19DCCN028" rel="Shortcut Icon">
<link rel="stylesheet" type="text/css" href="/home/style/css/style.css"/>

<link href="/home/style/css/popup.css" type="text/css" rel="stylesheet">
<link href="/home/style/css/external.min.css" type="text/css" rel="stylesheet">



<script src="/home/style/js/jquery.1.10.1.min.js" type="text/javascript"></script>

<script type="text/javascript" src="/home/style/js/jquery.lib.min.js"></script>
<script src="/home/style/js/setting.js"></script>
<script src="/home/style/js/core.min.js" type="text/javascript"></script>
<script src="/home/style/js/popup.min.js" type="text/javascript"></script>

<script type="text/javascript">

</script> 
<script type="text/javascript" src="/home/style/js/conv.js"></script>
<script src="/home/common/confirm_null.js"></script>
</head>

<body id="login_bg">
	<div class="login_wrapper">
		<div class="login_header">
            <#--  <div id="cloud_s"><img src="/home/style/images/cloud_s.png" width="81" height="52" alt="cloud" /></div>
            <div id="cloud_m"><img src="/home/style/images/cloud_m.png" width="136" height="95"  alt="cloud" /></div>  -->
        </div>
        
    	<input type="hidden" id="resubmitToken" value="9b207beb1e014a93bc852b7ba450db27" />		
		<div class="login_box">
        	<form id="loginForm">
        		<ul class="register_radio clearfix">
		            <li>
		            	ứng viên
		              	<input type="radio" value="0" id="type0" name="type" /> 
		            </li>
		            <li>
		           	   tuyển dụng
		              	<input type="radio" value="1" id="type1" name="type" /> 
		            </li>
		        </ul> 
		        <span for="type" generated="true" class="error" id="error_type" style="display:none">Vui lòng kiểm tra mục đích đăng ký</span>
            	<input type="text" id="Email" name="Email" tabindex="1" class="require" placeholder="Vui lòng nhập địa chỉ hộp thư thường được sử dụng"  tips="Địa chỉ hộp thư không thể trống！"  attributes="error_email" />
            	<span for="email" generated="true" class="error" id="error_email" style="display:none">Vui lòng nhập địa chỉ email</span>
            	
                <input type="password" id="Password" name="Password" class="require" tabindex="2" placeholder="Xin vui lòng nhập mật khẩu"  tips="Mật khẩu không thể trống！" attributes="error_password"  />
                <span for="password" generated="true" class="error" id="error_password" style="display:none">Xin vui lòng nhập mật khẩu</span>
                
                <input type="text" id="username" name="username" class="require" tabindex="3" placeholder="Hãy nhập tên"   tips="tên nên được điền！" attributes="error_username" />
                <span for="username" generated="true" class="error" id="error_username" style="display:none">Hãy nhập tên</span>
                
            	<input type="text" id="cpacha" name="cpacha" class="require" tabindex="4" placeholder="Nhập mã xác nhận" /  tips="Mã xác minh phải được điền！" attributes="error_cpacha" >
            	&nbsp;&nbsp;<a onclick="sendMailCpacha();" href="javascript:void(0);">Gửi mã xác minh</a>
            	<span for="cpacha" generated="true" class="error" id="error_cpacha" style="display:none">nhập mã xác nhận</span>
            	
            	
                <input type="button" id="submitLogin" value="Đăng ký" />
              
            </form>
            <div class="login_right">
            	<div>Tài khoản hiện có</div>
            	<a  href="/home/system/login"  class="registor_now">Đăng nhập trực tiếp</a>
            </div>
        </div>
        <div class="login_box_btm"></div>
    </div>
    
<#include "../common/alert.ftl"/>
    
<script type="text/javascript">

	//Mục đích của việc kiểm tra là lựa chọn mặc định của lần trước
	window.onload = function(){
		var type =$('input[type="radio"]:checked',loginForm).val();
		if(type == 0)
			$("#type0").click();
		else if(type == 1)
			$("#type1").click();
 　　	}

    //Gửi mã xác minh email
    function sendMailCpacha()
    {
    	var reg=/^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;
        if(!reg.test(document.getElementById("Email").value))
        { 
       		//Nếu định dạng hộp thư không chính xác
        	$("#errorMsg").html("Việc gửi không thành công, định dạng của hộp thư không chính xác !!!");
            $.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
        }
        else{
           var email =  $('#Email').val(); //Nhận địa chỉ hộp thư được nhập bởi người dùng
           var type = "user_register"; //Loại thư là xác minh đăng ký người dùng
           $.ajax({
				url:'/common/cpacha/generate_emailCpacha',
				dataType:'json',
				type:'post',
				data:{receiver:email,type:type},
				success:function(data){
					if(data.code == 0){
						$("#successMsg").html("Thư đã được gửi thành công!");
						$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
					}else{
						$("#errorMsg").html("Gửi thất bại，"+data.msg+"!!!");
            			$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
					}
				}
			});
        } 
    }
    
      //Đăng ký nộp đơn
      $("#submitLogin").click(function() {
         	var type =$('input[type="radio"]:checked',loginForm).val();
			$('#error_type').css('display','none');
			$('#error_email').css('display','none');
			$('#error_password').css('display','none');
			$('#error_username').css('display','none');
			$('#error_cpacha').css('display','none');
			
			//Một biểu mẫu thống nhất không phải là xác minh trống
			if(!checkForm('loginForm'))
			{
				return;
			}
         	
        	var data = $("#loginForm").serialize(); 
       		$.ajax({
				url:'/home/system/register',
				dataType:'json',
				type:'post',
				data:data,
				success:function(data){
					if(data.code == 0){
						$("#successMsg").html("Đăng ký thành công!");
						$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
					}else{
						switch(data.code){
										case -1010:
											$('#error_type').css('display','block');
											$("#errorMsg").html("Đăng ký không thành công，"+data.msg+"!!!");
            					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
            					break;
            				case -3:
            					$('#error_cpacha').css('display','block');
            					$("#errorMsg").html("Đăng ký không thành công，"+data.msg+"!!!");
            					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
            					break;
            				case -1000:
            					$('#error_username').css('display','block');
            					$("#errorMsg").html("Đăng ký không thành công，"+data.msg+"!!!");
            					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
            					break;
            				case -1001:
            					$('#error_password').css('display','block');
            					$("#errorMsg").html("Đăng ký không thành công，"+data.msg+"!!!");
            					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
            					break;
            				case -1006:
            					$('#error_email').css('display','block');
            					$("#errorMsg").html("Đăng ký không thành công，"+data.msg+"!!!");
            					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
            					break;
            				default:
            					$("#errorMsg").html("Đăng ký không thành công,"+data.msg+"!!!");
            					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
            					break;
						}
					}
				}
			});
       
       })
       
        
   
    
    
</script>
</body>
</html>
