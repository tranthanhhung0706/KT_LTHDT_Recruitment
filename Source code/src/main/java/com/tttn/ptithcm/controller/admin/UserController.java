package com.tttn.ptithcm.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.tttn.ptithcm.entity.common.User;
import com.tttn.ptithcm.entity.home.EducationBackground;
import com.tttn.ptithcm.entity.home.ExpectWork;
import com.tttn.ptithcm.entity.home.ProjectExperience;
import com.tttn.ptithcm.entity.home.Resume;
import com.tttn.ptithcm.entity.home.WorkExperience;
import com.tttn.ptithcm.entity.home.WorkShow;
import com.tttn.ptithcm.service.common.CompanyService;
import com.tttn.ptithcm.service.common.PositionService;
import com.tttn.ptithcm.service.common.UserService;
import com.tttn.ptithcm.service.home.EducationBackgroundService;
import com.tttn.ptithcm.service.home.ExpectWorkService;
import com.tttn.ptithcm.service.home.ProjectExperienceService;
import com.tttn.ptithcm.service.home.ResumeService;
import com.tttn.ptithcm.service.home.WorkExperienceService;
import com.tttn.ptithcm.service.home.WorkShowService;

@RequestMapping("/admin/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private WorkExperienceService workExperienceService;
	
	@Autowired
	private WorkShowService workShowService;
	
	@Autowired 
	private ProjectExperienceService projectExperienceService;
	
	@Autowired
	private ExpectWorkService expectWorkService;
	
	@Autowired
	private EducationBackgroundService educationBackgroundService;
	
	@Autowired 
	private CompanyService companyService;
	
	@Autowired
	private PositionService PositionService;
	

	@RequestMapping(value="/user_info",method=RequestMethod.GET)
	public String myInfo(Model model){
		return "admin/admin/user_info";
	}
	

	
	@RequestMapping(value="/user_info_list",method=RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object>  userInfoList(HttpServletRequest request,Page page){
		Map<String, Object> ret = new HashMap<String, Object>();
		List<User> findAllUserList = userService.findAllUserList(page.getOffset(), page.getRows());
		ret.put("rows", findAllUserList);
		ret.put("total", userService.total());
		return ret; 
	}
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		User user = userService.find(id);
		try {
			if(user.getType() == 1) {
			
				Company findCompany = companyService.findByUserId(id);
				if(findCompany != null) {
					
					List<Resume> findResumeList = resumeService.findByCompanyId(findCompany.getId());
					for(Resume resume : findResumeList) {
						resumeService.delete(resume.getId());
					}
					
					List<Position> findPositionList = PositionService.findPositionByCompanyId(findCompany.getId());
					for(Position position : findPositionList) {
						PositionService.delete(position.getId());
					}
				
					companyService.delete(findCompany.getId());
				}
			}
			if(user.getType() == 0) {
			
				List<Resume> findResume = resumeService.findByUserId(id);
				for(Resume resume : findResume) {
					resumeService.delete(resume.getId());
				}
				
				WorkExperience findWorkExperience = workExperienceService.findWorkExperienceByUserId(id);
				if(findWorkExperience != null) {
					workExperienceService.delete(findWorkExperience.getId());
				}
			
				WorkShow findWorkShow = workShowService.findWorkShowByUserId(id);
				if(findWorkShow != null) {
					workShowService.delete(findWorkShow.getId());
				}
			
				ProjectExperience findProjectExperience = projectExperienceService.findProjectExperienceByUserId(id);
				if(findProjectExperience != null) {
					projectExperienceService.delete(findProjectExperience.getId());
				}
			
				ExpectWork findExpectWork = expectWorkService.findExpectWorkByUserId(id);
				if(findExpectWork != null) {
					expectWorkService.delete(findExpectWork.getId());
				}
			
				EducationBackground findEducationBackground = educationBackgroundService.findEducationBackgroundByUserId(id);
				if(findEducationBackground != null) {
					educationBackgroundService.delete(findEducationBackground.getId());
				}
			}
		
			userService.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			return Result.error(CodeMsg.FOREIGN_KEY_RESTRAIN);
		}
		
		return Result.success(true);
	}
}
