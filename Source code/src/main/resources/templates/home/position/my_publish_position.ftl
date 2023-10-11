
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb">

<head>
	<script async="" src="/home/style/js/analytics.js"></script>
	
	<script src="/home/style/js/allmobilize.min.js" charset="utf-8" id="allmobilize"></script>
	<style type="text/css"></style>
	<meta content="no-siteapp" http-equiv="Cache-Control">
	<link media="handheld" rel="alternate">
	
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<title>Dịch vụ sau khi nhận của tôi-Web tuyển dụng Internet chuyên nghiệp nhất</title>
	
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
	<script type="text/javascript" src="/home/style/js/ajaxfileupload.js"></script>
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
					<dd>
						<a href="/home/resume/my_receive_resume?resumeState=wait">Được xử lý hồ sơ</a>
					</dd>
					<dd>
						<a href="/home/resume/my_receive_resume?resumeState=effective">Hồ sơ phỏng vấn</a>
					</dd>
					<dd class="btm">
						<a href="/home/resume/my_receive_resume?resumeState=out">Hồ sơ không phù hợp</a>
					</dd>
				</dl>
				<dl class="company_center_aside">
					<dt>Vị trí tôi đã đăng</dt>
					<#if positionState??>
						<#if positionState=="effective">
							<dd class="current">
								<a href="/home/position/my_publish_position?positionState=effective">Vị trí đang trực tuyến</a>
							</dd>
							<#else>
								<dd>
									<a href="/home/position/my_publish_position?positionState=effective">Vị trí đang trực tuyến</a>
								</dd>
						</#if>
						<#if positionState=="wait">
							<dd class="current">
								<a href="/home/position/my_publish_position?positionState=wait">Đang xem xét</a>
							</dd>
							<#else>
								<dd>
									<a href="/home/position/my_publish_position?positionState=wait">Đang xem xét</a>
								</dd>
						</#if>
						<#if positionState=="out">
							<dd class="current">
								<a href="/home/position/my_publish_position?positionState=out">Có vị trí ngoại tuyến</a>
							</dd>
							<#else>
								<dd>
									<a href="/home/position/my_publish_position?positionState=out">Có vị trí ngoại tuyến</a>
								</dd>
						</#if>
						<#else>
							<dd class="current">
								<a href="/home/position/my_publish_position?positionState=effective">Vị trí đang trực tuyến</a>
							</dd>
							<dd>
								<a href="/home/position/my_publish_position?positionState=wait">Đang xem xét</a>
							</dd>
							<dd>
								<a href="/home/position/my_publish_position?positionState=out">Có vị trí ngoại tuyến</a>
							</dd>
					</#if>
				</dl>
			</div><!-- end .sidebar -->
			<div class="content">
				<dl class="company_center_content">
					<dt>
						<h1>
							<em></em>
							<#if positionState??>
								<#if positionState=="effective">
									Vị trí đang trực tuyến
								</#if>
								<#if positionState=="wait">
									Đang xem xét
								</#if>
								<#if positionState=="out">
									Có vị trí ngoại tuyến
								</#if>
								<#else>
									Vị trí đang trực tuyến
							</#if>
							<span>（Gồm: <i style="color:#fff;font-style:normal" id="positionNumber">
									${positionTotal!""}
								</i> công việc）</span>
						</h1>
					</dt>
					<dd>
						<form id="searchForm">
							<input type="hidden" value="Publish" name="type">
							<ul class="reset my_jobs">
								<#if PositionList?size gt 0>
									<#list PositionList as position>
										<li data-id="149594">
											<h3>
												<a target="_blank" title="${position.name!""}" href="">
													${position.name!""}
												</a>
												<span>[${position.city!""}]
												</span>
											</h3>
											<span class="receivedResumeNo">Sơ yếu lý lịch áp dụng cho vị trí này (${position.number!""}）</span>
											<div>
												${position.workType!""}/ ${position.minMoney!""} triệu-${position.maxMoney!""} triệu / ${position.workExperience!""} / ${position.degree!""}
											</div>
											<div class="c9">Thời gian phát hành： ${position.createTime!""}
											</div>
											<div class="links">
												<a href="/home/index/publish_position?id=${position.id!""}">Chỉnh sửa</a>
												<#if positionState??>
													<#if positionState=="out">
														<a href="javascript:void(0)" onclick="changeStateToWait('${position.id!""}')">Trực tuyến</a>
														<#else>
															<a href="javascript:void(0)" onclick="changeStateToOut('${position.id!""}')">Ngoại tuyến</a>
													</#if>
												</#if>
												<a href="javascript:void(0)" onclick="deletePosition('${position.id!""}')">xóa bỏ</a>
											</div>
										</li>
									</#list>
								</#if>
							</ul>
						</form>
					</dd>
				</dl>
			</div><!-- end .content -->
			<#include "../common/alert.ftl" />
			<script src="/home/style/js/job_list.min.js" type="text/javascript"></script>
			<#include "../common/footer.ftl" />
			<script src="/home/style/js/core.min.js" type="text/javascript"></script>
			<script src="/home/style/js/popup.min.js" type="text/javascript"></script>
			<script src="/home/style/js/tongji.js" type="text/javascript"></script>
			<!--  -->
			<script src="/home/style/js/analytics01.js" type="text/javascript"></script>
			<script type="text/javascript" src="/home/style/js/h.js"></script>
			<script type="text/javascript">
			//Xóa vị trí
			function deletePosition(i) {
				if (confirm("Bạn có OK muốn xóa nó?Nếu bị xóa, Hồ sơ cho công việc cũng sẽ bị xóa")) {
					$.ajax({
						url: '/home/position/delete',
						dataType: 'json',
						type: 'post',
						data: {id:i},
						success: function(data) {
							if (data.code == 0) {
								$("#successMsg").html("Công việc được xóa thành công! ");
								$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
								setTimeout(function() {
									window.location.reload(); //Trang làm mới
								}, 3000);
							} else {
								$("#errorMsg").html("Xóa vị trí thất bại，" + data.msg);
								$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
							}
						}
					});
				}
			}
			//Thay đổi trạng thái vị trí thành ngoại tuyến
			function changeStateToOut(i) {
				$.ajax({
					url: '/home/position/changeStateToOut',
					dataType: 'json',
					type: 'post',
					data: {id:i},
					success: function(data) {
						if (data.code == 0) {
							$("#successMsg").html("Việc ngoại tuyến là thành công!");
							$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
							setTimeout(function() {
								window.location.reload(); //Trang làm mới
							}, 3000);
						} else {
							$("#errorMsg").html("Thất bại ngoại tuyến，" + data.msg + "!!!");
							$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
						}
					}
				});
			}
			//Thay đổi vị trí của vị trí ngoại tuyến thành trực tuyến
			function changeStateToWait(i) {
				$.ajax({
					url: '/home/position/changeStateToWait',
					dataType: 'json',
					type: 'post',
					data: {id:i},
					success: function(data) {
						if (data.code == 0) {
							$("#successMsg").html("Thành công đã lên mạng!Nhưng đợi cho quản trị viên xem xét nó!");
							$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
							setTimeout(function() {
								window.location.reload(); //Trang làm mới
							}, 3000);
						} else {
							$("#errorMsg").html("Không lên mạng," + data.msg);
							$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
						}
					}
				});
			}
			</script>
			<div id="cboxOverlay" style="display: none;"></div>
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
		</div>
	</div>
</body>

</html>