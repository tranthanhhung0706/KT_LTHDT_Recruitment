package com.tttn.ptithcm.controller.home;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.constant.SessionConstant;
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
import com.tttn.ptithcm.util.StringUtil;

@RequestMapping("/home/resume")
@Controller
public class HomeResumeController {

	@Autowired
	private ExpectWorkService expectWorkService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkExperienceService  workExperienceService;
	
	@Autowired
	private ProjectExperienceService projectExperienceService; 
	
	@Autowired
	private EducationBackgroundService educationBackgroundService;
	
	@Autowired
	private WorkShowService workShowService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private ResumeService  resumeService;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
  private JavaMailSender mailSender;
	
	// Xem chi tiết hồ sơ ứng viên
	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String preview(Model model, HttpServletRequest request, Long user_id) {
		if (user_id != null) {
			model.addAttribute("currentUser", userService.find(user_id));
			model.addAttribute("ExpectWork", expectWorkService.findExpectWorkByUserId(user_id));
			model.addAttribute("WorkExperience", workExperienceService.findWorkExperienceByUserId(user_id));
			model.addAttribute("ProjectExperience", projectExperienceService.findProjectExperienceByUserId(user_id));
			model.addAttribute("EducationBackground", educationBackgroundService.findEducationBackgroundByUserId(user_id));
			model.addAttribute("WorkShow", workShowService.findWorkShowByUserId(user_id));
		} else {
			User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
			model.addAttribute("currentUser", userService.find(user.getId()));
			model.addAttribute("ExpectWork", expectWorkService.findExpectWorkByUserId(user.getId()));
			model.addAttribute("WorkExperience", workExperienceService.findWorkExperienceByUserId(user.getId()));
			model.addAttribute("ProjectExperience", projectExperienceService.findProjectExperienceByUserId(user.getId()));
			model.addAttribute("EducationBackground",
					educationBackgroundService.findEducationBackgroundByUserId(user.getId()));
			model.addAttribute("WorkShow", workShowService.findWorkShowByUserId(user.getId()));
		}
		return "home/resume/preview";
	}

	// Lấy hồ sơ mà ứng viên đã gửi
	@RequestMapping(value="/my_delivery_resume",method=RequestMethod.GET)
	public String my_delivery_resume(Model model,String resumeState,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		if(!StringUtil.isEmpty(resumeState)) {
			//Nếu ResumeState không trống
			model.addAttribute("ResumeList", resumeService.findByUserIdAndState(user.getId(), resumeState));
			model.addAttribute("resumeState", resumeState);
			
		}else {
			//Nếu ResumeState trống
			model.addAttribute("ResumeList", resumeService.findByUserId(user.getId()));
		}
		
		return "home/resume/my_delivery_resume";
	}
	
	// Lấy hồ sơ đã nhận dựa vào trạng thái hồ sơ
	@RequestMapping(value="/my_receive_resume",method=RequestMethod.GET)
	public String my_receive_resume(Model model,String resumeState,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		if(!StringUtil.isEmpty(resumeState)) {
			//Nếu ResumeState không trống
			Company findCompanyByUserId = companyService.findByUserId(user.getId());
			List<Resume> findByCompanyIdAndState = resumeService.findByCompanyIdAndState(findCompanyByUserId.getId(), resumeState);
			model.addAttribute("ResumeList", findByCompanyIdAndState);
			model.addAttribute("EducationBackgroundList", educationBackgroundService.findAllEducationBackground());
			model.addAttribute("WorkExperienceList", workExperienceService.findAllWorkExperience());
			model.addAttribute("resumeTotal", resumeService.getPositionTotalByStateAndCompanyId(findCompanyByUserId.getId(), resumeState));
			model.addAttribute("resumeState", resumeState);
		}else {
			Company findCompanyByUserId = companyService.findByUserId(user.getId());
			List<Resume> findByCompanyIdAndState = resumeService.findByCompanyIdAndState(findCompanyByUserId.getId(), "wait");
			model.addAttribute("ResumeList", findByCompanyIdAndState);
			model.addAttribute("EducationBackgroundList", educationBackgroundService.findAllEducationBackground());
			model.addAttribute("WorkExperienceList", workExperienceService.findAllWorkExperience());
			model.addAttribute("resumeTotal", resumeService.getPositionTotalByStateAndCompanyId(findCompanyByUserId.getId(), "wait"));
		}
		return "home/resume/my_receive_resume";
	}
	
	

	@RequestMapping(value="/interview",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> interview(HttpServletRequest request,@RequestParam(name="id",required=true)Long id)
	{
		Resume findByResumeId = resumeService.findByResumeId(id);
		findByResumeId.setState("effective");
		if (resumeService.save(findByResumeId) == null) {
			return Result.error(CodeMsg.RESUME_STATE_SAVE_ERROR);
		}
		return Result.success(true);
	}
	
	// Phỏng vấn gửi mail
	@RequestMapping(value="/interview2",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> interview2(@RequestParam(name = "id", required = true) Long id,
	@RequestParam(name="interview",required=true)String interview, HttpServletRequest request)
	{

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date interviewDate = null;
		try {
			interviewDate = dateFormat.parse(interview);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("interview=" + interviewDate);

		Resume findByResumeId = resumeService.findByResumeId(id);
		findByResumeId.setState("effective");
		findByResumeId.setInterview(interviewDate);
		if (resumeService.save(findByResumeId) == null) {
			return Result.error(CodeMsg.RESUME_STATE_SAVE_ERROR);
		}
		// User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		// String mailUser = session_user.getEmail();
		User receiveUser = userService.findUserByResumeId(id);
		if (receiveUser != null) {
			sendEmail(receiveUser.getEmail(), interviewDate.toString());
		}

		return Result.success(true);

	}
	
	// GỬi mail phỏng vấn
	private void sendEmail(String email, String interviewDate) {

		 // tạo thông báo phỏng vấn
    MimeMessage message = mailSender.createMimeMessage();
    

		try {
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(new InternetAddress("your-email@gmail.com", "Company" ));
        helper.setTo(email);
        helper.setSubject("Thông báo phỏng vấn");

        // Nội dung email dưới dạng HTML với định dạng và màu sắc
        String content = "<html><body>"
                + "<h2 style=\"color: #007BFF;\">Chúc mừng! Bạn đã được chọn để tham gia phỏng vấn.</h2>"
                + "<p style=\"color: #FF0000;\">Phỏng vấn dự kiến diễn ra vào ngày " + interviewDate + ".</p>"
                + "<p style=\"color: #212529;\">Chúng tôi mong đợi gặp bạn. Chúc may mắn!</p>"
                + "</body></html>";

        helper.setText(content, true);

        mailSender.send(message);
    } catch (MessagingException | UnsupportedEncodingException e) {
        // Xử lý ngoại lệ nếu có
    }
	}
	
	// @RequestMapping(value="/change_state",method=RequestMethod.POST)
	// @ResponseBody
	// public  Result<Boolean> changeState(Company company){
	// 	if(company == null) {
	// 		return Result.error(CodeMsg.DATA_ERROR);
	// 	}
	// 	Company findCompany = companyService.find(company.getId());
	// 	findCompany.setState(company.getState());
	// 	if(companyService.save(findCompany) == null) {
	// 		return Result.error(CodeMsg.COMPANY_CHANGE_STATE_ERROR);
	// 	}
	// 	return Result.success(true); 
	// }
	

	@RequestMapping(value="/unsuitable",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> unsuitable(HttpServletRequest request,@RequestParam(name="id",required=true)Long id)
	{
		Resume findByResumeId = resumeService.findByResumeId(id);
		findByResumeId.setState("out");
		if(resumeService.save(findByResumeId) == null) {
			return Result.error(CodeMsg.RESUME_STATE_SAVE_ERROR);
		}
		return Result.success(true);
	}
	

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(HttpServletRequest request,@RequestParam(name="id",required=true)Long id)
	{
		try {
			resumeService.delete(id);
		}catch(Exception e) {
			return Result.error(CodeMsg.RESUME_DELETE_ERROR);
		}
		return Result.success(true);
	}
	
	
	// Ứng viên nộp hồ sơ
	@RequestMapping(value="/submit_resume",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> submitResume(HttpServletRequest request,@RequestParam(name="id",required=true)Long id)
	{
		User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		// WorkExperience workExperience = workExperienceService.findWorkExperienceByUserId(user.getId());
		// ProjectExperience projectExperience = projectExperienceService.findProjectExperienceByUserId(user.getId());
		EducationBackground educationBackground = educationBackgroundService.findEducationBackgroundByUserId(user.getId());
		WorkShow workShow = workShowService.findWorkShowByUserId(user.getId());

		//Xác định xem sơ yếu lý lịch phải được điền vào
		if(educationBackground != null) {
			Position findById = positionService.findById(id);
			Resume resume = new Resume();
			resume.setPosition(findById);
			Resume findByPositionIdAndUserId = resumeService.findByPositionIdAndUserId(user.getId(), findById.getId());
			if(workShow != null && findByPositionIdAndUserId != null) {
				return Result.error(CodeMsg.RESUME_ALREADY_SUBMIT);
			}else {
				resume.setUser(user);
				resume.setCompany(findById.getCompany());
				resume.setState("wait");
				resumeService.save(resume);
				findById.setNumber(findById.getNumber()+1);
				positionService.save(findById);
			}
		}else {
			return Result.error(CodeMsg.RESUME_WRITE_NOT_COMPLETE);
		}
		return Result.success(true);
	}
	

	@RequestMapping(value="/save_expect_work",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveExpectWorkForm(ExpectWork expectWork,HttpServletRequest request)
	{
		if(expectWork == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		User session_user = (User) request.getSession().getAttribute("user");
		expectWork.setUser(session_user);
		//Kiểm tra xem có một công việc dự kiến
		ExpectWork findExpectWork = expectWorkService.findExpectWorkByUserId(session_user.getId());
		if(findExpectWork != null){
			//Sửa đổi hoạt động
			BeanUtils.copyProperties(expectWork, findExpectWork, "id","createTime","updateTime");
			if(expectWorkService.save(findExpectWork) == null){
				return Result.error(CodeMsg.RESUME_EXPECT_WORK_ERROR);
			}
		}else {
			//Thêm hoạt động
			if(expectWorkService.save(expectWork) == null){
				return Result.error(CodeMsg.RESUME_EXPECT_WORK_ERROR);
			}
		}
		//Cập nhật thời gian sửa đổi cuối cùng
		session_user.setUpdateTime(new Date());
		if(userService.save(session_user) == null){
			return Result.error(CodeMsg.USER_UPDATE_TIME_ERROR);
		}
		//Cập nhật quyền của người dùng
		User new_session_user = userService.find(session_user.getId());
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, new_session_user);
		return Result.success(true);
	}
	

	@RequestMapping(value="/save_work_experience",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveWorkExperienceForm(WorkExperience workExperience,HttpServletRequest request)
	{
		if(workExperience == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Kiểm tra xem tên công ty có trống không
		if(StringUtil.isEmpty(workExperience.getName())){
			return Result.error(CodeMsg.RESUME_WORK_EXPERIENCE_COMPANY_NAME_EMPTY);
		}
		//Kiểm tra xem tên của vị trí có trống không
		if(StringUtil.isEmpty(workExperience.getPosition())) {
			return Result.error(CodeMsg.RESUME_WORK_EXPERIENCE_POSITION_NAME_EMPTY);
		}
		//Kiểm tra xem thời gian bắt đầu có trống không
		if(workExperience.getStartYear() == null || workExperience.getStartMonth() == null || "Năm bắt đầu".equals(workExperience.getStartYear()) || "Bắt đầu tháng".equals(workExperience.getStartMonth())) {
			return Result.error(CodeMsg.RESUME_WORK_EXPERIENCE_START_TIME_EMPTY);
		}
		//Kiểm tra xem thời gian kết thúc có trống không
		if(workExperience.getEndYear() == null || workExperience.getEndMonth() == null || "Kết thúc".equals(workExperience.getEndYear()) || "Tháng kết thúc".equals(workExperience.getEndMonth())) {
			return Result.error(CodeMsg.RESUME_WORK_EXPERIENCE_END_TIME_EMPTY);
		}
		//Kiểm tra xem thời gian kết thúc có lớn hơn thời gian bắt đầu không
		if(Integer.valueOf(workExperience.getStartYear()) > Integer.valueOf(workExperience.getEndYear()) || (workExperience.getStartYear().equals(workExperience.getEndYear()) && Integer.valueOf(workExperience.getStartMonth()) > Integer.valueOf(workExperience.getEndMonth()))) {
			return Result.error(CodeMsg.RESUME_WORK_EXPERIENCE_TIME_NOT_CORRECT);
		}
		User session_user = (User) request.getSession().getAttribute("user");
		workExperience.setUser(session_user);
		//Kiểm tra xem bạn có thêm kinh nghiệm làm việc không
		WorkExperience findWorkExperience = workExperienceService.findWorkExperienceByUserId(session_user.getId());
		if(findWorkExperience != null){
			//Sửa đổi hoạt động
			BeanUtils.copyProperties(workExperience, findWorkExperience, "id","createTime","updateTime");
			if(workExperienceService.save(findWorkExperience) == null){
				return Result.error(CodeMsg.RESUME_WORK_EXPERIENCE_ERROR);
			}
		}else {
			//Thêm hoạt động
			if(workExperienceService.save(workExperience) == null){
				return Result.error(CodeMsg.RESUME_WORK_EXPERIENCE_ERROR);
			}
		}
		//Cập nhật thời gian sửa đổi cuối cùng
		session_user.setUpdateTime(new Date());
		if(userService.save(session_user) == null){
			return Result.error(CodeMsg.USER_UPDATE_TIME_ERROR);
		}
		//Cập nhật quyền của người dùng
		User new_session_user = userService.find(session_user.getId());
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, new_session_user);
		
		return Result.success(true);
	}
	

	@RequestMapping(value="/save_project_experience",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveProjectExperienceForm(ProjectExperience projectExperience,HttpServletRequest request)
	{
		if(projectExperience == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Kiểm tra xem tên dự án có trống không
		if(StringUtil.isEmpty(projectExperience.getName())){
			return Result.error(CodeMsg.RESUME_PROJECT_EXPERIENCE_PROJECT_NAME_EMPTY);
		}
		//Kiểm tra xem tên của vị trí có trống không
		if(StringUtil.isEmpty(projectExperience.getPosition())) {
			return Result.error(CodeMsg.RESUME_PROJECT_EXPERIENCE_POSITION_NAME_EMPTY);
		}
		//Kiểm tra xem thời gian bắt đầu có trống không
		if(projectExperience.getStartYear() == null || projectExperience.getStartMonth() == null || "Năm bắt đầu".equals(projectExperience.getStartYear()) || "Bắt đầu tháng".equals(projectExperience.getStartMonth())) {
			return Result.error(CodeMsg.RESUME_PROJECT_EXPERIENCE_START_TIME_EMPTY);
		}
		//Kiểm tra xem thời gian kết thúc có trống không
		if(projectExperience.getEndYear() == null || projectExperience.getEndMonth() == null || "Kết thúc".equals(projectExperience.getEndYear()) || "Tháng kết thúc".equals(projectExperience.getEndMonth())) {
			return Result.error(CodeMsg.RESUME_PROJECT_EXPERIENCE_END_TIME_EMPTY);
		}
		//Kiểm tra xem thời gian kết thúc có lớn hơn thời gian bắt đầu không
		if(Integer.valueOf(projectExperience.getStartYear()) > Integer.valueOf(projectExperience.getEndYear()) || (projectExperience.getStartYear().equals(projectExperience.getEndYear()) && Integer.valueOf(projectExperience.getStartMonth()) > Integer.valueOf(projectExperience.getEndMonth()))) {
			return Result.error(CodeMsg.RESUME_PROJECT_EXPERIENCE_TIME_NOT_CORRECT);
		}
		User session_user = (User) request.getSession().getAttribute("user");
		projectExperience.setUser(session_user);
		//Kiểm tra xem bạn có thêm kinh nghiệm dự án không
		ProjectExperience findProjectExperience = projectExperienceService.findProjectExperienceByUserId(session_user.getId());
		if(findProjectExperience != null){
			//Sửa đổi hoạt động
			BeanUtils.copyProperties(projectExperience, findProjectExperience, "id","createTime","updateTime");
			if(projectExperienceService.save(findProjectExperience) == null){
				return Result.error(CodeMsg.RESUME_PROJECT_EXPERIENCE_ERROR);
			}
		}else {
			//Thêm hoạt động
			if(projectExperienceService.save(projectExperience) == null){
				return Result.error(CodeMsg.RESUME_PROJECT_EXPERIENCE_ERROR);
			}
		}
		//Cập nhật thời gian sửa đổi cuối cùng
		session_user.setUpdateTime(new Date());
		if(userService.save(session_user) == null){
			return Result.error(CodeMsg.USER_UPDATE_TIME_ERROR);
		}
		//Cập nhật quyền của người dùng
		User new_session_user = userService.find(session_user.getId());
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, new_session_user);
		
		return Result.success(true);
	}
	
	
	@RequestMapping(value="/save_education_background",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveEducationBackgroundForm(EducationBackground educationBackground,HttpServletRequest request)
	{
		if(educationBackground == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Kiểm tra xem tên trường có trống không
		if(StringUtil.isEmpty(educationBackground.getSchool_name())){
			return Result.error(CodeMsg.RESUME_EDUCATION_BACKGROUND_SCHOOL_NAME_EMPTY);
		}
		//Kiểm tra xem tên chuyên nghiệp có trống không
		if(StringUtil.isEmpty(educationBackground.getMajor())) {
			return Result.error(CodeMsg.RESUME_EDUCATION_BACKGROUND_MAJOR_EMPTY);
		}
		//Kiểm tra xem phần đầu của đầu có trống không
		if(educationBackground.getStartYear() == null || "Năm bắt đầu".equals(educationBackground.getStartYear())) {
			return Result.error(CodeMsg.RESUME_EDUCATION_BACKGROUND_START_TIME_EMPTY);
		}
		//Kiểm tra xem kết thúc kiểm tra có trống không
		if(educationBackground.getEndYear() == null || "Kết thúc".equals(educationBackground.getEndYear())) {
			return Result.error(CodeMsg.RESUME_EDUCATION_BACKGROUND_END_TIME_EMPTY);
		}
		//Kiểm tra xem phần cuối của kết thúc có lớn hơn đầu của đầu
		if(Integer.valueOf(educationBackground.getStartYear()) > Integer.valueOf(educationBackground.getEndYear())) {
			return Result.error(CodeMsg.RESUME_EDUCATION_BACKGROUND_TIME_NOT_CORRECT);
		}
		User session_user = (User) request.getSession().getAttribute("user");
		educationBackground.setUser(session_user);
		
		//Kiểm tra xem bạn có thêm một nền tảng giáo dục không
		EducationBackground findEducationBackground = educationBackgroundService.findEducationBackgroundByUserId(session_user.getId());
		if(findEducationBackground != null){
			//Sửa đổi hoạt động
			BeanUtils.copyProperties(educationBackground, findEducationBackground, "id","createTime","updateTime");
			if(educationBackgroundService.save(findEducationBackground) == null){
				return Result.error(CodeMsg.RESUME_EDUCATION_BACKGROUND_ERROR);
			}
		}else {
			//Thêm hoạt động
			if(educationBackgroundService.save(educationBackground) == null){
				return Result.error(CodeMsg.RESUME_EDUCATION_BACKGROUND_ERROR);
			}
		}
		
		//Cập nhật thời gian sửa đổi cuối cùng
		session_user.setUpdateTime(new Date());
		if(userService.save(session_user) == null){
			return Result.error(CodeMsg.USER_UPDATE_TIME_ERROR);
		}
		//Cập nhật quyền của người dùng
		User new_session_user = userService.find(session_user.getId());
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, new_session_user);
		return Result.success(true);
	}
	
	

	@RequestMapping(value="/save_work_show",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveWorkShow(WorkShow workShow,HttpServletRequest request)
	{
		if (workShow == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		User session_user = (User) request.getSession().getAttribute("user");
		workShow.setUser(session_user);

		//Kiểm tra xem bạn có thêm một nền tảng giáo dục không
		WorkShow findWorkShow = workShowService.findWorkShowByUserId(session_user.getId());
		if (findWorkShow != null) {
			//Sửa đổi hoạt động
			BeanUtils.copyProperties(workShow, findWorkShow, "id", "createTime", "updateTime");
			if (workShowService.save(findWorkShow) == null) {
				return Result.error(CodeMsg.RESUME_WORK_SHOW_SAVE_ERROR);
			}
		} else {
			//Thêm hoạt động
			if (workShowService.save(workShow) == null) {
				return Result.error(CodeMsg.RESUME_WORK_SHOW_SAVE_ERROR);
			}
		}

		//Cập nhật thời gian sửa đổi cuối cùng
		session_user.setUpdateTime(new Date());
		if (userService.save(session_user) == null) {
			return Result.error(CodeMsg.USER_UPDATE_TIME_ERROR);
		}
		//Cập nhật quyền của người dùng
		User new_session_user = userService.find(session_user.getId());
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, new_session_user);

		return Result.success(true);
	}
	
	@RequestMapping(value="/save_cv",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCV(WorkShow workShow,HttpServletRequest request)
	{
		if (workShow == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		User session_user = (User) request.getSession().getAttribute("user");
		workShow.setUser(session_user);

		//Kiểm tra xem bạn có thêm một nền tảng giáo dục không
		WorkShow findWorkShow = workShowService.findWorkShowByUserId(session_user.getId());
		if (findWorkShow != null) {
			//Sửa đổi hoạt động
			BeanUtils.copyProperties(workShow, findWorkShow, "id", "createTime", "updateTime");
			if (workShowService.save(findWorkShow) == null) {
				return Result.error(CodeMsg.RESUME_WORK_SHOW_SAVE_ERROR);
			}
		} else {
			//Thêm hoạt động
			if (workShowService.save(workShow) == null) {
				return Result.error(CodeMsg.RESUME_WORK_SHOW_SAVE_ERROR);
			}
		}

		return Result.success(true);
	}
}
