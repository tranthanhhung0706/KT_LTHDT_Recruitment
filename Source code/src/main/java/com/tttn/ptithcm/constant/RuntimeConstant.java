package com.tttn.ptithcm.constant;

import java.util.Arrays;
import java.util.List;


public class RuntimeConstant {

	public static List<String> adminLoginExcludePathPatterns = Arrays.asList(
			"/admin/common/**",
			"/admin/easyui/**",
			"/admin/h-ui/**",
			"/admin/login/**",
			"/admin/system/login",
			"/common/cpacha/generate_cpacha",
			"/common/cpacha/generate_emailCpacha",
			"/photo/view",
			"/upload/upload_photo"
		);
	

	public static List<String> userLoginExcludePathPatterns = Arrays.asList(
			"/common/cpacha/generate_cpacha",
			"/common/cpacha/generate_emailCpacha",
			"/photo/view",
			"/upload/upload_photo",
			"/home/index/index",
			"/home/style/**",
			"/home/system/login",
			"/home/system/register",
			"/home/common/**",
			"/home/index/company_list",
			"/home/company/detail",
			"/home/position/detail",
			"/home/position/list"
		);
}
