package com.tttn.ptithcm.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.constant.SessionConstant;
import com.tttn.ptithcm.entity.admin.Admin;
import com.tttn.ptithcm.entity.admin.OperaterLog;
import com.tttn.ptithcm.service.admin.AdminService;
import com.tttn.ptithcm.service.admin.OperaterLogService;
import com.tttn.ptithcm.util.ValidateEntityUtil;

@RequestMapping("/admin/system")
@Controller
public class SystemController {

	@Autowired 
	private AdminService adminService ;
	
	@Autowired
	private OperaterLogService  operaterLogService; 
	
	
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String list(Model model){
		return "admin/system/index";
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model){
		return "admin/system/login";
	}
	
	
	@RequestMapping(value="/logout")
	public String loginout(HttpServletRequest request){
		request.getSession().setAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY , null);
		return "redirect:/admin/systen/login";
	}
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> login(Admin admin,String cpacha,HttpServletRequest request)
	{
		if(admin == null)
		{
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Xác định xem người dùng có nhập mã xác minh không
		if(cpacha == null)
		{
			return Result.error(CodeMsg.CPACHA_EMPTY);
		}

		String systemCpacha = (String) request.getSession().getAttribute("admin_login");
		if(!systemCpacha.toUpperCase().equals(cpacha.toUpperCase()))
		{
			return Result.error(CodeMsg.CPACHA_ERROR);
		}
		
		//Xác định xem người dùng có tồn tại không
		Admin findByAdminName = adminService.findByAdminName(admin.getAdminName());
		if(findByAdminName == null)
		{
			return Result.error(CodeMsg.USER_NO_EXIST);
		}
		
		findByAdminName.setPassword(admin.getPassword());
		//CodeMsg validate = ValidateEntityUtil.validate(findByAdminName);
		// if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
		// 	return Result.error(validate);
		// }
		
		
		if(!findByAdminName.getPassword().equals(admin.getPassword()))
		{
			return Result.error(CodeMsg.USER_PASSWORD_ERROR);
		}
		//Quyền 
		request.getSession().setAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY, findByAdminName);
		
		
		return Result.success(true);
	}
	
	
}
