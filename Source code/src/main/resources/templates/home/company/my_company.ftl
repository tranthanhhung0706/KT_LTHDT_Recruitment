<!--công ty của tôiftl-->
<!DOCTYPE HTML>
<html xmlns:wb="http://open.weibo.com/wb"><head>
</script><script type="text/javascript" async="" src="style/js/conversion.js"></script><script src="style/js/allmobilize.min.js" charset="utf-8" id="allmobilize"></script><style type="text/css"></style>
<meta content="no-siteapp" http-equiv="Cache-Control">
<link  media="handheld" rel="alternate">
<!-- end Nhiều mây -->
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Web tuyển dụng</title>




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
<script src="/home/style/js/setting.js"></script>
<script src="/home/common/confirm_null.js"></script>
<body>
<div id="body">
	<#include "../common/top_menu.ftl"/>
    <div id="container">
        <div class="clearfix">
        				
            <div class="content_l">           
	                <div class="c_detail">
	                	<div style="background-color:#fff;" class="c_logo">
		                	<a title="Tải lên một công tyLOGO" id="logoShow" href="javascript:void(0);" onclick="uploadPhoto();">
                			<img width="190" id="preview_company_photo" height="190" alt="công tylogo" src="/home/style/images/logo_default.png">
                        	<span>Thay thế hình ảnh của công ty<br>Chỉ tên công ty bên cạnh bút chì để lưu</span>
	                        </a>
		                </div>
		                <input type="file" id="company_photo_file" style="display:none;" onchange="uploadCompanyPhoto()">
		             
		                
	                    <div class="c_box companyName">
	                    
                    		<#if Company??>
                    			<h2 title="${Company.name}">${Company.name}</h2>
		                    	
		                		
		                    	<#if Company.state == "Chưa Xác Minh">
		                    		<em class="unvalid"></em>
		                			<span class="va dn">Không thể phát hiện được</span>
		                    		<a  class="applyC" href="javascript:void(0);" onclick="applyConfirm('${Company.id}')">Áp dụng cho chứng nhận</a>
		                    	</#if>
	                    		<#if Company.state == "Đang xem xét">
	                    			<em class="unvalid"></em>
		                			<span class="va dn">Không thể phát hiện được</span>
		                    		<a  class="applyC" href="javascript:void(0);">xem xét</a>
		                    	</#if>
		                    	<#if Company.state == "đã xác minh">
		                    		<img src="/home/style/images/valid.png" width="15" height="19" style="margin-bottom:-4px;" title="Doanh nghiệp chứng nhận" />
		                    		<a  class="applyC" href="javascript:void(0);">đã xác minh</a>
		                    	</#if>
		                    	
		                        <div class="clear"></div>
		               			<h1 title="${Company.name}" class="fullname">${Company.name}</h1>
                    		<#else>
                    			<h2 title="Hãy đến và điền vào tên công ty">Hãy đến và điền vào tên công ty</h2>
		                    	<em class="unvalid"></em>
		                		<span class="va dn">Không thể phát hiện được</span>
		                    	
		                    	<a class="applyC"  href="javascript:void(0);" onclick="tips();">Áp dụng cho chứng nhận</a>
		                        <div class="clear"></div>
		               			<h1 title="Hãy đến và điền vào tên công ty" class="fullname">Hãy đến và điền vào tên công ty</h1>
                    		</#if>
               			             
	                        <form class="clear editDetail dn" id="editDetailForm">
	                        	<input type="hidden" id="company_photo" value="common/logo_default.png">
	                            <input type="text" placeholder="Vui lòng nhập tên công ty, giới hạn 30 từ"  maxlength="30" class="require" value="" name="companyShortName" id="companyShortName" tips="Tên công ty không thể trống!"  attributes="error_company_name">
	                            <span for="company_name" generated="true" class="error" id="error_company_name" style="display:none">Tên công ty không thể trống!</span>
	                            
	                            <input type="text" placeholder="Một câu mô tả lợi thế của công ty, giới hạn ở 50 từ" maxlength="50" class="require" value="" name="companyFeatures" id="companyFeatures" tips="Giá trị của công ty không thể trống!"  attributes="error_company_value">
	                            <span for="company_value" generated="true" class="error" id="error_company_value" style="display:none">Giá trị của công ty không thể trống!</span>
	                            
	                            <input type="hidden" value="25927" id="companyId" name="companyId">
	                            <input type="button" value="Lưu" id="saveDetail" class="btn_small" onclick="saveCompanyDetailForm();">
	                            <a id="cancelDetail" class="btn_cancel_s" >Hủy bỏ</a>
		                    </form>
	                            
	                        <div class="clear oneword"><img width="17" height="15" src="/home/style/images/quote_l.png">
	                        	<#if Company??>
	                        		<span>${Company.value}</span> 
	                        	<#else>
	                        		<span>Hãy đến và điền vào các giá trị của công ty!</span> 
	                        	</#if>
		                        <img width="17" height="15" src="/home/style/images/quote_r.png">
	                        </div>
	                        <h3 class="dn">Thẻ đã chọn</h3>
	                        <ul style="overflow:auto" id="hasLabels" class="reset clearfix">
		                        <#if Tags??>
		                           <#if Tags?size gt 0>
							    		<#list Tags as tags>
		                        			<li><span>${tags!""}</span></li>
	                            		</#list>
	                            	</#if>
	                            </#if>
                            	<li class="link">Chỉnh sửa nhãn</li>
	                        </ul>
	                        <div class="dn" id="addLabels">
	                        	<font color="red">Sử dụng , để tách biệt: </font>
	                        	<input type="hidden" value="1" id="labelPageNo">
                            	<input type="text" placeholder="Thêm thẻ tùy chỉnh" name="label" id="label" class="label_form fr">	
	                            <div class="clear"></div>
	                            <ul class="reset clearfix"> </ul>
	                            <a id="saveLabels" class="btn_small" href="javascript:void(0)" onclick="saveCompanyTags();">Lưu</a>
	                            <a id="cancelLabels" class="btn_cancel_s" href="javascript:void(0)">Hủy bỏ</a>
	                        </div>
	                    </div>
	                    <a title="Chỉnh sửa thông tin cơ bản" class="c_edit" id="editCompanyDetail" href="javascript:void(0);"></a>
	                	<div class="clear"></div>
	                </div>
                
                	<div class="c_breakline"></div>
       
       				<div id="Product">
        					        				
	        					<div class="product_wrap">
	                
					            	<!--Biên tập sản phẩm-->
					                <dl id="newProduct" class="newProduct dn">
					                	<dt>
					                    	<h2><em></em>sản phẩm của công ty</h2>
					                    </dt>
					                    <dd id="company_product_edit">
					                       
					                            <div class="new_product">
					                            	
						                            <div class="product_upload productShow" >
						                            	<img width="380" height="220" id="edit_preview_product_photo" src="/photo/view?filename=common/product_default.png">
							                        	<span>Thay thế hình ảnh sản phẩm<br>380*220px Dưới 1m</span>
							                        	<input type="file" style="display:none;" onchange="uploadCompanyProductPhoto()">
							                        </div>
							                        <input type="hidden" value="common/product_default.png" id="product_photo"> 
							                        <input type="file"  id="product_photo_file" title="Hỗ trợ JPG, JPEG, GIF, định dạng PNG, tệp nhỏ hơn 1M" onchange="uploadCompanyProductPhoto();">
							                    	<input type="hidden" value="3" name="type" class="type"> 
							                    </div>
					                            
					                            <div class="cp_intro">
					                               	<input type="text" placeholder="Vui lòng nhập tên sản phẩm"  name="product" id="company_product_name">	
					                                <textarea placeholder="Vui lòng mô tả ngắn gọn định vị sản phẩm, đặc điểm sản phẩm, nhóm người dùng, v.v." maxlength="500"  class="s_textarea" name="productProfile" id="company_product_description"></textarea>	
					                                <div class="clear"></div>
					                                <input type="button" value="Lưu" class="btn_small" onclick="saveCompanyProduct();">
					                                <a class="btn_cancel_s product_delete" href="javascript:void(0)">Hủy bỏ</a>
					                        		<input type="hidden" value="11867" class="product_id">
					                            </div>
											
					                    </dd>
					                </dl>
					                <!--Sản phẩm-->
					                <dl class="c_product" id="company_product_show">
					                	<dt>
					                    	<h2><em></em>sản phẩm của công ty</h2>
					                    </dt>
					                    <dd >
					                    	<img width="380" height="220" id="preview_product_photo" alt="" src="/photo/view?filename=common/product_default.png">
				                        	<div class="cp_intro">
				                        	<#if Company??>
				                        		<#if Company.productTitle??>
				                        			<h3><a target="_blank" href="http://www.weimob.com">${Company.productTitle!""}</a></h3>
				                        		<#else>
				                        			<h3><a target="_blank" href="http://www.weimob.com">Hãy đến và điền vào tiêu đề sản phẩm</a></h3>
				                        		</#if>
						                            <div class="scroll-pane" style="overflow: hidden; padding: 0px; width: 260px;">
						                            <div class="jspContainer" style="width: 260px; height: 140px;"><div class="jspPane" style="padding: 0px; top: 0px; width: 260px;"><div>
						                         <#if Company.productContent??>
				                        			${Company.productContent!""}
				                        		<#else>
				                        				Jiu Xiang không sợ rằng con hẻm đã lỗi thời!<br>
													Hiển thị các sản phẩm tuyệt vời của bạn để thu hút tài năng để xem！<br>
				                        		</#if>
				                        	   	
						                            </div></div></div></div>
						                    <#else>       
						                            <h3><a target="_blank" href="http://www.weimob.com">Hãy đến và điền vào tiêu đề sản phẩm</a></h3>
						                       		<div class="scroll-pane" style="overflow: hidden; padding: 0px; width: 260px;">
						                            <div class="jspContainer" style="width: 260px; height: 140px;"><div class="jspPane" style="padding: 0px; top: 0px; width: 260px;"><div>
				                        			Jiu Xiang không sợ rằng con hẻm đã lỗi thời!<br>
													Hiển thị các sản phẩm tuyệt vời của bạn để thu hút tài năng để xem!<br>
													 </div></div></div></div>
				                        	</#if>
				                        		
					                        </div>
					                        <a title="Chỉnh sửa sản phẩm của công ty" class="c_edit product_edit" href="javascript:void(0)"></a>
					                    </dd>
					                </dl>
	            
	              				</div>
        			</div>   <!-- end #Product --> 
       	
       				<div id="Profile">
				        	<div class="profile_wrap">
					             <!--Không giới thiệu -->
									<dl class="c_section dn">
					                	<dt>
					                    	<h2><em></em>Thông tin công ty</h2>
					                    </dt>
					                    <dd>
					                    	<div class="addnew">
					                        	Lịch sử phát triển của công ty để làm cho người tìm việc hiểu bạn tốt hơn!<br>
					                            <a id="addIntro" href="javascript:void(0)">+Thêm phần giới thiệu của công ty</a>
					                        </div>
					                    </dd>
					                </dl>
					            <!--Biên tập giới thiệu-->
					                <dl class="c_section newIntro dn">
					                    <dt>
					                        <h2><em></em>Thông tin công ty</h2>
					                    </dt>
					                    <dd>
						                    <form id="companyDesForm">
						                        <textarea placeholder="Vui lòng mô tả hồ sơ của công ty, văn hóa doanh nghiệp, v.v." name="companyProfile" id="companyProfile"></textarea>		                                        
						                        <#--  <div class="word_count fr">Bạn cũng có thể nhập <span>1000</span> Kí tự</div>  -->
						                        <div class="clear"></div>
						                        <input type="button" value="Lưu" id="submitProfile" class="btn_small" onclick="saveCompanyIntroducation();">
						                        <a id="delProfile" class="btn_cancel_s" href="javascript:void(0)">Hủy bỏ</a>
						                    </form>
					                    </dd>
					                </dl>
					            
					            <!--Giới thiệu-->
					               <dl class="c_section">
					               		<dt>
					                   		<h2><em></em>Thông tin công ty</h2>
					                   	</dt>
					                   	<dd>
					                   		<#if Company??>
					                   			<#if Company.introduction??>
					                   				<div class="c_intro">${Company.introduction!""}</div>
					                   				<a title="Chỉnh sửa giới thiệu công ty" id="editIntro" class="c_edit" href="javascript:void(0)"></a>
					                   			<#else>
					                   				<div class="addnew">
							                        	Quá trình phát triển của công ty để làm cho người tìm việc biết bạn tốt hơn!<br>
							                            <a id="addIntro" href="javascript:void(0)">+Thêm phần giới thiệu của công ty</a>
							                        </div>
					                   			</#if>
					                   		<#else>
					                   			<div class="addnew">
						                        	Lịch sử phát triển của công ty để làm cho người tìm việc hiểu bạn tốt hơn!<br>
						                            <a id="addIntro" href="javascript:void(0)">+Thêm phần giới thiệu của công ty</a>
						                        </div>
					                   		</#if>
					                   		
					                   	</dd>
					               </dl>
				            </div>
				                 	
	     			</div><!-- end #Profile -->
      	
      
	    
	        			<!--Không có vị trí tuyển dụng-->
						<dl id="noJobs" class="c_section">
		                	<dt>
		                    	<h2><em></em>Vị trí tuyển dụng</h2>
		                    </dt>
		                    <#if PositionList??>
		                    	 <dd>
			                    	<#if PositionList?size gt 0>
				 						<#list PositionList as position>
				 							<p><a href="/home/position/detail?id=${position.id!""}"><font size="3">${position_index+1}.${position.name!""}</a></font></p>
				 						</#list>
				 					</#if>
			                    </dd>
		                    <#else>
		                    	<dd>
			                    	<div class="addnew">
			                        	Xuất bản thông tin tài năng bạn cần, hãy để Bo le và Qianlima gặp nhau càng sớm càng tốt ...…<br>
			                            <a href="/home/index/publish_position">+Thêm vị trí tuyển dụng</a>
			                        </div>
			                    </dd>
		                    </#if>
		                    
		                </dl>
	          	
	          	<input type="hidden" value="" name="hasNextPage" id="hasNextPage">
	          	<input type="hidden" value="" name="pageNo" id="pageNo">
	          	<input type="hidden" value="" name="pageSize" id="pageSize">
          		<div id="flag"></div>
            </div>	<!-- end .content_l -->
            
            <div class="content_r">
            	<div id="Tags">
	            	<div id="c_tags_show" class="c_tags solveWrap">
	                    <table>
	                        <tbody><tr>
	                            <td width="45">Địa điểm</td>
	                            <#if Company??>
	                            	<#if Company.locale??>
	                            		<td>${Company.locale!""}</td>
	                            	<#else>
	                            		<td>Hãy đến và điền vào vị trí của công ty</td>
	                            	</#if>
	                            <#else>
	                            	<td>Hãy đến và điền vào vị trí của công ty</td>
	                            </#if>
	                        </tr>
	                        <tr>
	                            <td>Lĩnh vực</td>
	                             <#if Company??>
	                            	<#if Company.territory??>
	                            		<td>${Company.territory!""}</td>
	                            	<#else>
	                            		<td>Hãy đến và điền vào lĩnh vực của công ty</td>
	                            	</#if>
	                            <#else>
	                            	<td>Hãy đến và điền vào lĩnh vực của công ty</td>
	                            </#if>
	                        </tr>
	                        <tr>
	                            <td>Quy mô</td>
	                             <#if Company??>
	                            	<#if Company.scale??>
	                            		<td>${Company.scale!""}</td>
	                            	<#else>
	                            		<td>Hãy đến và điền vào quy mô công ty</td>
	                            	</#if>
	                            <#else>
	                            	<td>Hãy đến và điền vào quy mô công ty</td>
	                            </#if>
	                        </tr>
	                        <tr>
	                            <td>Trang chủ</td>
	                            <td>
                            	 	<#if Company??>
		                            	<#if Company.territory??>
		                            		<a rel="nofollow" title="${Company.url!""}" href="${Company.url!""}" target="_blank">${Company.url!""}</a>
		                            	<#else>
		                            		Hãy đến và điền vào trang web của công ty
		                            	</#if>
		                            <#else>
		                            		Hãy đến và điền vào trang web của công ty
		                            </#if>
	                            </td>
	                        </tr>
	                    </tbody></table>
	                    <a id="editTags" class="c_edit" href="javascript:void(0)"></a>
	                </div>
	                <div id="c_tags_edit" class="c_tags editTags dn">
		                <form id="tagForms">
		                    <table>
		                        <tbody><tr>
		                            <td>Địa điểm</td>
		                            <td>
		                            	<input type="text" class="require" id="company_locale" placeholder="Vui lòng nhập vị trí" value="Thượng Hải"  tips="Vị trí của công ty không thể trống!"  attributes="error_company_locale">	
		                            	<span for="company_locale" generated="true" class="error" id="error_company_locale" style="display:none">Vị trí của công ty không thể trống!</span>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>Lĩnh vực</td>
		                            <td>
		                            	<input type="text" class="require" id="company_territory" placeholder="Vui lòng vào trường"  tips="Lĩnh vực của công ty không thể trống!"  attributes="error_company_territory">
		                            	<span for="company_territory" generated="true" class="error" id="error_company_territory" style="display:none">Lĩnh vực của công ty không thể trống!</span>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>Quy mô</td>
		                            <td>
		                            	<input type="hidden" value="150-500人" id="companySize" name="companySize">
		                            	<input type="button" value="150-500人"   id="select_sca" class="select_tags require"  tips="Quy mô của công ty không thể trống!"  attributes="error_company_scale">
		                                <div class="selectBox dn" id="box_sca" style="display: none;">
		                                    <ul class="reset">
		                            			<li>Ít hơn 15</li>
		                            			<li>15-50 người</li>
		                            			<li>50-150 người</li>
		                            			<li>150-500 người</li>
		                            			<li>500-2000 người</li>
		                            			<li>2000 người</li>
	                                    	</ul>
		                                </div>	
		                            	<span for="company_scale" generated="true" class="error" id="error_company_scale" style="display:none">Quy mô của công ty không thể trống!</span>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>Trang chủ</td>
		                            <td>
                            			<input type="text" placeholder="Vui lòng nhập URL"  name="companyUrl" id="companyUrl" class="require"  tips="Trang web của công ty không thể trống!"  attributes="error_company_url">	
                            			<span for="company_url" generated="true" class="error" id="error_company_url" style="display:none">Trang web của công ty không thể trống!</span>
		                            </td>
		                        </tr>
		                    </tbody></table>
		                    <input type="button" value="Lưu" id="submitFeatures" class="btn_small" onclick="saveCompanyBasic();">
		                    <a id="cancelFeatures" class="btn_cancel_s" href="javascript:void(0)">Hủy bỏ</a>
		                    <div class="clear"></div>
		            	</form>
	                </div>
       			</div><!-- end #Tags -->
       			
       			<dl class="c_section c_stages">
                	<dt>
                    	<h2><em></em>Loại hình</h2>
                    	<a title="Chỉnh sửa loại hình công ty" class="c_edit" href="javascript:void(0)"></a>
                    </dt>
                    <dd>
                    	<ul class="reset stageshow">
                    		<#if Company??>
	                            	<#if Company.finance??>
	                            		<li>Hiện tại:<span class="c5">${Company.finance!""}</span></li>
	                            	</#if>
                            </#if>
                    	</ul>
                    	<form class="dn" id="stageform">
	                    	<ul id="stagesList" class="reset">
       		                    <li>
		                    		<label>Loại hình hiện nay</label>
		                    		<input type="hidden" class="select_invest_hidden" name="select_invest_hidden">
				                    <input type="button" value="Không thể tài trợ"  class="select_tags_short select_invest" id="company_finance">
				                    <div class="selectBoxShort dn" style="display: none;">
				                        <ul class="reset">
		                        				<li>niêm yết </li>
		                           			<li>tư nhân</li>
		                           			<li>nhà nước</li>
				                        </ul>
				                    </div>
		                    	</li>
		                    </ul>
		                    <input type="button" value="Lưu" class="btn_small" onclick="saveCompanyFinance();">
		                    
		                    <a id="cancelStages" class="btn_cancel_s" href="javascript:void(0)">Hủy bỏ</a>
		                    <div class="clear"></div>
		                    
		                    
		                   
		                </form>
                    </dd>
                </dl><!-- end .c_stages -->
       				
	      
	       		<div id="Member">		
	       		<!--Có một đội ngũ sáng lập-->
		                <dl class="c_section c_member">
		                	<dt>
		                    	<h2><em></em>Đội ngũ sáng lập</h2>
		                    </dt>
		                    <dd> 
	       						<div class="member_wrap">
				                        <!-- Biên tập viên -người sáng lập -->
		                        		<div class="member_info newMember dn" id="edit_company_founder">
			                        		<form class="memberForm" id="founderForm">
			                        			<input type="hidden" value="common/default_headpic.png" id="founder_photo">
					                            <div class="new_portrait">
						                            <div class="portrait_upload dn portraitNo">
						                                <span>Tải lên Avatar người sáng lập</span>
						                            </div>
						                            <div class="portraitShow">
						                            	<img width="120" height="120" id="edit_preview_founder_photo" src="/home/style/images/leader_default.png">
							                        	<span>Thay đổi hình đại diện</span>
							                        </div>
							                        <input type="file" title="ủng hộ jpg、jpeg、gif、png Định dạng，Tệp nhỏ hơn 1M" onchange="uploadCompanyFounderPhoto();" id="founder_photo_file">
							                    	<input type="hidden" value="7" name="type" class="type">
							                    	<input type="hidden" value="images/leader_default.png" name="photo" class="leaderInfos">
						                            <em>
												                                Kích thước: 120*120px<br> 	
												                               Kích thước: < 1m
						                            </em>
						                        </div>
						                        <input type="text" placeholder="Vui lòng nhập tên của người sáng lập"  class="require" id="company_founder_name" name="name" tips="Tên người sáng lập của công ty không thể trống!"  attributes="error_company_founder_name">	
						                        <span for="company_founder_name" generated="true" class="error" id="error_company_founder_name" style="display:none">Tên người sáng lập của công ty không thể trống!</span>
					                            
					                            <input type="text" placeholder="Vui lòng nhập vị trí hiện tại của người sáng lập" value="ceo" class="require" id="company_founder_position" name="position" tips="Vị trí của người sáng lập công ty không thể trống!"  attributes="error_company_founder_position">
					                            <span for="company_founder_position" generated="true" class="error" id="error_company_founder_position" style="display:none">Vị trí của người sáng lập công ty không thể trống!</span>
					                           
					                            <div class="clear"></div>
					                            <input type="button" value="Lưu" class="btn_small" onclick="saveCompanyFounder();">
				                                <a class="btn_cancel_s member_delete" href="javascript:void(0)">Hủy bỏ</a>
				                        		<input type="hidden" value="11493" class="leader_id">
				                        	</form>
				                        </div>
				                        
				                        <!-- Người sáng lập hiển thị -->
				                    	<div class="member_info" id="show_company_founder">
	                    					<a title="Biên tập viên -người sáng lập" class="c_edit member_edit" href="javascript:void(0)"></a>
				                        	<div class="m_portrait">
				                            	<div></div>
				                            	<img width="120" height="120" id="preview_founder_photo" alt="Mặt trời taiying" src="/home/style/images/leader_default.png">
					                        </div>
					                        
				                            <div class="m_name">
				                            	 <#if Company??>
					                            	<#if Company.founderName??>
					                            		${Company.founderName!""}
					                            	<#else>
					                            		Người sáng lập: trống rỗng
					                            	</#if>
					                            <#else>
					                            	Người sáng lập: trống rỗng
					                            </#if>
				                            </div>
				                             <#if Company??>
					                            	<#if Company.founderPosition??>
					                            		 <div class="m_position">${Company.founderPosition!""}</div>
					                            	<#else>
					                            		<div class="m_position">Vị trí người sáng lập: trống rỗng</div>
					                            	</#if>
					                            <#else>
					                            	<div class="m_position">Vị trí người sáng lập: trống rỗng</div>
				                            </#if>
				                           
				                    		<div class="m_intro"></div>
				                        </div>
				                        
				                     </div><!-- end .member_wrap -->
 		                    </dd>
		                </dl>
		       			       	</div> <!-- end #Member -->
	       	
	       	
	       
	       	
        </div>
   	</div>
<#include "../common/alert.ftl"/>
<!------------------------------------lightbox  ----------------------------------------->

<!------------------------------------- end ----------------------------------------->

<script src="/home/style/js/company.min.js" type="text/javascript"></script>
<script>
var avatar = {};
avatar.uploadComplate = function( data ){
	var result = eval('('+ data +')');
	if(result.success){
		jQuery('#logoShow img').attr("src",ctx+ '/'+result.content);
		jQuery.colorbox.close();
	}
};
</script>
<#include "../common/footer.ftl"/>			

<script src="/home/style/js/core.min.js" type="text/javascript"></script>
<script src="/home/style/js/popup.min.js" type="text/javascript"></script>

<!--  -->
<script type="text/javascript">
window.onload = function(){
<!-----------Tại thời điểm sửa đổi, tên công ty và gán giá trị---------------------->
	<#if Company??>
		document.getElementById("companyShortName").value='${Company.name!""}';
		document.getElementById("companyFeatures").value='${Company.value!""}';
		document.getElementById("company_photo").value='${Company.photo!""}';
		$("#preview_company_photo").attr('src','/photo/view?filename=' + '${Company.photo!""}');
	<#else>
		document.getElementById("companyShortName").value='';
		document.getElementById("companyFeatures").value='';
	</#if>
<!-----------Việc chuyển nhượng nhãn công ty trong quá trình sửa đổi---------------------->
	<#if Tags??>
		document.getElementById("label").value='${Company.tags!""}';
	<#else>
		document.getElementById("label").value='';
	</#if>
<!-----------Khi sửa đổi các sản phẩm của công ty, sản phẩm của công ty được chỉ định---------------------->
	<#if Company??>
		document.getElementById("company_product_name").value='${Company.productTitle!""}';
		document.getElementById("company_product_description").value='${Company.productContent!""}';
		$("#preview_product_photo").attr('src','/photo/view?filename=' + '${Company.productPhoto!""}');
		$("#edit_preview_product_photo").attr('src','/photo/view?filename=' + '${Company.productPhoto!""}');
		document.getElementById("product_photo").value='${Company.productPhoto!""}';
		
	<#else>
		document.getElementById("company_product_name").value='';
		document.getElementById("company_product_description").value='';
	</#if>
<!-----------Ý nghĩa của địa điểm, lĩnh vực, quy mô và thông tin web của công ty trong quá trình sửa đổi---------------------->
	<#if Company??>
		document.getElementById("company_locale").value='${Company.locale!""}';
		document.getElementById("company_territory").value='${Company.territory!""}';
		document.getElementById("companyUrl").value='${Company.url!""}';
		document.getElementById("select_sca").value='${Company.scale!""}';
	<#else>
		document.getElementById("company_locale").value='';
		document.getElementById("company_territory").value='';
		document.getElementById("companyUrl").value='';
	</#if>
<!-----------Trong quá trình sửa đổi, phân công loại hình công ty của công ty---------------------->
	<#if Company??>
		document.getElementById("company_finance").value='${Company.finance!""}';
	<#else>
		document.getElementById("company_finance").value='';
	</#if>
<!-----------Khi sửa đổi, người sáng lập của công ty được chỉ định---------------------->
	<#if Company??>
		document.getElementById("company_founder_name").value='${Company.founderName!""}';
		document.getElementById("company_founder_position").value='${Company.founderPosition!""}';
		document.getElementById("founder_photo").value='${Company.founderPhoto!""}';
		$("#edit_preview_founder_photo").attr('src','/photo/view?filename=' + '${Company.founderPhoto!""}');
		$("#preview_founder_photo").attr('src','/photo/view?filename=' + '${Company.founderPhoto!""}');
	<#else>
		document.getElementById("company_founder_name").value='';
		document.getElementById("company_founder_position").value='';
	</#if>
}
//Mở cửa sổ hình ảnh của công ty tải lên
function uploadPhoto(){
	$("#company_photo_file").click();
}
//Tải lên hình ảnh người sáng lập công ty
function uploadCompanyFounderPhoto(){
		if($("#founder_photo_file").val() == '')return;
		var formData = new FormData();
		//Lấy ra cái đầu tiên trong hình ảnh đã chọn
		formData.append('photo',document.getElementById('founder_photo_file').files[0]);
		//Hộp Phiên nhập tiến độ mở ra
		$.ajax({
			url:'/upload/upload_photo',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				if(data.code == 0){
					$("#successMsg").html("Tải lên thành công!");
					$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
					$("#edit_preview_founder_photo").attr('src','/photo/view?filename=' + data.data);
					$("#preview_founder_photo").attr('src','/photo/view?filename=' + data.data);
					$("#founder_photo").val(data.data);
				}else{
					$("#errorMsg").html("Tải lên thất bại,"+data.msg+"!!!");
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
			},
			error:function(data){
				$("#errorMsg").html("Tải lên không thành công, vui lòng tải lên đúng định dạng hoặc tệp kích thước!!!");
				$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
			}
		});
}
//Tải lên hình ảnh sản phẩm của công ty
function uploadCompanyProductPhoto(){
	if($("#product_photo_file").val() == '')return;
		var formData = new FormData();
		//Lấy ra cái đầu tiên trong hình ảnh đã chọn
		formData.append('photo',document.getElementById('product_photo_file').files[0]);
		//Hộp Phiên nhập tiến độ mở ra
		$.ajax({
			url:'/upload/upload_photo',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				if(data.code == 0){
					$("#successMsg").html("Tải lên thành công!");
					$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
					$("#edit_preview_product_photo").attr('src','/photo/view?filename=' + data.data);
					$("#preview_product_photo").attr('src','/photo/view?filename=' + data.data);
					$("#product_photo").val(data.data);
				}else{
					$("#errorMsg").html("Tải lên thất bại,"+data.msg+"!!!");
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
			},
			error:function(data){
				$("#errorMsg").html("Tải lên thất bại, vui lòng tải lên đúng định dạng hoặc tệp kích thước!!!");
				$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
			}
		});
}
//Tải lên hình ảnh của công ty
function uploadCompanyPhoto(){
		if($("#company_photo_file").val() == '')return;
		var formData = new FormData();
		//Lấy ra cái đầu tiên trong hình ảnh đã chọn
		formData.append('photo',document.getElementById('company_photo_file').files[0]);
		//Hộp Phiên nhập tiến độ mở ra
		$.ajax({
			url:'/upload/upload_photo',
			type:'post',
			data:formData,
			contentType:false,
			processData:false,
			success:function(data){
				if(data.code == 0){
					$("#successMsg").html("Tải lên thành công!");
					$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
					$("#preview_company_photo").attr('src','/photo/view?filename=' + data.data);
					$("#company_photo").val(data.data);
				}else{
					$("#errorMsg").html("Tải lên thất bại,"+data.msg+"!!!");
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
			},
			error:function(data){
				$("#errorMsg").html("Tải lên thất bại, vui lòng tải lên đúng định dạng hoặc tệp kích thước!!!");
				$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
			}
		});
}



//Lưu thông tin hình ảnh giới thiệu tiêu đề công ty
function saveCompanyDetailForm()
{
	var name = $("#companyShortName").val();
	var value = $("#companyFeatures").val();
	var photo = $("#company_photo").val();
	
	$('#error_company_name').css('display','none');
	$('#error_company_value').css('display','none');
	//Một biểu mẫu thống nhất không phải là xác minh trống
	if(!checkForm('editDetailForm'))
	{
		return;
	}
	$.ajax({
		url:'/home/company/save_company_detail',
		dataType:'json',
		type:'post',
		data:{name:name,value:value,photo:photo},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Tiêu đề và hồ sơ của công ty được lưu thành công!");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					switch(data.code){
					case -5000:
    					$('#error_company_name').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg);
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
					case -5001:
    					$('#error_company_value').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
    				default:
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
					}
				}
		}
	});
	
}
//Lưu thông tin nhãn của công ty
function saveCompanyTags()
{
	var tags = $("#label").val();
	$.ajax({
		url:'/home/company/save_company_tags',
		dataType:'json',
		type:'post',
		data:{tags:tags},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Nhãn công ty được lưu thành công!");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					$("#errorMsg").html("Lưu không thành công,"+data.msg);
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
		}
	});
}
//Lưu thông tin giới thiệu của công ty
function saveCompanyIntroducation(){
	var introduction = $("#companyProfile").val();
	$.ajax({
		url:'/home/company/save_company_introduction',
		dataType:'json',
		type:'post',
		data:{introduction:introduction},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Công ty giới thiệu đã lưu thành công !");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
		}
	});
}

//Lưu thông tin sản phẩm của công ty
function saveCompanyProduct(){

	var productTitle = $("#company_product_name").val();
	var productContent = $("#company_product_description").val();
	var productPhoto = $("#product_photo").val();
	$.ajax({
		url:'/home/company/save_company_product',
		dataType:'json',
		type:'post',
		data:{productTitle:productTitle,productContent:productContent,productPhoto:productPhoto},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Các sản phẩm của công ty được lưu trữ thành công!");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					$("#errorMsg").html("Thất bại lưu，"+data.msg);
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
		}
	});
	
}
//Lưu thông tin về vị trí, trường, quy mô và trang web của công ty
function saveCompanyBasic(){
	var locale = $("#company_locale").val();
	var territory = $("#company_territory").val();
	var scale = $("#select_sca").val();
	var url = $("#companyUrl").val();
	$('#error_company_locale').css('display','none');
	$('#error_company_scale').css('display','none');
	$('#error_company_territory').css('display','none');
	$('#error_company_url').css('display','none');
	//Một biểu mẫu thống nhất không phải là xác minh trống
	if(!checkForm('tagForms'))
	{
		return;
	}
	$.ajax({
		url:'/home/company/save_company_basic',
		dataType:'json',
		type:'post',
		data:{locale:locale,territory:territory,scale:scale,url:url},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Thông tin vị trí, trường, quy mô và trang web của công ty được đã lưu thành công!");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					switch(data.code){
					case -5012:
    					$('#error_company_locale').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
					case -5013:
    					$('#error_company_territory').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
    				case -5014:
    					$('#error_company_url').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
    				case -5016:
    					$('#error_company_scale').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
    				default:
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
					}
				}
		}
	});
	
}
//Lưu thông tin loại hình công ty của công ty
function saveCompanyFinance()
{
	var finance = $("#company_finance").val();
	$.ajax({
		url:'/home/company/save_company_finance',
		dataType:'json',
		type:'post',
		data:{finance:finance},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Thông tin loại hình công ty của công ty được lưu thành công!");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
		}
	});
}

//Lưu thông tin người sáng lập của công ty
function saveCompanyFounder(){
	var founderName = $("#company_founder_name").val();
	var founderPosition = $("#company_founder_position").val();
	var founderPhoto = $("#founder_photo").val();
	
	$('#error_company_founder_position').css('display','none');
	$('#error_company_founder_name').css('display','none');
	//Một biểu mẫu thống nhất không phải là xác minh trống
	if(!checkForm('founderForm'))
	{
		return;
	}
	$.ajax({
		url:'/home/company/save_company_founder',
		dataType:'json',
		type:'post',
		data:{founderName:founderName,founderPosition:founderPosition,founderPhoto:founderPhoto},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Thông tin của người sáng lập công ty đã được lưu thành công!");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					switch(data.code){
					case -5020:
    					$('#error_company_founder_position').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
					case -5019:
    					$('#error_company_founder_name').css('display','block');
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
    				default:
    					$("#errorMsg").html("Lưu không thành công,"+data.msg+"!!!");
    					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
    					break;
					}
				}
		}
	});
}

//Đăng ký chứng nhận công ty
function applyConfirm(i){
	$.ajax({
		url:'/home/company/apply_confirm',
		dataType:'json',
		type:'post',
		data:{id:i},
		success:function(data){
			if(data.code == 0){
				$("#successMsg").html("Ứng dụng chứng nhận của công ty được nộp thành công!");
				$.colorbox({inline:true, href:$("#successTip"),title:"Thông báo"});
				setTimeout(function(){  
						window.location.reload();//Trang làm mới
						},3000);
			}else{
					$("#errorMsg").html("Đệ trình ứng dụng chứng nhận công ty không thành công,"+data.msg+"!!!");
					$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});
				}
		}
	});

}

//gợi ý
function tips(){

		$("#errorMsg").html("Nếu bạn không đăng ký chứng nhận của công ty, vui lòng điền thông tin cơ bản của công ty trước tiên!!!");
		$.colorbox({inline:true, href:$("#errorTip"),title:"Thông báo"});

}
</script>


<div id="cboxOverlay" style="display: none;"></div><div id="colorbox" class="" role="dialog" tabindex="-1" style="display: none;"><div id="cboxWrapper"><div><div id="cboxTopLeft" style="float: left;"></div><div id="cboxTopCenter" style="float: left;"></div><div id="cboxTopRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxMiddleLeft" style="float: left;"></div><div id="cboxContent" style="float: left;"><div id="cboxTitle" style="float: left;"></div><div id="cboxCurrent" style="float: left;"></div><button type="button" id="cboxPrevious"></button><button type="button" id="cboxNext"></button><button id="cboxSlideshow"></button><div id="cboxLoadingOverlay" style="float: left;"></div><div id="cboxLoadingGraphic" style="float: left;"></div></div><div id="cboxMiddleRight" style="float: left;"></div></div><div style="clear: left;"><div id="cboxBottomLeft" style="float: left;"></div><div id="cboxBottomCenter" style="float: left;"></div><div id="cboxBottomRight" style="float: left;"></div></div></div><div style="position: absolute; width: 9999px; visibility: hidden; display: none;"></div></div></body></html>