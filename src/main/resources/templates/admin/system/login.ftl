
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title> Đăng nhập hệ thống</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link rel="stylesheet" type="text/css" href="/admin/easyui/easyui/1.3.4/themes/default/easyui.css" />
  <link rel="stylesheet" type="text/css" href="/admin/easyui/css/wu.css" />
  <link rel="stylesheet" type="text/css" href="/admin/easyui/css/icon.css" />
  <link rel="stylesheet" media="screen" href="/admin/login/css/style.css">
  <link rel="stylesheet" type="text/css" href="/admin/login/css/reset.css">
<body>

<div id="particles-js">
		<div class="login" style="display: block;">
			<div class="login-top">
				Đăng nhập hệ thống quản lý
			</div>
			<form id="adminLoginForm">
				<div class="login-center clearfix">
					<div class="login-center-img"><img src="/admin/login/images/name.png"></div>
					<div class="login-center-input">
						<input type="text" name="adminName" class="required" id="adminName" value="" placeholder="Xin hãy điền tên đăng nhập" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;Xin hãy điền tên đăng nhập&#39;" tips="Tên ngươi dung không được để trông">
						<div class="login-center-input-text">tên tài khoản</div>
					</div>
				</div>
				<div class="login-center clearfix">
					<div class="login-center-img"><img src="/admin/login/images/password.png"></div>
					<div class="login-center-input">
						<input type="password" name="password" class="required" id="password" value="" placeholder="Vui lòng nhập mật khẩu của bạn" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;Vui lòng nhập mật khẩu của bạn&#39;" tips="Mật khẩu không thể trống">
						<div class="login-center-input-text">mật khẩu</div>
					</div>
				</div>
				<div class="login-center clearfix">
					<#--  <div class="login-center-img"><img src="capcha-admin"></div>  -->
					<div class="login-center-input">
						<input style="width:50%;" type="text" class="required" name="cpacha" id="cpacha" value="" placeholder="Nhập mã xác nhận" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;Nhập mã xác nhận&#39;" tips="Mã xác minh phải được điền">
						<div class="login-center-input-text">Mã xác nhận</div>
						<img id="cpacha-img" title="Nhấp để chuyển đổi mã xác minh" style="cursor:pointer;" src="/common/cpacha/generate_cpacha?vl=4&fs=21&w=98&h=33&method=admin_login" width="110px" height="30px" onclick="changeCpacha()">
					</div>
				</div>
			</form>
			<div class="login-button">
				Đăng nhập
			</div>
		</div>
		<div class="sk-rotating-plane"></div>
<canvas class="particles-js-canvas-el" width="1147" height="952" style="width: 100%; height: 100%;"></canvas></div>

<!-- scripts -->
<script src="/admin/login/js/particles.min.js"></script>
<script src="/admin/login/js/app.js"></script>
<script src="/admin/login/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/admin/easyui/easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/admin/easyui/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script src="/admin/common/confirm_null.js"></script>
<script type="text/javascript">
	function hasClass(elem, cls) {
	  cls = cls || '';
	  if (cls.replace(/\s/g, '').length == 0) return false; //Khi CLS không có tham số, hãy trả về sai
	  return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
	}
	 
	function addClass(ele, cls) {
	  if (!hasClass(ele, cls)) {
	    ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
	  }
	}
	 
	function removeClass(ele, cls) {
	  if (hasClass(ele, cls)) {
	    var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
	    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
	      newClass = newClass.replace(' ' + cls + ' ', ' ');
	    }
	    ele.className = newClass.replace(/^\s+|\s+$/g, '');
	  }
	}
	
	function changeCpacha(){
		$("#cpacha-img").attr("src",'/common/cpacha/generate_cpacha??vl=4&fs=21&w=98&h=33&method=admin_login&t=' + new Date().getTime());
	}
		document.querySelector(".login-button").onclick = function(){
				//Một biểu mẫu thống nhất không phải là xác minh trống
				if(!checkForm('adminLoginForm'))
				{
					return;
				}
				addClass(document.querySelector(".login"), "active")
				addClass(document.querySelector(".sk-rotating-plane"), "active")
				document.querySelector(".login").style.display = "none"
				
				var data = $('#adminLoginForm').serialize();
				
				$.ajax({
					url:'/admin/system/login',
					data:data,
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.code == 0){
							window.location.href = '/admin/system/index';
						}else{
							removeClass(document.querySelector(".login"), "active");
							removeClass(document.querySelector(".sk-rotating-plane"), "active");
							document.querySelector(".login").style.display = "block";
							$.messager.alert("Thông báo tin nhắn",data.msg, "error");
							changeCpacha();
						}
					},
					error:function(data){
						$.messager.alert("Thông báo tin nhắn", 'Lỗi mạng!', "error");
					}
				});
				
		}
</script>
</body></html>