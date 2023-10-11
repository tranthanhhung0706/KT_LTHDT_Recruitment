package com.tttn.ptithcm.interceptor.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.constant.SessionConstant;
import com.tttn.ptithcm.util.StringUtil;


@Component
public class AdminLoginInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(AdminLoginInterceptor.class);
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String requestURI = request.getRequestURI();  //Nhận liên kết đến bị chặn, chẳng hạn như: requesturi =/admin/system/index
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		if(attribute == null && requestURI.contains("/admin/")){
			log.info("Người dùng chưa đăng nhập hoặc phiên bị lỗi và chuyển hướng được chuyển sang trang đăng nhập, URL hiện tại = hiện tại" + requestURI);
			//Trước tiên hãy xác định xem đó có phải là yêu cầu Ajax hay không
			if(StringUtil.isAjax(request)){
				//Cho biết rằng đó là yêu cầu AJAX
				try {
					response.setCharacterEncoding("UTF-8");
					//JSON.parseObject，Chuỗi JSON được chuyển đổi thành đối tượng tương ứng; json.tojsonstring được chuyển đổi thành chuỗi JSON.
					response.getWriter().write(JSON.toJSONString(CodeMsg.SESSION_EXPIRED));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
			//Giải thích rằng đó là một yêu cầu thông thường, bạn có thể trực tiếp chuyển hướng đến trang đăng nhập
			//Người dùng chưa đăng nhập hoặc phiên bị lỗi và chuyển hướng được chuyển sang trang đăng nhập
			try {
				response.sendRedirect("/admin/system/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		log.info("Yêu cầu này đáp ứng các yêu cầu đăng nhập, buông tay" + requestURI);
		return true;
	}
}
