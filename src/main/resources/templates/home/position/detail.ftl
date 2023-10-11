
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
<script src="/home/style/js/jquery-hbzx.js" type="text/javascript"></script>
<script src="/home/style/js/additional-methods.js" type="text/javascript"></script>
<script src="/home/style/js/setting.js"></script>
<script src="/home/common/confirm_null.js"></script>
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
                        <h1 title="${Position.name!""}">
                            <em></em>
                           	${Position.name!""}
                        </h1>
                	</dt>
                    <dd class="job_request">
                    	<span class="red">${Position.minMoney!""} triệu-${Position.maxMoney!""} triệu</span>
											<br>
                    	<span>Địa chỉ: ${Position.city!""}</span>
											<br>
                    	<span>Kinh nghiệm: ${Position.workExperience!""}</span>
                    	<span>Bằng cấp: ${Position.degree!""}</span>
											<br>
                    	<span>Loại hình: ${Position.workType!""}</span><br>
                    	Yêu cầu : ${Position.advantage!""}
                      	<div>Thời gian tạo： ${Position.createTime!""}</div>
                    </dd>
                  	<dd class="job_bt">
                        <h3 class="description">Mô tả công ty</h3>
                       	${Position.description!""}
                    </dd>
                	
					<dd>
						<#if user??>
							<#if user.type == 0>
                				<a href="javascript:void(0);" title="Nộp hồ sơ" class="btn fr btn_apply" onclick="submitResume('${Position.id}');">Nộp hồ sơ</a>
                			</#if>
                		<#else>
                			<a href="javascript:void(0);" title="Nộp hồ sơ" class="btn fr btn_apply" onclick="submitResume('${Position.id}');">Nộp hồ sơ</a>
                		</#if>
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
                                	<img src="/home/style/images/valid.png" width="15" height="19" title="认证企业" />
                                	
                                </h2>
                            </div>
                    </dt>
                    <dd>
                    	<ul class="c_feature reset">
                        	<li><span>Khu vực</span>${Company.territory!""}</li>
                        	<li><span>Số lượng: </span>${Company.scale!""}</li>
                        	<li>
                        		<span>Trang chủ</span> 
       											<a href="${Company.url!""}" title="${Company.url!""}" rel="nofollow" target="_blank">${Company.url!""}</a>
                        	</li>
                        </ul>
                        
                        <h4>Loại hình</h4>
                        <ul class="c_feature reset">
                        	<li><span>Loại hình hiện nay</span>${Company.finance!""}</li>
                        </ul>
                       	<h4>địa chỉ làm việc</h4>
                       	<div>${Company.locale!""}</div>
                       	
                       	<h4>Người sáng lập</h4>
                       	 <ul class="c_feature reset">
                        	<li><span>Tên</span>${Company.founderName!""}</li>
                        	<li><span>Chức vụ</span>${Company.founderPosition!""}</li>
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
<#include "../common/alert.ftl"/>
<!-------------------------------------lightbox  ----------------------------------------->

   
<!------------------------------------- end ----------------------------------------->

<script type="text/javascript" src="/home/style/js/job_detail.js"></script>
<script type="text/javascript" src="/home/style/js/count.js"></script>


<script src="/home/style/js/common.js" type="text/javascript"></script>


<script type="text/javascript">
	function submitResume(i){
	
		$.ajax({
			url:'/home/resume/submit_resume',
			dataType:'json',
			type:'post',
			data:{id:i},
			success:function(data){
				if(data.code == 0){
					$("#successMsg").html("Gửi thành công！");
					$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				}else{
						$("#errorMsg").html("Gửi thất bại,"+data.msg+"!!!");
						$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
					}
			}
		});
		
	}
</script>
<#include "../common/footer.ftl"/>	
<script type="text/javascript" src="/home/style/js/core.min.js"></script>
<script type="text/javascript" src="/home/style/js/popup.min.js"></script>

<!--  -->

</body>
</html>