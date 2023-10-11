// package com.tttn.ptithcm.controller.admin;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import javax.servlet.http.HttpServletRequest;

// import org.springframework.beans.BeanUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

// import com.tttn.ptithcm.bean.CodeMsg;
// import com.tttn.ptithcm.bean.Page;
// import com.tttn.ptithcm.bean.Result;
// import com.tttn.ptithcm.constant.SessionConstant;
// import com.tttn.ptithcm.entity.admin.Admin;
// import com.tttn.ptithcm.entity.admin.OperaterLog;
// import com.tttn.ptithcm.entity.common.PositionCategory;
// import com.tttn.ptithcm.service.admin.OperaterLogService;
// import com.tttn.ptithcm.service.common.PositionCategoryService;
// import com.tttn.ptithcm.util.PositionCategoryUtil;
// import com.tttn.ptithcm.util.ValidateEntityUtil;
// @RequestMapping("/admin/position_category")
// @Controller
// public class PositionCategoryController {

// 	@Autowired 
// 	private PositionCategoryService positionCategoryService;
	
// 	@Autowired 
// 	private OperaterLogService operaterLogService;
	
	
	
// 	@RequestMapping(value="/list",method=RequestMethod.GET)
// 	public String positionCategory(Model model){
// 		List<PositionCategory> findList = positionCategoryService.findAll();
// 		model.addAttribute("topPositionCategoryList", PositionCategoryUtil.getTopPositionCategory(findList));
// 		return "admin/position/category";
// 	}
	
// 	@RequestMapping(value="/list",method=RequestMethod.POST)
// 	@ResponseBody
// 	public  Map<String, Object>  positionCategoryList(Page page){
// 		Map<String, Object> ret = new HashMap<String, Object>();
// 		List<PositionCategory> findList = positionCategoryService.findAll();
// 		ret.put("rows", findList);
// 		ret.put("total", positionCategoryService.total());
// 		return ret; 
// 	}
	
	
// 	@RequestMapping(value="/add",method=RequestMethod.POST)
// 	@ResponseBody
// 	public  Result<Boolean> add(HttpServletRequest request,PositionCategory positionCategory,@RequestParam(name="parent_id",required=true)Long parent_id){
// 		if(parent_id == null)
// 		{
// 			return Result.error(CodeMsg.POSITION_CATEGORY_PARENT_EMPTY);
// 		}
// 		CodeMsg validate = ValidateEntityUtil.validate(positionCategory);
// 		if(validate.getCode() != CodeMsg.SUCCESS.getCode())
// 		{
// 			return Result.error(validate);
// 		}
// 		if(parent_id == 0)
// 		{
		
// 			positionCategory.setParentId(null);
// 			positionCategory.set_parentId(null);
// 		}else {
		
// 			PositionCategory pc = new PositionCategory();
// 			pc.setId(parent_id);
// 			positionCategory.setParentId(pc);
// 			positionCategory.set_parentId(parent_id);
// 		}
	
// 		if(positionCategoryService.save(positionCategory) == null)
// 		{
// 			return Result.error(CodeMsg.POSITION_CATEGORY_ADD_ERROR);
// 		}
		
		
// 		return Result.success(true); 
// 	}
	
	
// 	@RequestMapping(value="/edit",method=RequestMethod.POST)
// 	@ResponseBody
// 	public  Result<Boolean> edit(PositionCategory positionCategory,HttpServletRequest request){
// 		if(positionCategory.getId() == null || positionCategory.getId() <= 0)
// 		{
// 			return Result.error(CodeMsg.POSITION_CATEGORY_EDIT_ID_EMPTY);
// 		}
// 		CodeMsg validate = ValidateEntityUtil.validate(positionCategory);
// 		if(validate.getCode() != CodeMsg.SUCCESS.getCode())
// 		{
// 			return Result.error(validate);
// 		}
// 		PositionCategory find = positionCategoryService.find(positionCategory.getId());
// 		BeanUtils.copyProperties(positionCategory, find, "id","createTime","updateTime","parentId","_parentId");
	
// 		if(positionCategoryService.save(find) == null)
// 		{
// 			return Result.error(CodeMsg.POSITION_CATEGORY_EDIT_ERROR);
// 		}
	
				
// 		return Result.success(true); 
// 	}
	
	
// 	@RequestMapping(value="/delete",method=RequestMethod.POST)
// 	@ResponseBody
// 	public  Result<Boolean> delete(Long id,HttpServletRequest request){
// 		if(id == null || id <= 0)
// 		{
// 			return Result.error(CodeMsg.POSITION_CATEGORY_DELETE_ID_EMPTY);
// 		}
// 		List<PositionCategory> findAll = positionCategoryService.findAll();
// 		for(PositionCategory positionCategory : findAll)
// 		{
		
// 			if(positionCategory.get_parentId() == id)
// 			{
// 				return Result.error(CodeMsg.POSITION_CATEGORY_PARENT_DELETE_ERROR);
// 			}
// 		}
// 		positionCategoryService.delete(id);
		
// 		return Result.success(true); 
// 	}
	
// }
