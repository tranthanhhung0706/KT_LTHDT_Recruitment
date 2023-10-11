package com.tttn.ptithcm.controller.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tttn.ptithcm.bean.Page;
import com.tttn.ptithcm.constant.SessionConstant;
import com.tttn.ptithcm.entity.common.Company;
import com.tttn.ptithcm.entity.common.Position;
import com.tttn.ptithcm.entity.common.PositionCategory;
import com.tttn.ptithcm.entity.common.User;
import com.tttn.ptithcm.entity.home.EducationBackground;
import com.tttn.ptithcm.entity.home.ExpectWork;
import com.tttn.ptithcm.entity.home.ProjectExperience;
import com.tttn.ptithcm.entity.home.WorkExperience;
import com.tttn.ptithcm.entity.home.WorkShow;
import com.tttn.ptithcm.service.common.CompanyService;
import com.tttn.ptithcm.service.common.PositionCategoryService;
import com.tttn.ptithcm.service.common.PositionService;
import com.tttn.ptithcm.service.home.EducationBackgroundService;
import com.tttn.ptithcm.service.home.ExpectWorkService;
import com.tttn.ptithcm.service.home.ProjectExperienceService;
import com.tttn.ptithcm.service.home.ResumeService;
import com.tttn.ptithcm.service.home.WorkExperienceService;
import com.tttn.ptithcm.service.home.WorkShowService;
import com.tttn.ptithcm.util.PositionCategoryUtil;
import com.tttn.ptithcm.util.StringUtil;

@RequestMapping("/home/index")
@Controller
public class IndexController {

	@Autowired
	private PositionCategoryService positionCategoryService;

	@Autowired
	private WorkExperienceService workExperienceService;

	@Autowired
	private ProjectExperienceService projectExperienceService;

	@Autowired
	private ExpectWorkService expectWorkService;

	@Autowired
	private EducationBackgroundService educationBackgroundService;

	@Autowired
	private WorkShowService workShowService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PositionService positionService;

	@Autowired
	private ResumeService resumeService;
	
	// DanhNV - Hiện danh sách các loại công việc
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		List<PositionCategory> topList = PositionCategoryUtil.getTopPositionCategory(positionCategoryService.findAll());
		List<PositionCategory> secondList = PositionCategoryUtil.getSecondCategory(positionCategoryService.findAll());
		List<PositionCategory> thirdList = PositionCategoryUtil.getThirdCategory(positionCategoryService.findAll());
		List<PositionCategory> quickIndexList = PositionCategoryUtil
				.getQuickIndexCategory(positionCategoryService.findAll());
		model.addAttribute("topPositionCategoryList", topList);
		model.addAttribute("secondPositionCategoryList", secondList);
		model.addAttribute("thirdPositionCategoryList", thirdList);
		model.addAttribute("quickIndexList", quickIndexList);
		model.addAttribute("index", "index"); // top_menu
		model.addAttribute("PositionList", positionService.findPositionList("effective", 0, 4));
		model.addAttribute("PositionTotal", positionService.getPositionTotal("effective"));

		return "home/index/index";
	}

	// Hiện danh sách công ty nếu có hoặc không có từ khóa
	@RequestMapping(value = "/company_list", method = RequestMethod.GET)
	public String companyList(Model model, Page page, String search_value) {
		model.addAttribute("company_list", "company_list"); // top_menu trình diễn

		if (StringUtil.isEmpty(search_value)) {
			// Nếu search_value trống
			// Pagling có được thông tin danh sách công ty
			Page p = new Page();
			p.setTotalCount(companyService.getCompanyTotal("đã xác minh"));
			p.setRows(6);// Danh sách công ty 6 mỗi trang
			model.addAttribute("totalPage", p.getTotalPage()); // Tổng số trang
			model.addAttribute("currentPage", page.getPage()); // trang hiện tại
			model.addAttribute("PositionList", positionService.findAll());
			List<Company> findCompanyList = companyService.findCompanyList("đã xác minh", page.getOffset(), 6);
			model.addAttribute("CompanyList", findCompanyList);
		} else {
			// Nếu search_value không trống
			Page p = new Page();
			p.setTotalCount(companyService.getCompanyTotalBySearchValue("đã xác minh", search_value));
			p.setRows(6);// Danh sách công ty 6 mỗi trang
			model.addAttribute("totalPage", p.getTotalPage()); // Tổng số trang
			model.addAttribute("currentPage", page.getPage()); // trang hiện tại
			List<Company> findCompanyList = companyService.findCompanyListBySearchValue("đã xác minh", search_value,
					page.getOffset(), 6);
			model.addAttribute("CompanyList", findCompanyList);
			model.addAttribute("PositionList", positionService.findAll());
			model.addAttribute("SearchValue", search_value);
		}

		model.addAttribute("resumeTotal", resumeService.total());

		return "home/index/company_list";
	}
	
	// Hiện thông tin hồ sơ
	@RequestMapping(value = "/my_resume", method = RequestMethod.GET)
	public String my_resume(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		int scores = 0; // mức độ hoàn hảo
		int isOK = 0;
		ExpectWork expectWork = expectWorkService.findExpectWorkByUserId(user.getId());
		WorkExperience workExperience = workExperienceService.findWorkExperienceByUserId(user.getId());
		ProjectExperience projectExperience = projectExperienceService.findProjectExperienceByUserId(user.getId());
		EducationBackground educationBackground = educationBackgroundService.findEducationBackgroundByUserId(user.getId());
		WorkShow workShow = workShowService.findWorkShowByUserId(user.getId());
		if (expectWork != null) {
			scores += 20;
		}
		if (workExperience != null) {
			scores += 20;
		}
		if (projectExperience != null) {
			scores += 20;
		}
		if (educationBackground != null) {
			scores += 20;
		}
		if (workShow != null) {
			scores += 20;
		}
		if (workExperience != null && projectExperience != null && educationBackground != null) {
			isOK = 1;
		}
		model.addAttribute("isOK", isOK);
		model.addAttribute("scores", scores);
		model.addAttribute("ExpectWork", expectWork);
		model.addAttribute("WorkExperience", workExperience);
		model.addAttribute("ProjectExperience", projectExperience);
		model.addAttribute("EducationBackground", educationBackground);
		model.addAttribute("WorkShow", workShow);
		model.addAttribute("my_resume", "my_resume"); // top_menu trình diễn
		return "home/index/my_resume";
	}

	// Hiện ví trị công việc
	@RequestMapping(value = "/publish_position", method = RequestMethod.GET)
	public String publishPosition(Model model, HttpServletRequest request, Long id) {
		List<PositionCategory> topList = PositionCategoryUtil.getTopPositionCategory(positionCategoryService.findAll());
		List<PositionCategory> secondList = PositionCategoryUtil.getSecondCategory(positionCategoryService.findAll());
		List<PositionCategory> thirdList = PositionCategoryUtil.getThirdCategory(positionCategoryService.findAll());
		model.addAttribute("topPositionCategoryList", topList);
		model.addAttribute("secondPositionCategoryList", secondList);
		model.addAttribute("thirdPositionCategoryList", thirdList);

		User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompanyByUserId = companyService.findByUserId(user.getId());
		if (findCompanyByUserId == null || !"đã xác minh".equals(findCompanyByUserId.getState())) {
			model.addAttribute("errorMsg",
					"Công ty của bạn chưa được xác thực, vui lòng điền thông tin của công ty hoặc chờ xác thực");
		}

		if (id != null) {
			// trong trường hợp
			Position findById = positionService.findById(id);
			model.addAttribute("editPosition", findById);
		}

		model.addAttribute("publish_position", "publish_position"); // top_menu trình diễn
		return "home/index/publish_position";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model) {
		model.addAttribute("about", "about"); 
		return "home/index/about";
	}
}
