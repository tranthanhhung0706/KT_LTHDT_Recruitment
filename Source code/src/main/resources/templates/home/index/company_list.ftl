<!--Danh sách công ty-->
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<script id="allmobilize" charset="utf-8" src="/home/style/js/allmobilize.min.js"></script>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate" media="handheld"  />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Web tuyển dụng</title>



<script type="text/javascript">

</script>
<link href="THUCTAPTOTNGHIEP_N19DCCN028" rel="Shortcut Icon">
<link rel="stylesheet" type="text/css" href="/home/style/css/style.css"/>
<link rel="stylesheet" type="text/css" href="/home/style/css/external.min.css"/>
<link rel="stylesheet" type="text/css" href="/home/style/css/popup.css"/>
<script src="/home/style/js/jquery.1.10.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/home/style/js/jquery.lib.min.js"></script>
<script type="text/javascript" src="/home/style/js/additional-methods.js"></script>
<!--[if lte IE 8]>
    <script type="text/javascript" src="/home/style/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">

</script> 
<script type="text/javascript" src="/home/style/js/conv.js"></script>
</head>
<body>
<div id="body">
	<#include "../common/top_menu.ftl"/>
    <div id="container">
        
        <div class="clearfix">
            <div class="content_l">
            	<form id="companyListForm" name="companyListForm" method="get" action="h/c/companylist.html">
	                <input type="hidden" id="city" name="city" value="Toàn quốc" />
	                <input type="hidden" id="fs" name="fs" value="" />
	                <input type="hidden" id="ifs" name="ifs" value="" />
	                <input type="hidden" id="ol" name="ol" value="" />
	                <dl class="hc_tag">
	                	<#if SearchValue??>
	                		 <dd><font size="3">Kết quả tìm kiếm theo: <font color="red">${SearchValue!""}</font> </font></dd>
	                	<#else>
	                		 <dd></dd>
	                	</#if>
	                </dl>
                	<ul class="hc_list reset">
                	
                	<#if CompanyList?size gt 0>
				 		<#list CompanyList as company>
				 			<#if company_index % 3 == 0>
	                        	<li style="clear:both;">
	                        <#else>
	                        	<li>
	                        </#if>	
			                        <a href="/home/company/detail?id=${company.id!""}">
			                        	<h3 title="${company.name}">${company.name}</h3>
			                        	<div class="comLogo">
				                        	<img src="/photo/view?filename=${company.photo}" width="190" height="190" alt="${company.name}" />
				                        	<ul>
				                        		<li>Địa điểm: ${company.locale!""}</li>
				                        		<li>Lĩnh vực: ${company.territory!""}</li>
				                        		<li>Giai đoạn tài trợ: ${company.finance!""}</li>
				                        	</ul>
			                        	</div>
			                        </a>
			                        <#assign count = 0>  <!--Đếm vị trí công việc-->
			                        <font color="red">Các vị trí công việc mà công ty đã đăng:</font>
		                       		<#if PositionList?size gt 0>
			 							<#list PositionList as position>
			 								<#if position.company.id == company.id>
			 									<#assign count = count+1>
			 									<a href="/home/position/detail?id=${position.id!""}" target="_blank">${count}.${position.name!""}</a>
			 								</#if>
			 							</#list>
			 						</#if>
			                        <ul class="reset ctags">
			                        	<#if company.tags??>
	                        				<#list company.tags?split(",") as tag>
									         	<li>${tag!""}</li>
									   		</#list>
			                        	</#if>
		                            </ul>
			                    </li>
		                    
	                     </#list>
	                 </#if>
	                 
	                 
	                </ul>
	                
	                
		                
       				<div class="Pagination">
       					<a title="Trang đầu" href="/home/index/company_list?page=1&rows=9&search_value=${SearchValue!""}">Trang đầu</a>
       					<#if currentPage == 1>
       					 	<a title="Không có trang trước"  href="javascript:void(0);">Không có trang trước</a>
 						<#else>
 							<a title="Trang trước"  href="/home/index/company_list?page=${currentPage-1}&rows=9&search_value=${SearchValue!""}">Trang trước</a>
 						</#if>
 
 						<#list 1..totalPage as i>
 							<#if i == currentPage-2>
 								<a title="${i}" href="/home/index/company_list?page=${i}&rows=9&search_value=${SearchValue!""}">${i}</a>
 							</#if>    
						  	<#if i == currentPage-1>
 								<a title="${i}" href="/home/index/company_list?page=${i}&rows=9&search_value=${SearchValue!""}">${i}</a>
 							</#if>   
 							<#if i == currentPage>
 								<a title="${i}" href="/home/index/company_list?page=${i}&rows=9&search_value=${SearchValue!""}" class="current">${i}</a>
 							</#if>   
 							<#if i == currentPage+1>
 								<a title="${i}" href="/home/index/company_list?page=${i}&rows=9&search_value=${SearchValue!""}">${i}</a>
 							</#if>    
						  	<#if i == currentPage+2>
 								<a title="${i}" href="/home/index/company_list?page=${i}&rows=9&search_value=${SearchValue!""}">${i}</a>
 							</#if>   
						</#list>
       					
       					
       					
       					<#if currentPage == totalPage>
       					 	<a title="Không có trang sau"  href="javascript:void(0);">Không có trang sau</a>
 						<#else>
 							<a title="Trang sau"  href="/home/index/company_list?page=${currentPage+1}&rows=9&search_value=${SearchValue!""}">Trang sau</a>
 						</#if>
 						
       					<a title="Trang cuối" href="/home/index/company_list?page=${totalPage}&rows=9&search_value=${SearchValue!""}">Trang cuối</a>
       				
       				</div>
       				
       				
       				
       				
       				
       				
            	</form>
            </div>	
            <div class="content_r">
            	<div class="subscribe_side">
	                    <#--  <div class="subpos"><span>Gửi</span> hồ sơ</div>
	                    <div class="c7">Sẽ cung cấp cho bạn hàng ngàn công việc bạn quan tâm
	                    </div>  -->
	                    <#--  <div class="count">Đã có<em>${resumeTotal!"0"}</em>người nộp hồ sơ
	                    </div>  -->
	            	
	            </div> 
                <div class="greybg qrcode mt20">
                	<img src="https://media.monster.com.vn/career-advice/wp-content/uploads/2022/03/interview-question-and-answer-for-hr-recruiter.jpg" width="242" height="242" alt="Mã QR WeChat" />
                    <#--  <span class="c7">Dễ dàng tìm kiếm công việc</span>  -->
                </div>
               	<#--  <a href="javascript:void(0);"  class="eventAd">
               		<img src="" width="280" height="135" />
               	</a>  -->
               	<a href="javascript:void(0);" class="eventAd">
               		<#--  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTI8puGOJefwN2x3_e5N4oOOuqK2f9920tSwg&usqp=CAU" width="280" height="135" />  -->
               	</a>
            </div>
       	</div>
   	
   	<input type="hidden" value="" name="userid" id="userid" />
      
<script type="text/javascript" src="style/js/company_list.min.js"></script>
<script>

</script>       	
<#include "../common/footer.ftl"/>	

<script type="text/javascript" src="/home/style/js/core.min.js"></script>
<script type="text/javascript" src="/home/style/js/popup.min.js"></script>

<!--  -->

</body>
</html>
