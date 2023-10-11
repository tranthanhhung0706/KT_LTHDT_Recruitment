
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb">

<head>
</script>
	
	<script src="/home/style/js/allmobilize.min.js" charset="utf-8" id="allmobilize"></script>
	<style type="text/css"></style>
	<meta content="no-siteapp" http-equiv="Cache-Control">
	<link media="handheld" rel="alternate">

	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<title>Web tuyển dụng internet chuyên nghiệp nhất</title>
	
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
	<script src="/home/common/confirm_null.js"></script>
	<script type="text/javascript">
	
	</script>
	<script src="/home/style/js/conv.js" type="text/javascript"></script>
	<script src="/home/style/js/ajaxCross.json" charset="UTF-8"></script>
</head>

<body>
	
	
	<div id="body">
		<#include "../common/top_menu.ftl" />
		<div id="container">
			<div class="sidebar">
				<a class="btn_create" href="/home/index/publish_position">Công việc mới</a>
				<dl class="company_center_aside">
					<dt>Hồ sơ tôi nhận được</dt>
					<#if resumeState??>
						<#if resumeState=='wait'>
							<dd class="current">
								<a href="/home/resume/my_receive_resume?resumeState=wait">Được xử lý hồ sơ</a>
							</dd>
							<#else>
								<dd>
									<a href="/home/resume/my_receive_resume?resumeState=wait">Được xử lý hồ sơ</a>
								</dd>
						</#if>
						<#if resumeState=='effective'>
							<dd class="current">
								<a href="/home/resume/my_receive_resume?resumeState=effective">Hồ sơ phỏng vấn </a>
							</dd>
							<#else>
								<dd>
									<a href="/home/resume/my_receive_resume?resumeState=effective">Hồ sơ phỏng vấn</a>
								</dd>
						</#if>
						<#if resumeState=='out'>
							<dd class="current">
								<a href="/home/resume/my_receive_resume?resumeState=out">Hồ sơ không phù hợp</a>
							</dd>
							<#else>
								<dd>
									<a href="/home/resume/my_receive_resume?resumeState=out">Hồ sơ không phù hợp</a>
								</dd>
						</#if>
						<#else>
							<dd class="current">
								<a href="/home/resume/my_receive_resume?resumeState=wait">Được xử lý hồ sơ</a>
							</dd>
							<dd>
								<a href="/home/resume/my_receive_resume?resumeState=effective">Hồ sơ phỏng vấn</a>
							</dd>
							<dd class="btm">
								<a href="/home/resume/my_receive_resume?resumeState=out">Hồ sơ không phù hợp</a>
							</dd>
					</#if>
				</dl>
				<dl class="company_center_aside">
					<dt>Công việc tôi đã đăng</dt>
					<dd>
						<a href="/home/position/my_publish_position?positionState=effective">Vị trí đang trực tuyến</a>
					</dd>
					<dd>
						<a href="/home/position/my_publish_position?positionState=wait">Đang xem xét</a>
					</dd>
					<dd>
						<a href="/home/position/my_publish_position?positionState=out">Có vị trí ngoại tuyến</a>
					</dd>
				</dl>
			</div><!-- end .sidebar -->
			<div class="content">
				<dl class="company_center_content">
					<dt>
						<h1>
							<em> </em>
							<#if resumeState??>
								<#if resumeState=='effective'>
									Hồ sơ phỏng vấn<span>（Gồm: ${resumeTotal!""} hồ sơ）</span>
								</#if>
								<#if resumeState=='wait'>
									Được xử lý hồ sơ <span>（Gồm: ${resumeTotal!""} hồ sơ）</span>
								</#if>
								<#if resumeState=='out'>
									Hồ sơ không phù hợp <span>（Gồm: ${resumeTotal!""} hồ sơ）</span>
								</#if>
								<#else>
									Được xử lý hồ sơ <span>（Gồm: ${resumeTotal!""} hồ sơ）</span>
							</#if>
						</h1>
					</dt>
					<dd>
						<form action="canInterviewResumes.html" method="get" id="filterForm">
							<ul class="reset resumeLists">
								<#if ResumeList?size gt 0>
									<#list ResumeList as resume>
										<li data-id="1686182" class="onlineResume">
											<div class="resumeShow">
												<img src="/photo/view?filename=${resume.user.headPic!""}">
												<div class="resumeIntro">
													<h3 class="unread">
														<a href="/home/resume/preview?user_id=${resume.user.id!""}" target="_blank">
															${resume.user.username!""} | Bảng tóm tắt
														</a>
													</h3>
													<span class="fr">Thời gian: ${resume.createTime!""}
													</span>
													<div>
														Tên: ${resume.user.username!""} / giới tính：${resume.user.sex!""} / Bằng cấp：${resume.user.degree!""} / kinh nghiệm：${resume.user.workExperience!""}
														<br>
														<#if WorkExperienceList?size gt 0>
															<#list WorkExperienceList as workExperience>
																<#if workExperience.user.id==resume.user.id>
																	${workExperience.position!""} · ${workExperience.name!""}
																</#if>
															</#list>
														</#if>
														|
														<#if EducationBackgroundList?size gt 0>
															<#list EducationBackgroundList as educationBackground>
																<#if educationBackground.user.id==resume.user.id>
																	${educationBackground.studyRecord!""} · ${educationBackground.school_name!""}
																</#if>
															</#list>
														</#if>
													</div>
													<div class="jdpublisher">
														<span>
															xin việc: <a title="${resume.position.name!""}" href="/home/position/detail?id=${resume.position.id!""}">
																${resume.position.name!""}
															</a>
														</span>
													</div>
												</div>
												<div class="links">
													<#if resumeState??>
														<#if resumeState !='effective'>
															<#--  <a class="resume_notice" href="javascript:void(0)" onclick="interview('${resume.id!""}');">Thông báo phỏng vấn</a>  -->
															<a class="resume_notice" href="javascript:void(0)" onclick="interview2('${resume.id!""}');"> phỏng vấn</a>
														</#if>
														<#if resumeState !='out'>
															<a class="resume_refuse" href="javascript:void(0)" onclick="unsuitable('${resume.id!""}');">Không phù hợp</a>
														</#if>
														<#if resumeState=='out'>
															<a class="resume_refuse" href="javascript:void(0)" onclick="deleteResume('${resume.id!""}');">xóa bỏ</a>
														</#if>
														<a class="resume_preview" target="_blank" href="/home/resume/preview?user_id=${resume.user.id!""}">Xem trước hồ sơ </a>
														<#else>
															<#--  <a class="resume_notice" href="javascript:void(0)" onclick="interview('${resume.id!""}');">Thông báo phỏng vấn </a>  -->
															<a class="resume_notice" href="javascript:void(0)" onclick="interview2('${resume.id!""}');">phỏng vấn </a>
															<a class="resume_refuse" href="javascript:void(0)" onclick="unsuitable('${resume.id!""}');">Không phù hợp</a>
															<a class="resume_preview" target="_blank" href="/home/resume/preview?user_id=${resume.user.id!""}">Xem trước hồ sơ</a>
													</#if>
												</div>
											</div>
											<div class="contactInfo">
												<span class="c9">Điện thoại:</span>
												${resume.user.mobile!""} &nbsp;&nbsp;&nbsp;
												<span class="c9">Email:</span>
												${resume.user.email!""}
											</div>
										</li>
									</#list>
								</#if>
							</ul><!-- end .resumeLists -->
						</form>
					</dd>
				</dl><!-- end .company_center_content -->
			</div><!-- end .content -->
			<#include "../common/alert.ftl" />
			<!------------------------------------- lightbox ----------------------------------------->
			<!------------------------------------- end ----------------------------------------->
			<script src="/home/style/js/jquery.ui.datetimepicker.min.js" type="text/javascript"></script>
			<script src="/home/style/js/received_resumes.min.js" type="text/javascript"></script>
			<script>
			</script>
			<#include "../common/footer.ftl" />
			<script src="/home/style/js/core.min.js" type="text/javascript"></script>
			<script src="/home/style/js/popup.min.js" type="text/javascript"></script>
			<!--  -->
			<script type="text/javascript">
					//Phỏng vấn thông báo
					function interview(i) {
						$.ajax({
							url: '/home/resume/interview',
							dataType: 'json',
							type: 'post',
							data: {id:i},
							success: function(data) {
								if (data.code == 0) {
									$("#successMsg").html("Cuộc phỏng vấn đã thành công!");
									$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
									setTimeout(function() {
										window.location.reload(); //Trang làm mới
									}, 3000);
								} else {
									$("#errorMsg").html("Thông báo của cuộc phỏng vấn không thành công" + data.msg + "!!!");
									$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
								}
							}
						});
					}
					//Đặt thành không phù hợp
					function unsuitable(i) {
						$.ajax({
							url: '/home/resume/unsuitable',
							dataType: 'json',
							type: 'post',
							data: {id:i},
							success: function(data) {
								if (data.code == 0) {
									$.messager.alert('Thông báo tin nhắn', 'Hồ sơ đã được đặt thành không phù hợp!', 'info');
									$('#edit-dialog').dialog('close');
									
									setTimeout(function() {
										window.location.reload(); 
									}, 3000);
								} else {
									$.messager.alert('Thông báo tin nhắn', data.msg, 'warning');
								}
							}
						});
					}
					//Xóa sơ yếu lý lịch
					function deleteResume(i) {
						if (confirm("Bạn có chắc bạn muốn xóa sơ yếu lý lịch không？")) {
							$.ajax({
								url: '/home/resume/delete',
								dataType: 'json',
								type: 'post',
								data: {id:i},
								success: function(data) {
								if (data.code == 0) {
									$.messager.alert('Thông báo tin nhắn', 'Hồ sơ được xóa thành công!', 'info');
									$('#edit-dialog').dialog('close');
									
									setTimeout(function() {
										window.location.reload(); 
									}, 3000);
								} else {
									$.messager.alert('Tiếp tục xóa không thành công，', data.msg, 'warning');
								}
							}
							});
						}
					}

					function interview2(i) {
						var currentDate = new Date();
						var defaultDatetime = currentDate.toISOString().slice(0, 16);
						console.log("Current date: " + defaultDatetime);
						$('#edit-dialog').dialog({
							closed: false,
							modal: true,
							title: "Thay đổi trạng thái vị trí",
							buttons: [{
								text: 'OK',
								iconCls: 'icon-ok',
								handler: change
							}, {
								text: 'Hủy bỏ',
								iconCls: 'icon-cancel',
								handler: function() {
									$('#edit-dialog').dialog('close');
								}
							}],
							onBeforeOpen: function() {
								$("#edit-id").val(i);
								$("#dateTimePicker").val(defaultDatetime);
							}
						});
						
					}
					function change() {
						var data = $("#edit-form").serialize();
						console.log('danh', data);
						$.ajax({
							url: '/home/resume/interview2',
							dataType: 'json',
							type: 'post',
							data: data,
							success: function(data) {
								if (data.code == 0) {
									$.messager.alert('Thông báo tin nhắn', 'Thay đổi thành công của công ty!', 'info');
									$('#edit-dialog').dialog('close');
									
									setTimeout(function() {
										window.location.reload(); 
									}, 3000);
								} else {
									$.messager.alert('Thông báo tin nhắn', data.msg, 'warning');
								}
							}
						});
					}
			</script>
			<#-- <div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
				<form id="edit-form" method="post">
					<input type="hidden" name="id" id="edit-id">
					<h2>Nhập ngày giờ phỏng vấn</h2>
					<input type="datetime-local" id="dateTimePicker" name="interview" pattern="\d{4}-\d{2}-\d{2}T\d{2}:\d{2}" placeholder="yyyy-mm-ddThh:mm" value="2023-07-17T08:38">
				</form> -->
		</div>
			<#-- <div style="display:none">
				<div class="popup user_popup" id="edit-dialog">
					<form id="edit-form" method="post">
						<input type="hidden" name="id" id="edit-id">
						<h2>Nhập ngày giờ phỏng vấn</h2>
						<input type="datetime-local" id="dateTimePicker" name="interview" pattern="\d{4}-\d{2}-\d{2}T\d{2}:\d{2}" placeholder="yyyy-mm-ddThh:mm" value="2023-07-17T08:38">
						<div class="user_bindBtn user_unbind">
							<button id="confirmSuccess" class="user_confirm click" type="submit">OK</button>
						</div>
					</form>
				</div>
		</div> -->
		
		<div id="cboxOverlay" style="display: none;">
			<div id="colorbox" class="" role="dialog" tabindex="-1" style="display: none;">
			<div id="cboxWrapper">
				<div>
					<div id="cboxTopLeft" style="float: left;"></div>
					<div id="cboxTopCenter" style="float: left;"></div>
					<div id="cboxTopRight" style="float: left;"></div>
				</div>
				<div style="clear: left;">
					<div id="cboxMiddleLeft" style="float: left;"></div>
					<div id="cboxContent" style="float: left;">
						<div id="cboxTitle" style="float: left;"></div>
						<div id="cboxCurrent" style="float: left;"></div><button type="button" id="cboxPrevious"></button><button type="button" id="cboxNext"></button><button id="cboxSlideshow"></button>
						<div id="cboxLoadingOverlay" style="float: left;"></div>
						<div id="cboxLoadingGraphic" style="float: left;"></div>
					</div>
					<div id="cboxMiddleRight" style="float: left;"></div>
				</div>
				<div style="clear: left;">
					<div id="cboxBottomLeft" style="float: left;"></div>
					<div id="cboxBottomCenter" style="float: left;"></div>
					<div id="cboxBottomRight" style="float: left;"></div>
				</div>
			</div>

			<div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div>
		</div>
		
		<div class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" id="ui-datepicker-div"></div>
	</div>
	
	
</body>

</html>
<#include "../common/confirm.ftl"/>