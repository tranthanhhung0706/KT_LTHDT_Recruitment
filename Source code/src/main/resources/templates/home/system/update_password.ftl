
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb"><head>
</script><script src="/home/style/js/allmobilize.min.js" charset="utf-8" id="allmobilize"></script><style type="text/css"></style>
<meta content="no-siteapp" http-equiv="Cache-Control">
<link  media="handheld" rel="alternate">

<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Sửa đổi mật khẩu-Web tuyển dụng Internet chuyên nghiệp nhất</title>

<meta name="description" content="">
<meta name="keywords" content="">


<script type="text/javascript">

</script>
<link href="THUCTAPTOTNGHIEP_N19DCCN028" rel="Shortcut Icon">
<link href="/home/style/css/style.css" type="text/css" rel="stylesheet">
<link href="/home/style/css/external.min.css" type="text/css" rel="stylesheet">
<link href="/home/style/css/popup.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/home/style/js/jquery.1.10.1.min.js"></script>
<script src="/home/style/js/jquery.lib.min.js" type="text/javascript"></script>
<script src="/home/style/js/additional-methods.js" type="text/javascript"></script>
<script src="/home/style/js/setting.js"></script>
<script type="text/javascript">

</script> 
<script src="/home/style/js/conv.js" type="text/javascript"></script>

<body>
<div id="body">
	<#include "../common/top_menu.ftl"/>
    <div id="container">
        	<div class="user_bindSidebar">
		    <dl id="user_sideBarmenu" class="user_sideBarmenu">
		     	        <dt><h3>Cài đặt tài khoản</h3></dt>
		       			<dd><a class="hover" href="/home/system/update_password">đổi mật khẩu</a></dd>
			</dl>
			</div>
			<div class="content user_modifyContent">
       		 <dl class="c_section">
            <dt>
            	<h2><em></em>đổi mật khẩu</h2>
            </dt>
            	<dd>
            		<table class="savePassword">
            			<tbody><tr>
            				<td>Đăng nhập e-mail</td>
            				<td class="c7">${user.email!""}</td>
            			</tr>
            			<tr>
            				<td class="label">Mật khẩu hiện tại</td>
            				<td>
            					<input type="password" maxlength="16" id="oldpassword" name="oldpassword" style="background-image: url(style/images/img/0CQnd2Jos49xUAAAAASUVORK5CYII=quot); background-repeat: no-repeat; background-attachment: scroll; background-position: right center;">
            					<span id="updatePwd_beError" style="display:none;" class="error">
            				</span></td>            				
            			</tr>
            			<tr>
            				<td class="label">mật khẩu mới</td>
            				<td><input type="password" maxlength="16" id="newpassword" name="newpassword" style="background-image: url(style/images/img/a6y3y0Wx5kbFHvGuXzkgf0xhKnPzA4UTyaTB8Ph8AvcHi3fnsrZ7Wore02YViqVOrRXXPhfqP8j6MYlawoAAAAASUVORK5CYII=quot); background-repeat: no-repeat; background-attachment: scroll; background-position: right center;"></td>
            			</tr>
            			<tr>
            				<td class="label">Xác nhận mật khẩu</td>
            				<td><input type="password" maxlength="16" id="comfirmpassword" name="comfirmpassword" style="background-image: url(style/images/img/a6y3y0Wx5kbFHvGuXzkgf0xhKnPzA4UTyaTB8Ph8AvcHi3fnsrZ7Wore02YViqVOrRXXPhfqP8j6MYlawoAAAAASUVORK5CYII=quot); background-repeat: no-repeat; background-attachment: scroll; background-position: right center;"></td>
            			</tr>
            			<tr>
            				<td>&nbsp;</td>
            				<td><input type="submit" value="Lưu"  id="savePassword"></td>
            			</tr>
            		</tbody></table>
	            </dd>
	        </dl>
	    </div>
<#include "../common/alert.ftl"/>
<!------------------------------------- lightbox ----------------------------------------->

<!------------------------------------- end -----------------------------------------> 
<script src="/home/style/js/setting.js"></script>
<#include "../common/footer.ftl"/>

<script src="/home/style/js/core.min.js" type="text/javascript"></script>
<script src="/home/style/js/popup.min.js" ></script>

<script type="text/javascript">
	document.getElementById("savePassword").onclick = function(){
      	
      	var oldPwd = $("#oldpassword").val();
      	var newPwd = $("#newpassword").val();
    	var confirmPwd = $("#comfirmpassword").val();
    	$.ajax({
				url:'/home/system/update_password',
				dataType:'json',
				type:'post',
				data:{oldPwd:oldPwd,newPwd:newPwd,confirmPwd:confirmPwd},
				success:function(data){
					if(data.code == 0){
						$("#successMsg").html("Mật khẩu được sửa đổi thành công ");
						$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
						setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
					}else{
    					$("#errorMsg").html("Sửa đổi mật khẩu không thành công，"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
					}
				}
			});
    	
    }
        

</script>

<div id="cboxOverlay" style="display: none;"></div><div id="colorbox" class="" role="dialog" tabindex="-1" style="display: none;"><div id="cboxWrapper"><div><div id="cboxTopLeft" style="float: left;"></div><div id="cboxTopCenter" style="float: left;"></div><div id="cboxTopRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxMiddleLeft" style="float: left;"></div><div id="cboxContent" style="float: left;"><div id="cboxTitle" style="float: left;"></div><div id="cboxCurrent" style="float: left;"></div><button type="button" id="cboxPrevious"></button><button type="button" id="cboxNext"></button><button id="cboxSlideshow"></button><div id="cboxLoadingOverlay" style="float: left;"></div><div id="cboxLoadingGraphic" style="float: left;"></div></div><div id="cboxMiddleRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxBottomLeft" style="float: left;"></div><div id="cboxBottomCenter" style="float: left;"></div><div id="cboxBottomRight" style="float: left;"></div></div></div><div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div></div></body></html>