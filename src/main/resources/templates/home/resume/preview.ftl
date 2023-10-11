
<!DOCTYPE HTML>
<html><head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Web tuyển dụng </title>

<meta name="description" content="">
<meta name="keywords" content="">



</script><script type="text/javascript">

</script>
<link href="THUCTAPTOTNGHIEP_N19DCCN028" rel="Shortcut Icon">
<link href="/home/style/css/style.css" type="text/css" rel="stylesheet">
<link href="/home/style/css/colorbox.min.css" type="text/css" rel="stylesheet">
<link href="/home/style/css/popup.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="/home/style/js/jquery.1.10.1.min.js"></script>

<script src="/home/style/js/jquery.colorbox-min.js" type="text/javascript"></script>
<script>
$(function(){
	 $("body").on("click","a.btn_s",function(){
		$.colorbox.close();
		parent.jQuery.colorbox.close();
	});
	$(".inline").colorbox({
		inline:true
	});
});
</script>
<script src="/home/style/js/ajaxCross.json" charset="UTF-8"></script></head>

<body>
  	<div id="previewWrapper">
        <div class="preview_header">
            <h1 title="Thông tin hồ sơ">Thông tin hồ sơ</h1>
                        	
        </div><!--end .preview_header-->

        <div class="preview_content">
            <div class="profile_box" id="basicInfo">
                <h2>Thông tin cơ bản</h2>
                <div class="basicShow">
                   <span>
                   	<#if currentUser??>
	                 	  Tên: ${currentUser.username!""} | Giới tính: <#if currentUser.sex == 0>LGBT<#elseif currentUser.sex == 1>nam<#else>nữ</#if> | Giáo dục: ${currentUser.degree!""} <br>	
	                  	 Kinh nghiệm làm việc: ${currentUser.workExperience!""}<br>	
	                  	 Điện thoại: ${currentUser.mobile!"Không có gì cho thời điểm hiện tại"}  |  Email: ${currentUser.email!"暂无"} <br>		  
	            		</span>

                   </#if>
                </div><!--end .basicShow-->
            </div><!--end #basicInfo-->
			
	            <div class="profile_box" id="expectJob">
	                <h2>Công việc mong đợi</h2>
	                <div class="expectShow">
	                	<#if ExpectWork??>
            					Thành phố hy vọng: ${ExpectWork.city!"Chưa cập nhật"}   |  Tính chất công việc: ${ExpectWork.type!"toàn thời gian"} </br>   Vị trí mong muốn: ${ExpectWork.position!"Chưa cập nhật"}  |  Lương mong muốn: ${ExpectWork.money!"Chưa cập nhật"}
						</#if>
	                </div><!--end .expectShow-->
	            </div><!--end #expectJob-->
						
	            <div class="profile_box" id="workExperience">
	                <h2>Kinh nghiệm làm việc</h2>
	                <div class="experienceShow">
	                  <ul class="wlist clearfix">
	    				<li class="clear">
	    					<#if WorkExperience??>
		    					<span class="c9"> Từ ${WorkExperience.startMonth!""}-${WorkExperience.startYear!""}  đến ${WorkExperience.endMonth!""}-${WorkExperience.endYear!""}</span>
		       					<div>
		       						<h3>Tiêu đề công việc: ${WorkExperience.position!""}</h3>
		       						<h4>Tên công ty: ${WorkExperience.name!""}</h4>
		       					</div>
	    					<#else>
	    						<span class="c9">&#160; Năm&#160; Tháng —&#160; Năm&#160; Tháng</span>
		       					<div>
		       						<h3>Tiêu đề công việc: </h3>
		       						<h4>Tên công ty:</h4>
		       					</div>
	    					</#if>
	       				</li>
                  	  </ul>
	                </div><!--end .experienceShow-->
	            </div><!--end #workExperience-->
			
	            <div class="profile_box" id="projectExperience">
	                <h2>Kinh nghiệm dự án</h2>
	                <div class="projectShow">
	                  <ul class="plist clearfix">
	                  	<li class="noborder">
	    					<div class="projectList">
	    						<#if ProjectExperience??>
	    							<div class="f16 mb10">Tên dự án: ${ProjectExperience.name!""} | Vị trí: ${ProjectExperience.position!""} | <span class="c9"> ${ProjectExperience.startYear!""}-${ProjectExperience.startMonth!""} -- ${ProjectExperience.endYear!""}-${ProjectExperience.endMonth!""}</span>
		        					</div>
		        					<div class="dl1">
		        						Mô tả dự án: ${ProjectExperience.content!""}
		        					</div>
	    						<#else>
	    							<div class="f16 mb10">Tên dự án: | Vị trí:  | <span class="c9">&#160; Năm&#160; tháng —— &#160; Năm &#160; tháng</span>
		        					</div>
		        					<div class="dl1">
		        						mô tả dự án:
		        					</div>
	    						</#if>
	        				</div>
        				</li>
                      </ul>
	                </div><!--end .projectShow-->
	            </div><!--end #projectExperience-->
						
	            <div class="profile_box" id="educationalBackground">
	                <h2>Nền giáo dục</h2>
	                <div class="educationalShow">
	                  <ul class="elist clearfix">
        				<li class="clear">
        					<#if EducationBackground??>
        						<span class="c9">&#160; Năm ${EducationBackground.startYear!""} — Năm ${EducationBackground.endYear!""} </span>
						 		<div>
		    						<h3>Tên trường: ${EducationBackground.school_name!""}</h3>
		    						<h4>Chuyên ngành: ${EducationBackground.major!""}  Trình độ: ${EducationBackground.studyRecord!""}</h4>
					     		</div>
        					<#else>
        						<span class="c9"></span>
						 		<div>
		    						<h3>Tên trường: </h3>
		    						<h4> · </h4>
					     		</div>
        					</#if>
        				</li>
                     </ul>
	                </div><!--end .educationalShow-->
	            </div><!--end #educationalBackground-->
	            
	            <div class="profile_box" id="selfDescription">
	                <h2>Tự mô tả</h2>
	                <div class="descriptionShow">
	            		${currentUser.content!""}
	                </div><!--end .descriptionShow-->
	            </div><!--end #selfDescription-->
						
	            <div class="profile_box" id="worksShow">
	                <h2>Trưng bày</h2>
										<div class="workShow">
												<ul class="slist clearfix">
													<li class="noborder">
															<div class="workList c7">
																<#if WorkShow??>
																<div class="f16">URL làm việc:<a target="_blank" href="${WorkShow.url!""}">${WorkShow.url!""}</a></div>
																	<p>Mô tả công việc: ${WorkShow.content!""}</p>
																<#else>
																	<div class="f16">URL làm việc:</div>
																	<p>Mô tả công việc: </p>
																</#if>
															</div>
													</li>
												</ul>
										</div><!--end .workShow-->
	            </div><!--end #worksShow-->

							 <div class="profile_box" id="worksShow">
	                <h2>Thông tin CV</h2>
										<div class="workShow">
												<ul class="slist clearfix">
													<li class="noborder">
															<div class="workList c7">
																<#if WorkShow??>
																<div class="f16">Link CV: <a target="_blank" href="/view-pdf?filename=${WorkShow.fileCv}">Tải CV của ứng viên</a> </div>
																<#else>
																	<div class="f16">Link CV: Chưa có thông tin CV</div>
																</#if>
															</div>
													</li>
												</ul>
										</div><!--end .workShow-->
	            </div><!--end #worksShow-->


			      </div><!--end .preview_content-->
  	</div><!--end #previewWrapper-->

<!-------------------------------------lightbox ----------------------------------------->

<!------------------------------------- end ----------------------------------------->  




<div id="cboxOverlay" style="display: none;"></div><div id="colorbox" class="" role="dialog" tabindex="-1" style="display: none;"><div id="cboxWrapper"><div><div id="cboxTopLeft" style="float: left;"></div><div id="cboxTopCenter" style="float: left;"></div><div id="cboxTopRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxMiddleLeft" style="float: left;"></div><div id="cboxContent" style="float: left;"><div id="cboxTitle" style="float: left;"></div><div id="cboxCurrent" style="float: left;"></div><button type="button" id="cboxPrevious"></button><button type="button" id="cboxNext"></button><button id="cboxSlideshow"></button><div id="cboxLoadingOverlay" style="float: left;"></div><div id="cboxLoadingGraphic" style="float: left;"></div></div><div id="cboxMiddleRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxBottomLeft" style="float: left;"></div><div id="cboxBottomCenter" style="float: left;"></div><div id="cboxBottomRight" style="float: left;"></div></div></div><div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div></div></body></html>