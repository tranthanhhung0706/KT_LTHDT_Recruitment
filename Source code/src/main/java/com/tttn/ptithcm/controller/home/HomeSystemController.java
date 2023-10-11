package com.tttn.ptithcm.controller.home;

import javax.servlet.http.HttpServletRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.constant.SessionConstant;
import com.tttn.ptithcm.entity.common.User;
import com.tttn.ptithcm.service.common.UserService;
import com.tttn.ptithcm.util.StringUtil;
import com.tttn.ptithcm.util.ValidateEntityUtil;
@RequestMapping("/home/system")
@Controller
public class HomeSystemController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "home/system/register";
	}

	// Đăng ký tài khoản
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> registerForm(HttpServletRequest request, User user, String cpacha) {
		// Nếu mục đích kiểm tra mục đích trống
		if (user.getType() == null || user.getType() < 0) {
			return Result.error(CodeMsg.USER_REGISTER_TYPE_EMPTY);
		}
		// Nếu địa chỉ hộp thư trống
		if (StringUtil.isEmpty(user.getEmail())) {
			return Result.error(CodeMsg.USER_EMAIL_EMPTY);
		}
		// Nếu mật khẩu người dùng trống
		if (StringUtil.isEmpty(user.getPassword())) {
			return Result.error(CodeMsg.USER_PASSWORD_EMPTY);
		}

		// Nếu có tên người dùng trống
		if (StringUtil.isEmpty(user.getUsername())) {
			return Result.error(CodeMsg.USER_NAME_EMPTY);
		}
		// Nếu mã xác minh trống
		if(StringUtil.isEmpty(cpacha)){
		return Result.error(CodeMsg.CPACHA_EMPTY);
		}
		// CodeMsg validate = ValidateEntityUtil.validate(user);
		// if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
		// return Result.error(validate);
		// }
		user.setMobile(null);
		// Nếu người dùng không nhấp vào email
		if (request.getSession().getAttribute("user_register") == null) {
			return Result.error(CodeMsg.SYSTEM_CPACHA_EMPTY);
		}
		String correct_cpacha = (String) request.getSession().getAttribute("user_register");
		// Nếu mã xác minh không chính xác
		if (!correct_cpacha.toUpperCase().equals(cpacha.toUpperCase())) {
			return Result.error(CodeMsg.CPACHA_ERROR);
		}
		// Kiểm tra xem hộp thư và tên của người dùng có được lặp lại không
		if (checkEmail(user, -0l)) {
			request.getSession().setAttribute("user_register", null); // Nhập một cái mới vào hộp thư, bạn cần gửi mã xác minh
			return Result.error(CodeMsg.USER_EMAIL_ALREADY_EXIST);
		}
		// if (checkUsername(user, 0l)) {
		// 	return Result.error(CodeMsg.USER_NAME_ALREADY_EXIST);
		// }

		String password = user.getPassword();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = md.digest(password.getBytes());

      StringBuilder hexString = new StringBuilder();
				for (byte hashByte : hashBytes) {
					String hex = Integer.toHexString(0xff & hashByte);
					if (hex.length() == 1)
						hexString.append('0');
					hexString.append(hex);
				}
				user.setPassword(hexString.toString());
			System.out.println("test" + hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (userService.save(user) == null) {
			return Result.error(CodeMsg.USER_REGISTER_ERROR);
		}
		// Phá hủy các quyền mã xác minh
		request.getSession().setAttribute("user_register", null);
		return Result.success(true);
	}

	@RequestMapping(value = "/update_password", method = RequestMethod.GET)
	public String updatePwd(Model model) {
		return "home/system/update_password";
	}

	@RequestMapping(value = "/logout")
	public String loginout(HttpServletRequest request) {
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, null);
		return "redirect:/home/index/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "home/system/login";
	}

	// Đăng nhập vào tải khoản
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> loginForm(String email, String password, String cpacha, HttpServletRequest request) {
		if (StringUtil.isEmpty(email)) {
			return Result.error(CodeMsg.USER_EMAIL_EMPTY);
		}
		if (StringUtil.isEmpty(password)) {
			return Result.error(CodeMsg.USER_PASSWORD_EMPTY);
		}
		if (StringUtil.isEmpty(cpacha)) {
			return Result.error(CodeMsg.CPACHA_EMPTY);
		}
		String correct_cpacha = (String) request.getSession().getAttribute("user_login");
		if (!cpacha.toUpperCase().equals(correct_cpacha.toUpperCase())) {
			return Result.error(CodeMsg.CPACHA_ERROR);
		}
		User user = userService.findByEmail(email);
		// Xác định xem địa chỉ hộp thư có tồn tại không
		if (user == null) {
			return Result.error(CodeMsg.USER_EMAIL_NOT_EXIST);
		}

		password = hashPassword(password);

		// Xác định xem người dùng có nhập đúng mật khẩu không
		if (!password.equals(user.getPassword())) {
			return Result.error(CodeMsg.USER_PASSWORD_ERROR);
		}
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, user);

		return Result.success(true);
	}

	// Thay đổi mật khẩu
	@RequestMapping(value = "/update_password", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updatePwd(String oldPwd, String newPwd, String confirmPwd, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);

		oldPwd = hashPassword(oldPwd);

		if (!user.getPassword().equals(oldPwd)) {
			return Result.error(CodeMsg.USER_OLD_PASSWORD_NOT_CORRECT);
		}
		if (newPwd.length() < 6 || newPwd.length() > 16) {
			return Result.error(CodeMsg.USER_NEW_PASSWORD_NOT_CORRECT);
		}
		if (!newPwd.equals(confirmPwd)) {
			return Result.error(CodeMsg.USER_CONFIRM_PASSWORD_NOT_CORRECT);
		}
		newPwd = hashPassword(newPwd);
		user.setPassword(newPwd);
		if (userService.save(user) == null) {
			return Result.error(CodeMsg.USER_NEW_PASSWORD_SAVE_ERROR);
		}
		User findUser = userService.find(user.getId());
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, findUser);
		return Result.success(true);
	}

	// Kiểm tra xem có sự trùng lặp không email
	public boolean checkEmail(User user, Long id) {
		User findByEmail = userService.findByEmail(user.getEmail());
		if (findByEmail == null)
			return false;
		if (findByEmail.getId().longValue() == id.longValue())
			return false;
		return true;
	}

	// Kiểm tra xem có sự trùng lặp không username
	public boolean checkUsername(User user, Long id) {
		User findByUsername = userService.findByUsername(user.getUsername());
		if (findByUsername == null)
			return false;
		if (findByUsername.getId().longValue() == id.longValue())
			return false;
		return true;
	}

	public String hashPassword(String password) {
		try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hashBytes = md.digest(password.getBytes());

				StringBuilder hexString = new StringBuilder();
				for (byte hashByte : hashBytes) {
						String hex = Integer.toHexString(0xff & hashByte);
						if (hex.length() == 1)
								hexString.append('0');
						hexString.append(hex);
				}
				return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
		}
		return null; // Return null in case of error
	}
}
