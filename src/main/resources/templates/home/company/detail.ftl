<!--Trang chi tiết công ty ftl-->
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb">
<head>
<script id="allmobilize" charset="utf-8" src="/home/style/js/allmobilize.min.js"></script>
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate" media="handheld"  />
<!-- end Ước lượng đám mây -->
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
<script src="/home/style/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="/home/style/js/jquery-hbzx.js" type="text/javascript"></script>

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
                <dl class="job_detail">
                    <dt>
                        <h1 title="${Company.name!""}">
                            <em></em>
                           	${Company.name!""}
                        </h1>
                	</dt>
                    <dd class="job_request">
                    	<#if Company.tags??>
	                    	<#list Company.tags?split(",") as tag>
						         <span>${tag!""}</span>
						    </#list>
	                    	<br/>
                    	</#if>
                    </dd>
                    <dd class="job_bt">
                        <h3 class="description">Sản phẩm của công ty</h3>
                    	<p><font size="3"><b>Tên sản phẩm:</b></font><br>
                       			${Company.productTitle!""}
                   		</p>
                       	<p><font size="3"><b>Ảnh sản phẩm:</b></font><br>
                       	<img src="/photo/view?filename=${Company.productPhoto!"common/product_default.png"}" width="600" height="350">
                       	</p>
                       	<p><font size="3"><b>Giới thiệu sản phẩm:</b></font><br>
                       			${Company.productContent!""}
                   		</p>
                       		
                    </dd>
                    
                    
                  	<dd class="job_bt">
                        <h3 class="description">Mô tả công ty</h3>
                       	<p>${Company.introduction!""}</p>
                    </dd>
                     
                	
                	
             
                </dl>
                                <div id="weibolist"></div>
            </div>	
            <div class="content_r">
                <dl class="job_company">
                    <dt>

                            <div>
                                <h2 class="fl">
                              			${Company.name!""}
                                	<img src="/home/style/images/valid.png" width="15" height="19" title="Công ty được chứng nhận" />
                                	
                                </h2>
                            </div>
                    </dt>
                    <dd>
                    	<ul class="c_feature reset">
                        	<li><span>Lĩnh vực</span>${Company.territory!""}</li>
                        	<li><span>Quy mô</span>${Company.scale!""}</li>
                        	<li>
                        		<span>Trang chủ</span> 
       										<a href="${Company.url!""}" title="${Company.url!""}" rel="nofollow" target="_blank">${Company.url!""}</a>
                        	</li>
                        </ul>
                        
                        <h4>Giai đoạn tài trợ</h4>
                        <ul class="c_feature reset">
                        	<li><span>Giai đoạn hiện tại</span>${Company.finance!""}</li>
                        </ul>
                       	<h4>Địa chỉ làm việc</h4>
                       	<div>${Company.locale!""}</div>
                       	
                       	<h4>Người sáng lập</h4>
                       	 <ul class="c_feature reset">
                        	<li><span>Tên</span>${Company.founderName!""}</li>
                        	<li><span>Chức vụ</span>${Company.founderPosition!""}</li>
                        	<li><span>Ảnh</span><img src="/photo/view?filename=${Company.founderPhoto!""}" width="80" height="80"></li>
                        </ul>
                       	
                       	
                     
                    </dd>
                </dl>
                	<a href="javascript:void(0);" class="eventAd">
                  		<#--  <img src="/home/style/images/tuyen-dung.jpg" width="280" height="135" />  -->
            		</a>
            </div>
       	</div>                    
      <input type="hidden" value="" name="userid" id="userid" />
      <input type="hidden" value="" name="type" id="resend_type" />
      <input type="hidden" value="140204"  id="jobid" />
      <input type="hidden" value="683"  id="companyid" />
      <input type="hidden" value="" id="positionLng" />
      <input type="hidden" value="" id="positionLat" />
	
		
		<div id="tipOverlay" ></div>
<!-------------------------------------Cửa sổ bật lên lightbox  ----------------------------------------->

   
<!------------------------------------- kết thúc ----------------------------------------->

<script type="text/javascript" src="/home/style/js/job_detail.js"></script>
<script type="text/javascript" src="/home/style/js/count.js"></script>


<script src="/home/style/js/common.js" type="text/javascript"></script>


<script type="text/javascript">
	
</script>
<#include "../common/footer.ftl"/>	
<script type="text/javascript" src="/home/style/js/core.min.js"></script>
<script type="text/javascript" src="/home/style/js/popup.min.js"></script>

<!--  -->

</body>
</html>
