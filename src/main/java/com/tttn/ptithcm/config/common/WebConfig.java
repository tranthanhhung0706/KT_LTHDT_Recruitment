package com.tttn.ptithcm.config.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tttn.ptithcm.constant.RuntimeConstant;
import com.tttn.ptithcm.interceptor.admin.AdminLoginInterceptor;
import com.tttn.ptithcm.interceptor.home.UserLoginInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private UserLoginInterceptor userLoginInterceptor;
	
	@Autowired
	private AdminLoginInterceptor adminLoginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.userLoginExcludePathPatterns);
	    registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.adminLoginExcludePathPatterns);
	}

}
