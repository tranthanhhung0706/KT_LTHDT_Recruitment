package com.tttn.ptithcm.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.bean.Page;
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.entity.common.Position;
import com.tttn.ptithcm.service.common.PositionService;
import com.tttn.ptithcm.util.StringUtil;

@RequestMapping("/admin/position")
@Controller
public class PositionController {

	@Autowired 
	private PositionService positionService;


	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		return "admin/position/list";
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> list(@RequestParam(name="state",required=false,defaultValue="") String state,Page page){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(state)) {
		
			List<Position> findPositionList = positionService.findPositionList(state, page.getOffset(), page.getRows());
			ret.put("rows", findPositionList);
			ret.put("total", positionService.getPositionTotal(state));
		}else {
		
			List<Position> findAllPositionList = positionService.findAllPositionList(page.getOffset(), page.getRows());
			ret.put("rows", findAllPositionList);
			ret.put("total", positionService.total());
		}
		
		return ret; 
	}
	
	
	@RequestMapping(value="/change_state",method=RequestMethod.POST)
	@ResponseBody
	public  Result<Boolean> changeState(Position position){
		if(position == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		Position findPosition = positionService.findById(position.getId());
		findPosition.setState(position.getState());
		if(positionService.save(findPosition) == null) {
			return Result.error(CodeMsg.POSITION_CHANGE_STATE_ERROR);
		}
		return Result.success(true); 
	}
}
