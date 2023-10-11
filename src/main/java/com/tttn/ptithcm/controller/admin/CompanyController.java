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
import com.tttn.ptithcm.entity.common.Company;
import com.tttn.ptithcm.entity.common.Position;
import com.tttn.ptithcm.entity.home.Resume;
import com.tttn.ptithcm.service.common.CompanyService;
import com.tttn.ptithcm.service.common.PositionService;
import com.tttn.ptithcm.service.home.ResumeService;
import com.tttn.ptithcm.util.StringUtil;

@RequestMapping("/admin/company")
@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private PositionService positionService;
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		return "admin/company/list";
	}
	


	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> list(@RequestParam(name="state",required=false,defaultValue="") String state,Page page){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(state)) {
			
			List<Company> findCompanyList = companyService.findCompanyList(state, page.getOffset(), page.getRows());
			ret.put("rows", findCompanyList);
			ret.put("total", companyService.getCompanyTotal(state));
		}else {
		
			List<Company> findAllCompanyList = companyService.findAllCompanyList(page.getOffset(), page.getRows());
			ret.put("rows", findAllCompanyList);
			ret.put("total", companyService.total());
		}
		
		return ret; 
	}
	
	
	@RequestMapping(value="/change_state",method=RequestMethod.POST)
	@ResponseBody
	public  Result<Boolean> changeState(Company company){
		if(company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		Company findCompany = companyService.find(company.getId());
		findCompany.setState(company.getState());
		if(companyService.save(findCompany) == null) {
			return Result.error(CodeMsg.COMPANY_CHANGE_STATE_ERROR);
		}
		return Result.success(true); 
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public  Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			List<Resume> findResumeList = resumeService.findByCompanyId(id);
		
			for(Resume resume : findResumeList) {
				resumeService.delete(resume.getId());
			}
			
			List<Position> findPositionList = positionService.findPositionByCompanyId(id);
			for(Position position : findPositionList) {
				positionService.delete(position.getId());
			}
		
			companyService.delete(id);
		}catch(Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.FOREIGN_KEY_RESTRAIN);
		}
	
		return Result.success(true); 
	}
	
}
