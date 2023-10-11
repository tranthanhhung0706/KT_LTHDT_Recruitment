package com.tttn.ptithcm.controller.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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
import com.tttn.ptithcm.constant.SessionConstant;
import com.tttn.ptithcm.entity.common.Company;
import com.tttn.ptithcm.entity.common.Position;
import com.tttn.ptithcm.entity.common.User;
import com.tttn.ptithcm.entity.home.Resume;
import com.tttn.ptithcm.service.common.CompanyService;
import com.tttn.ptithcm.service.common.PositionService;
import com.tttn.ptithcm.service.home.ResumeService;
import com.tttn.ptithcm.util.StringUtil;
import com.tttn.ptithcm.util.ValidateEntityUtil;

@RequestMapping("/home/position")
@Controller
public class HomePositionController {

	@Autowired
	private PositionService positionService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ResumeService resumeService;
	

	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String positionDetail(Model model,@RequestParam(name="id",required=true)Long id){
		Position position = positionService.findById(id);
		model.addAttribute("Position", position);
		model.addAttribute("Company", companyService.find(position.getCompany().getId()));
		return "home/position/detail";
	}
	
	// Lấy danh sách công việc có hiệu lực dựa vào từ khóa hoặc không có
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String positionList(Model model,Page page,String search_value){
		
		if(StringUtil.isEmpty(search_value)) {
			//Nếu search_value trống
			//Thông tin danh sách công việc Pagling
			Page p = new Page();
			p.setTotalCount(positionService.getPositionTotal("effective"));
			p.setRows(4);
			model.addAttribute("totalPage",p.getTotalPage());  //Tổng số trang
			model.addAttribute("currentPage",page.getPage()); //trang hiện tại
		    List<Position> findPositionList = positionService.findPositionList("effective", page.getOffset(), 4);
			model.addAttribute("PositionList", findPositionList);
			model.addAttribute("PositionTotal", positionService.getPositionTotal("effective"));
			
		}else {
			//Nếu search_value không trống
			//Thông tin danh sách công việc Pagling
			Page p = new Page();
			p.setTotalCount(positionService.getPositionTotalBySearchValue("effective", search_value));
			p.setRows(4);
			model.addAttribute("totalPage",p.getTotalPage());  //Tổng số trang
			model.addAttribute("currentPage",page.getPage()); //trang hiện tại
		    List<Position> findPositionList = positionService.findPositionListBySearchValue("effective", search_value, page.getOffset(), 4);
			model.addAttribute("PositionList", findPositionList);
			model.addAttribute("PositionTotal", positionService.getPositionTotalBySearchValue("effective", search_value));
			model.addAttribute("SearchValue", search_value);
		}
		
		model.addAttribute("resumeTotal", resumeService.total());
		return "home/position/list";
	}
	
	
	// Công ty lấy danh sách công việc dựa vào trạng thái 
	@RequestMapping(value="/my_publish_position",method=RequestMethod.GET)
	public String myPosition(Model model,String positionState,HttpServletRequest request){
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompanyByUserId = companyService.findByUserId(session_user.getId());
		if(!StringUtil.isEmpty(positionState)) {
			//Nếu positystate không trống
			List<Position> findPositionByCompanyIdAndState = positionService.findPositionByCompanyIdAndState(findCompanyByUserId.getId(), positionState);
			model.addAttribute("PositionList",findPositionByCompanyIdAndState);
			model.addAttribute("positionTotal", positionService.getPositionTotalByState(findCompanyByUserId.getId(), positionState));
			model.addAttribute("positionState", positionState);
		}else {
			//Nếu positystate trống
			List<Position> findPositionByCompanyIdAndState = positionService.findPositionByCompanyIdAndState(findCompanyByUserId.getId(), "effective");
			model.addAttribute("PositionList",findPositionByCompanyIdAndState);
			model.addAttribute("positionTotal", positionService.getPositionTotalByState(findCompanyByUserId.getId(), "effective"));
		}
		
		return "home/position/my_publish_position";
	}
	
	// Chỉnh sửa vị trí công việc cho các trường hợp
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> savePosition(Position position,HttpServletRequest request)
	{
		if(position == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Xác minh hình thức thống nhất
		CodeMsg validate = ValidateEntityUtil.validate(position);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(position.getMinMoney() > position.getMaxMoney()) {
			return Result.error(CodeMsg.POSITION_MONEY_NOT_CORRECT);
		}
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompanyByUserId = companyService.findByUserId(session_user.getId());
		position.setCompany(findCompanyByUserId);
		
		CodeMsg url_way_one = new CodeMsg(1,"");  //Chuyển đến trang vị trí để được xử lý
		CodeMsg url_way_two = new CodeMsg(2,"");  //Chuyển đến trang vị trí ngoại tuyến
		//Tiếp theo, thêm hoặc cập nhật hoạt động cơ sở dữ liệu
		if(position.getId() != null) {
			Position findById = positionService.findById(position.getId());
			if("effective".equals(findById.getState()) || "wait".equals(findById.getState())) {
				position.setState("wait");
			}else if("out".equals(findById.getState())) {
				position.setState("out");
			}
			BeanUtils.copyProperties(position, findById, "id","createTime","updateTime","number");
			if(positionService.save(findById) == null) {
				return Result.error(CodeMsg.POSITION_SAVE_ERROR);
			}
			if("effective".equals(findById.getState()) || "wait".equals(findById.getState())) {
				return Result.error(url_way_one);  //Chuyển đến trang vị trí để được xử lý
			}else{
				return Result.error(url_way_two);  //Chuyển đến trang vị trí ngoại tuyến
			}
		}else {
			//Thêm vào
			position.setState("wait");
			if(positionService.save(position) == null) {
				return Result.error(CodeMsg.POSITION_SAVE_ERROR);
			}
			return Result.error(url_way_one);  //Chuyển đến trang vị trí để được xử lý
		}
	
	}
	
	// Danh-NV Xóa công việc 
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deletePosition(@RequestParam(name="id",required=true)Long id)
	{
		try {
			List<Resume> findByPositionId = resumeService.findByPositionId(id);
			//Xóa tất cả các sơ yếu lý lịch dưới vị trí này
			for(Resume resume : findByPositionId)
			{
				resumeService.delete(resume.getId());
			}
			positionService.delete(id);
		}catch(Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.POSITION_SAVE_ERROR);
		}
		
		return Result.success(true);
	}
	
	// Danh-NV Ngoại tuyến công việc 
	@RequestMapping(value="/changeStateToOut",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> changeStateToOut(@RequestParam(name="id",required=true)Long id)
	{
	
		Position findById = positionService.findById(id);
		findById.setState("out");
		if(positionService.save(findById) == null){
			return Result.error(CodeMsg.POSITION_CHANGE_STATE_TO_OUT_ERROR);
		}
		return Result.success(true);
	}
	
	// Công việc thiết lập chờ xét duyệt 
	@RequestMapping(value="/changeStateToWait",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> changeStateToWait(@RequestParam(name="id",required=true)Long id)
	{
	
		Position findById = positionService.findById(id);
		findById.setState("wait");
		if(positionService.save(findById) == null){
			return Result.error(CodeMsg.POSITION_CHANGE_STATE_TO_WAIT_ERROR);
		}
		return Result.success(true);
	}
	
	
}
