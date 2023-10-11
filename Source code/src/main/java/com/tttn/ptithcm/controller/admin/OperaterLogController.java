package com.tttn.ptithcm.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.bean.Page;
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.service.admin.OperaterLogService;

@RequestMapping("/admin/operater_log")
@Controller
public class OperaterLogController {

	@Autowired 
	private OperaterLogService operaterLogService;
	

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String operaterLog(Model model){
		return "admin/operater_log/list";
	}
	
	
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> operaterLogList(Page page){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("rows", operaterLogService.findOperaterLogList(page.getOffset(), page.getRows()));
		ret.put("total", operaterLogService.total());
		return ret; 
	}
	

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteOperaterLogs(String ids){
		if(ids == null || "".equals(ids))
		{
			return Result.error(CodeMsg.OPERATERLOG_DELETE_ID_EMPTY);
		}
		String[] split = ids.split(",");
		for(int i=0;i<split.length;i++)
		{
			try {
				operaterLogService.delete(Long.valueOf(split[i]));
			}catch(Exception e)
			{
				return Result.error(CodeMsg.OPERATERLOG_DELETE_ERROR);
			}
		}
		return Result.success(true); 
	}
	
	
}
