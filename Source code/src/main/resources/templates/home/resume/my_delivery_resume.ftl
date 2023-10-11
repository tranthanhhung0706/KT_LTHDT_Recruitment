
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb"><head>
</script><script src="/home/style/js/allmobilize.min.js" charset="utf-8" id="allmobilize"></script><style type="text/css"></style>
<meta content="no-siteapp" http-equiv="Cache-Control">
<link  media="handheld" rel="alternate">

<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Web tuyển dụng</title>

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

<script type="text/javascript">

</script> 
<script src="/home/style/js/conv.js" type="text/javascript"></script>
<script src="/home/style/js/ajaxCross.json" charset="UTF-8"></script></head>
<body>
<div id="body">
	<#include "../common/top_menu.ftl"/>
    <div id="container">
        	  	
        <div class="clearfix">
            <div class="content_l">
            	<dl class="c_delivery">
                    <dt>
                        <h1><em></em>Trạng thái sơ yếu lý lịch</h1>
                        <a class="d_refresh" href="javascript:void(0);" onclick="refresh();">Reload</a>
                    </dt>
                    <dd>
                    	<div class="delivery_tabs">
                    		<ul class="reset">
                    			
                    			<#if resumeState??>
                    				<li>
                    					<a href="/home/resume/my_delivery_resume">tất cả</a>
	                    			</li>
                    				<#if resumeState == "wait">
                    					<li class="current">
	                    					<a href="/home/resume/my_delivery_resume?resumeState=wait">Gửi thành công</a>
	                    				</li>
	                    			<#else>
	                    				<li>
	                    					<a href="/home/resume/my_delivery_resume?resumeState=wait">Gửi thành công</a>
                    					</li>
                    				</#if>
                    				<#if resumeState == "effective">
                    					<li class="current">
	                    					<a href="/home/resume/my_delivery_resume?resumeState=effective">Phỏng vấn</a>
	                    				</li>
	                    			<#else>
	                    				<li>
	                    					<a href="/home/resume/my_delivery_resume?resumeState=effective">Phỏng vấn</a>
                    					</li>
                    				</#if>
                    				<#if resumeState == "out">
                    					<li class="current">
	                    					<a href="/home/resume/my_delivery_resume?resumeState=out">Không phù hợp</a>
	                    				</li>
	                    			<#else>
	                    				<li>
	                    					<a href="/home/resume/my_delivery_resume?resumeState=out">Không phù hợp</a>
                    					</li>
                    				</#if>
                    			<#else>
                    				<li class="current">
                    					<a href="/home/resume/my_delivery_resume">tất cả</a>
	                    			</li>
	                    			<li>
	                    				<a href="/home/resume/my_delivery_resume?resumeState=wait">Gửi thành công</a>
	                    			</li>
	                    			<li>
	                    				<a href="/home/resume/my_delivery_resume?resumeState=effective">Phỏng vấn</a>
	                    			</li>
	                    			<li class="last">
	                    				<a href="/home/resume/my_delivery_resume?resumeState=out">Không phù hợp</a>
	                    			</li>
                    			</#if>
                    		
                    			
                    		</ul>
                    	</div>
                        <form id="deliveryForm">
                            <ul class="reset my_delivery">
                            	<#if ResumeList?size gt 0>
									 <#list ResumeList as resume>
									 
		                             	<li>
		                             		<div class="d_item">
		 	                                    <h2 title="${resume.position.name!""}">
																							Công việc: 
			                                        <a href="/home/position/detail?id=${resume.position.id!""}">
			                                        	<em>${resume.position.name!""}</em> 
			                                        	<span>（${resume.position.minMoney!""} triệu-${resume.position.maxMoney!""} triệu）</span>
			                                    	</a>
			                                    </h2>
		                            			<div class="clear">Tên Công ty: </div>
			                                   	<a title="${resume.company.name!""}" class="d_jobname" href="/home/company/detail?id=${resume.company.id!""}">
			                                   		${resume.company.name!""}<span>[${resume.position.city!""}]</span> 
			                                    </a>

			                                    <span class="d_time">Ngày Nộp: ${resume.createTime!""}</span>
																					</br>
																					<div class="d_time">
																							<span>Ngày thông báo: ${resume.updateTime!""}</span>
																					</div>
																					<#if resume.interview?? && resume.state == "effective" >
																						
																						<div class="d_resume" style ="font-size: 17px;">
																						Lịch phỏng vấn: 
																						<span> ${resume.interview!""} </span>
																						
																						</div>
																						
																						
																						<div class="d_resume" style ="font-size: 20px;">
			                                    	Mô tả:
			                                    	<p>Chúc mừng! Bạn đã được nhận phỏng vấn!</p>
			                                    </div>
																					<#else>

																					</#if>
																					
																				

			                                  
			                             
		                            	</li>
		                            	
		                              </#list>
		                           </#if>
                            	
                            </ul>
                        	<input type="hidden" value="-1" name="tag">
                        	<input type="hidden" value="" name="r">
                        </form>
                    </dd>
                </dl>
            </div>	
  
       	</div>
      <input type="hidden" id="userid" name="userid" value="314873">

<script src="/home/style/js/delivery.js"></script>
<#include "../common/footer.ftl"/>	
<script src="/home/style/js/core.min.js" type="text/javascript"></script>
<script src="/home/style/js/popup.min.js" type="text/javascript"></script>
<script type="text/javascript">
 	function refresh(){
 		window.location.reload();
 	}
 	
 	

</script>

<div id="cboxOverlay" style="display: none;"></div><div id="colorbox" class="" role="dialog" tabindex="-1" style="display: none;"><div id="cboxWrapper"><div><div id="cboxTopLeft" style="float: left;"></div><div id="cboxTopCenter" style="float: left;"></div><div id="cboxTopRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxMiddleLeft" style="float: left;"></div><div id="cboxContent" style="float: left;"><div id="cboxTitle" style="float: left;"></div><div id="cboxCurrent" style="float: left;"></div><button type="button" id="cboxPrevious"></button><button type="button" id="cboxNext"></button><button id="cboxSlideshow"></button><div id="cboxLoadingOverlay" style="float: left;"></div><div id="cboxLoadingGraphic" style="float: left;"></div></div><div id="cboxMiddleRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxBottomLeft" style="float: left;"></div><div id="cboxBottomCenter" style="float: left;"></div><div id="cboxBottomRight" style="float: left;"></div></div></div><div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div></div></body></html>