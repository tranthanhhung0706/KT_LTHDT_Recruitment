package com.tttn.ptithcm.controller.home;

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
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.constant.SessionConstant;
import com.tttn.ptithcm.entity.common.Company;
import com.tttn.ptithcm.entity.common.User;
import com.tttn.ptithcm.service.common.CompanyService;
import com.tttn.ptithcm.service.common.PositionService;
import com.tttn.ptithcm.util.StringUtil;

@RequestMapping("/home/company")
@Controller
public class HomeCompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PositionService positionService;

	// Danh-NV Lấy chi tiết công ty
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String companyDetail(Model model, @RequestParam(name = "id", required = true) Long id) {
		Company findCompany = companyService.find(id);
		model.addAttribute("Company", findCompany);
		return "home/company/detail";
	}

	// Danh-NV Thông tin công ty
	@RequestMapping(value = "/my_company", method = RequestMethod.GET)
	public String myCompany(Model model, HttpServletRequest request) {

		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company company = companyService.findByUserId(session_user.getId());
		model.addAttribute("Company", company);
		if (company != null && company.getTags() != null) {
			model.addAttribute("Tags", company.getTags().split(","));
		}
		if (company != null && company.getId() != null) {
			model.addAttribute("PositionList", positionService.findPositionByCompanyIdAndState(company.getId(), "effective"));
		}

		return "home/company/my_company";
	}

	// Thiệt lập đang xem xét
	@RequestMapping(value = "/apply_confirm", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> applyConfirm(@RequestParam(name = "id", required = true) Long id) {
		Company findCompany = companyService.find(id);
		findCompany.setState("Đang xem xét");
		if (companyService.save(findCompany) == null) {
			return Result.error(CodeMsg.COMPANY_APPLY_SAVE_ERROR);
		}
		return Result.success(true);
	}

	// Lưu thông tin công ty 
	@RequestMapping(value = "/save_company_detail", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCompanyDetail(Company company, HttpServletRequest request) {
		if (company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		// Tên của công ty kiểm tra trống rỗng
		if (StringUtil.isEmpty(company.getName())) {
			return Result.error(CodeMsg.COMPANY_NAME_EMPTY);
		}
		// Kiểm tra xem giá trị của công ty có trống không
		if (StringUtil.isEmpty(company.getValue())) {
			return Result.error(CodeMsg.COMPANY_VALUE_EMPTY);
		}
		// Kiểm tra độ dài tên của công ty
		if (company.getName().length() > 30) {
			return Result.error(CodeMsg.COMPANY_NAME_WORD_OVER);
		}
		// Kiểm tra độ dài tên của công ty
		if (company.getValue().length() > 50) {
			return Result.error(CodeMsg.COMPANY_VALUE_WORD_OVER);
		}
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompany = companyService.findByUserId(session_user.getId());
		if (findCompany == null) {
			// Thêm hoạt động
			if (checkCompanyName(company, 0l)) {
				return Result.error(CodeMsg.COMPANY_NAME_ALREADY_EXIST);
			}
			company.setUser(session_user);
			if (companyService.save(company) == null) {
				return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_SAVE_ERROR);
			}
		} else {
			// Sửa đổi hoạt động
			if (checkCompanyName(company, findCompany.getId())) {
				return Result.error(CodeMsg.COMPANY_NAME_ALREADY_EXIST);
			}
			company.setState("Đang xem xét");
			BeanUtils.copyProperties(company, findCompany, "id", "createTime", "updateTime", "user", "tags", "productPhoto",
					"productTitle", "productContent", "introduction", "locale", "territory", "scale", "url", "finance",
					"founderName", "founderPosition", "founderPhoto");
			if (companyService.save(findCompany) == null) {
				return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_SAVE_ERROR);
			}
		}
		return Result.success(true);
	}

	@RequestMapping(value = "/save_company_tags", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCompanyTags(Company company, HttpServletRequest request) {
		if (company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		// Chiều dài nhãn công ty kiểm tra
		if (company.getTags().length() > 30) {
			return Result.error(CodeMsg.COMPANY_TAGS_WORD_OVER);
		}
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompany = companyService.findByUserId(session_user.getId());
		if (findCompany == null) {
			// Để người dùng điền vào thông tin cơ bản của tên và giá trị trước tiên
			return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_PRIORITY);
		} else {
			// Sửa đổi hoạt động
			company.setState("Đang xem xét");
			BeanUtils.copyProperties(company, findCompany, "id", "createTime", "updateTime", "user", "name", "value", "photo",
					"productPhoto", "productTitle", "productContent", "introduction", "locale", "territory", "scale", "url",
					"finance", "founderName", "founderPosition", "founderPhoto");
			if (companyService.save(findCompany) == null) {
				return Result.error(CodeMsg.COMPANY_TAGS_SAVE_ERROR);
			}
		}
		return Result.success(true);
	}

	// Lưu sản phẩm của công ty
	@RequestMapping(value = "/save_company_product", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCompanyProduct(Company company, HttpServletRequest request) {
		if (company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		// Kiểm tra độ dài tiêu đề sản phẩm của công ty
		if (company.getProductTitle().length() > 50) {
			return Result.error(CodeMsg.COMPANY_PRODUCT_NAME_WORD_OVER);
		}
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompany = companyService.findByUserId(session_user.getId());
		if (findCompany == null) {
			// Để người dùng điền vào thông tin cơ bản của tên và giá trị trước tiên
			return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_PRIORITY);
		} else {
			// Sửa đổi hoạt động
			if ("".equals(company.getProductTitle())) {
				company.setProductTitle(null);
			}
			if ("".equals(company.getProductContent())) {
				company.setProductContent(null);
			}
			company.setState("Đang xem xét");
			BeanUtils.copyProperties(company, findCompany, "id", "createTime", "updateTime", "tags", "user", "name", "value",
					"photo", "introduction", "locale", "territory", "scale", "url", "finance", "founderName", "founderPosition",
					"founderPhoto");
			if (companyService.save(findCompany) == null) {
				return Result.error(CodeMsg.COMPANY_PRODUCT_SAVE_ERROR);
			}
		}
		return Result.success(true);
	}

	// Lưu giới thiệu công ty
	@RequestMapping(value = "/save_company_introduction", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCompanyIntroduction(Company company, HttpServletRequest request) {
		if (company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompany = companyService.findByUserId(session_user.getId());
		if (findCompany == null) {
			// Để người dùng điền vào thông tin cơ bản của tên và giá trị trước tiên
			return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_PRIORITY);
		} else {
			// Sửa đổi hoạt động
			if ("".equals(company.getIntroduction())) {
				company.setIntroduction(null);
			}
			company.setState("Đang xem xét");
			BeanUtils.copyProperties(company, findCompany, "id", "createTime", "updateTime", "tags", "user", "name", "value",
					"photo", "productPhoto", "productTitle", "productContent", "locale", "territory", "scale", "url", "finance",
					"founderName", "founderPosition", "founderPhoto");
			if (companyService.save(findCompany) == null) {
				return Result.error(CodeMsg.COMPANY_INTRODUCTION_SAVE_ERROR);
			}
		}
		return Result.success(true);
	}

	// Lưu các thông tin cơ bản công ty
	@RequestMapping(value = "/save_company_basic", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCompanyBasic(Company company, HttpServletRequest request) {
		if (company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		// Vị trí của công ty kiểm tra trống rỗng
		if (StringUtil.isEmpty(company.getLocale())) {
			return Result.error(CodeMsg.COMPANY_LOCALE_EMPTY);
		}
		// Là lĩnh vực kiểm tra lĩnh vực của công ty trống
		if (StringUtil.isEmpty(company.getTerritory())) {
			return Result.error(CodeMsg.COMPANY_TERRITORY_EMPTY);
		}
		// Quy mô của công ty kiểm tra trống
		if (StringUtil.isEmpty(company.getScale())) {
			return Result.error(CodeMsg.COMPANY_SCALE_EMPTY);
		}
		// Trang web của công ty thử nghiệm có trống không?
		if (StringUtil.isEmpty(company.getUrl())) {
			return Result.error(CodeMsg.COMPANY_URL_EMPTY);
		}

		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompany = companyService.findByUserId(session_user.getId());
		if (findCompany == null) {
			// Để người dùng điền vào thông tin cơ bản của tên và giá trị trước tiên
			return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_PRIORITY);
		} else {
			// Sửa đổi hoạt động
			company.setState("Đang xem xét");
			BeanUtils.copyProperties(company, findCompany, "id", "createTime", "updateTime", "user", "name", "tags", "value",
					"photo", "productPhoto", "productTitle", "productContent", "introduction", "finance", "founderName",
					"founderPosition", "founderPhoto");
			if (companyService.save(findCompany) == null) {
				return Result.error(CodeMsg.COMPANY_BASIC_SAVE_ERROR);
			}
		}
		return Result.success(true);
	}

	// Lưu vào loại hình công ty
	@RequestMapping(value = "/save_company_finance", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCompanyFinance(Company company, HttpServletRequest request) {
		if (company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompany = companyService.findByUserId(session_user.getId());
		if (findCompany == null) {
			// Để người dùng điền vào thông tin cơ bản của tên và giá trị trước tiên
			return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_PRIORITY);
		} else {
			// Sửa đổi hoạt động
			company.setState("Đang xem xét");
			BeanUtils.copyProperties(company, findCompany, "id", "createTime", "updateTime", "user", "name", "tags", "value",
					"photo", "productPhoto", "productTitle", "productContent", "introduction", "locale", "territory", "scale",
					"url", "founderName", "founderPosition", "founderPhoto");
			if (companyService.save(findCompany) == null) {
				return Result.error(CodeMsg.COMPANY_FINANCE_SAVE_ERROR);
			}
		}
		return Result.success(true);
	}

	// Lưu nhà sáng lập công ty
	@RequestMapping(value = "/save_company_founder", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> saveCompanyFounder(Company company, HttpServletRequest request) {
		if (company == null) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		// Tên của người sáng lập công ty kiểm tra trống rỗng
		if (StringUtil.isEmpty(company.getFounderName())) {
			return Result.error(CodeMsg.COMPANY_FOUNDER_NAME_EMPTY);
		}
		// Vị trí của người sáng lập công ty kiểm tra trống rỗng
		if (StringUtil.isEmpty(company.getFounderPosition())) {
			return Result.error(CodeMsg.COMPANY_FOUNDER_POSITION_EMPTY);
		}
		if (company.getFounderName().length() > 50) {
			return Result.error(CodeMsg.COMPANY_FOUNDER_NAME_WORD_OVER);
		}
		if (company.getFounderPosition().length() > 30) {
			return Result.error(CodeMsg.COMPANY_FOUNDER_POSITION_WORD_OVER);
		}
		User session_user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		Company findCompany = companyService.findByUserId(session_user.getId());
		if (findCompany == null) {
			// Để người dùng điền vào thông tin cơ bản của tên và giá trị trước tiên
			return Result.error(CodeMsg.COMPANY_NAME_AND_VALUE_PRIORITY);
		} else {
			// Sửa đổi hoạt động
			company.setState("Đang xem xét");
			BeanUtils.copyProperties(company, findCompany, "id", "createTime", "updateTime", "user", "name", "tags", "value",
					"photo", "productPhoto", "productTitle", "productContent", "introduction", "locale", "territory", "scale",
					"url", "finance");
			if (companyService.save(findCompany) == null) {
				return Result.error(CodeMsg.COMPANY_FOUNDER_SAVE_ERROR);
			}
		}
		return Result.success(true);
	}

	// Kiểm tra xem có sự trùng lặp không companyName
	public boolean checkCompanyName(Company company, Long id) {
		Company findByCompanyName = companyService.findByCompanyName(company.getName());
		if (findByCompanyName == null)
			return false; // Không lặp lại
		if (findByCompanyName.getId().longValue() == id.longValue())
			return false; // Không lặp lại
		return true; // Nhân bản
	}
}
