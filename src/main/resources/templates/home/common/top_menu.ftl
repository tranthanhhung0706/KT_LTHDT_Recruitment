<!--Đầu menu chung phía trên ftl-->
<div id="header">
    <div class="wrapper">
        <a href="/home/index/index" class="logo">
            <!-- <img src="/home/style/images/logo.png" width="229" height="43" alt="Tuyển dụng - Tập trung vào tuyển dụng trên Internet" /> -->
        </a>
        <ul class="reset" id="navheader">
            <#if index??>
                <li class="current"><a href="/home/index/index">Trang chủ</a></li>
            <#else>
                <li><a href="/home/index/index">Trang chủ</a></li>
            </#if>

            <#if company_list??>
                <li class="current"><a href="/home/index/company_list" >Công ty</a></li>
            <#else>
                <li><a href="/home/index/company_list" >Công ty</a></li>
            </#if>

            <#if about??>
                <li class="current"><a href="/home/index/about" >Điều khoản</a></li>
            <#else>
                <li><a href="/home/index/about" >Điều khoản</a></li>
            </#if>


            <#if user??>
                <#if user.type == 0>
                    <#if my_resume??>
                        <li class="current"><a href="/home/index/my_resume" rel="nofollow">Hồ sơ của tôi</a></li>
                    <#else>
                        <li><a href="/home/index/my_resume" rel="nofollow">Hồ sơ của tôi</a></li>
                    </#if>
                </#if>
            </#if>

            <#if user??>
                <#if user.type == 1>
                    <#if publish_position??>
                        <li class="current"><a href="/home/index/publish_position" rel="nofollow">Đăng tuyển</a></li>
                    <#else>
                        <li><a href="/home/index/publish_position" rel="nofollow">Đăng tuyển</a></li>
                    </#if>
                </#if>
            </#if>

        </ul>

        <#if user ??>
            <dl class="collapsible_menu">
                <dt>
                    <span>${user.username!""}&nbsp;</span> 
                    <span class="red dn" id="noticeDot-0"></span>
                    <i></i>
                </dt>
                <#if user.type == 0>
                    <dd><a rel="nofollow" href="/home/index/my_resume">Hồ sơ của tôi</a></dd>
                    <dd class="btm"><a href="/home/resume/my_delivery_resume">Hồ sơ đã nộp</a></dd>
                <#elseif user.type == 1>
                    <dd><a href="/home/position/my_publish_position">Tuyển dụng đã đăng</a></dd>
                    <dd><a href="/home/resume/my_receive_resume">Hồ sơ đã nhận</a></dd>
                    <dd><a href="/home/index/publish_position">Đăng tuyển dụng</a></dd>
                    <dd class="btm"><a href="/home/company/my_company">Thông tin công ty</a></dd>
                </#if>
                <dd><a href="/home/system/update_password">Thay đổi mật khẩu</a></dd>
                <dd class="logout"><a rel="nofollow" href="/home/system/logout">Đăng xuất</a></dd>

            </dl>
        <#else>
            <ul class="loginTop">
                <li><a href="/home/system/login" rel="nofollow">Đăng nhập</a></li> 
                <li>|</li>
                <li><a href="/home/system/register" rel="nofollow">Đăng ký</a></li>
            </ul>
        </#if>
    </div>
</div><!-- end #header -->
