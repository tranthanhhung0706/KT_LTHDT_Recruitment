package com.tttn.ptithcm.controller.common;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tttn.ptithcm.bean.CodeMsg;
import com.tttn.ptithcm.bean.Result;
import com.tttn.ptithcm.util.CpachaUtil;


@Controller
@RequestMapping("/common/cpacha")
public class CpachaController {

	private Logger log = LoggerFactory.getLogger(CpachaController.class);
	
	@Value("${yjq.sender.email.username}")
	private String username;  //Tài khoản email của nhà phát hành
	
	@Value("${yjq.sender.email.password}")
	private String password;  //Mật khẩu email của người gửi
	
	@Value("${yjq.sender.email.address}")
	private String senderAddress; //Địa chỉ email của người gửi

	@Autowired
  private JavaMailSender mailSender;
	
	
	@RequestMapping(value="/generate_cpacha",method=RequestMethod.GET)
	public void generateCpacha(
			@RequestParam(name="vl",defaultValue="4")Integer vcodeLength,//vcodeLength,Độ dài mã xác minh
			@RequestParam(name="fs",defaultValue="21")Integer fontSize,//fontSize,Xác minh kích thước phông chữ mã
			@RequestParam(name="w",defaultValue="98")Integer width,//width,Chiều rộng hình ảnh
			@RequestParam(name="h",defaultValue="33")Integer height,//height,Chiều cao hình ảnh
			@RequestParam(name="method")String method,//Được sử dụng để gọi tên của phương thức này, sử dụng tên này làm khóa và lưu trữ nó trong phiên
			HttpServletRequest request,
			HttpServletResponse response){
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLength,fontSize,width,height);
		String generatorVCode = cpachaUtil.generatorVCode(); //Giá trị của mã xác minh
		request.getSession().setAttribute(method, generatorVCode);
		log.info("Mã xác minh được tạo thành công，method=" + method + ",value=" + generatorVCode);
		try {
			ImageIO.write(cpachaUtil.generatorRotateVCodeImage(generatorVCode, true), "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/generate_emailCpacha",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> generateEmailCpacha(HttpServletRequest request,String receiver,String type)
	{
		
		//Xác định xem địa chỉ hộp thư của người nhận có trống không
		if (receiver == null || "".equals(receiver)) {
			return Result.error(CodeMsg.USER_EMAIL_EMPTY);
		}
		//Xác định xem danh mục thư có trống không
		if (type == null || "".equals(type)) {
			return Result.error(CodeMsg.USER_EMAIL_TYPE_EMPTY);
		}
		System.out.println();

		try {
			//Nếu danh mục thư là xác minh đăng ký người dùng
			if ("user_register".equals(type)) {
				//Đặt chủ đề
				//message.setSubject("Xác minh hộp thư đăng ký người dùng");

				//Nhận mã xác minh hộp thư
				CpachaUtil cpachaUtil = new CpachaUtil(4);
				String generatorVCode = cpachaUtil.generatorVCode(); //Giá trị của mã xác minh
				request.getSession().setAttribute(type, generatorVCode);

				sendEmail(receiver, generatorVCode);

				log.info("Mã xác minh hộp thư được tạo thành công，method=" + type + ",value=" + generatorVCode);
				//message.setText("Welcome to JavaMail World!");
				//message.setContent((generatorVCode), "text/html;charset=utf-8");
			} else {
				return Result.error(CodeMsg.USER_EMAIL_TYPE_ERROR);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(CodeMsg.USER_EMAIL_SEND_ERROR);
		}

		return Result.success(true);
	}
	
	private void sendEmail(String email, String otpCode) {

		// create email message
		SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("your-email@gmail.com");
    message.setTo(email);
    message.setSubject("Mã xác thực cho việc đăng ký");
    message.setText("Mã xác thực của bạn cho việc đăng ký là: " + otpCode);
    mailSender.send(message);
	}

}
