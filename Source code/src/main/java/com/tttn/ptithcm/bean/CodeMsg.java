package com.tttn.ptithcm.bean;


public class CodeMsg {

	private int code;

	private String msg;


	public CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public static CodeMsg SUCCESS = new CodeMsg(0, "Thành công");
	// Mã lỗi dữ liệu không hợp lệ
	public static CodeMsg DATA_ERROR = new CodeMsg(-1, "Dữ liệu không hợp lệ");
	public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-2, "");
	public static CodeMsg CPACHA_EMPTY = new CodeMsg(-3, "Mã xác thực không được để trống");
	public static CodeMsg CPACHA_ERROR = new CodeMsg(-4, "Mã xác thực không chính xác");
	public static CodeMsg SESSION_EXPIRED = new CodeMsg(-5,
			"Chưa đăng nhập hoặc phiên làm việc hết hạn, vui lòng làm mới trang hoặc đăng nhập lại");
	public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-6, "Định dạng file không đúng");
	public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-7, "Lỗi tải ảnh lên");
	public static CodeMsg SYSTEM_CPACHA_EMPTY = new CodeMsg(-8, "Vui lòng nhấp vào gửi mã xác thực qua email trước");
	public static CodeMsg FOREIGN_KEY_RESTRAIN = new CodeMsg(-9,
			"Người dùng này có ràng buộc khóa ngoại, không thể xóa");

	// Mã lỗi dữ liệu người dùng
	public static CodeMsg USER_NAME_EMPTY = new CodeMsg(-1000, "Tên người dùng không được để trống");
	public static CodeMsg USER_PASSWORD_EMPTY = new CodeMsg(-1001, "Mật khẩu không được để trống");
	public static CodeMsg USER_NO_EXIST = new CodeMsg(-1002, "Người dùng không tồn tại");
	public static CodeMsg USER_PASSWORD_ERROR = new CodeMsg(-1003, "Mật khẩu người dùng không chính xác");
	public static CodeMsg USER_EDIT_ID_EMPTY = new CodeMsg(-1004,
			"Không tìm thấy người dùng để chỉnh sửa! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_EDIT_ERROR = new CodeMsg(-1005,
			"Chỉnh sửa không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_EMAIL_EMPTY = new CodeMsg(-1006, "Địa chỉ email không được để trống");
	public static CodeMsg USER_EMAIL_TYPE_EMPTY = new CodeMsg(-1007,
			"Loại email không được để trống! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_EMAIL_SEND_ERROR = new CodeMsg(-1008,
			"Gửi email không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_EMAIL_TYPE_ERROR = new CodeMsg(-1009,
			"Lỗi khi lấy loại email! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_REGISTER_TYPE_EMPTY = new CodeMsg(-1010, "Mục đích đăng ký không được để trống");
	public static CodeMsg USER_REGISTER_ERROR = new CodeMsg(-1011, "Mục đích đăng ký không được để trống");
	public static CodeMsg USER_EMAIL_ALREADY_EXIST = new CodeMsg(-1012, "Email này đã được đăng ký");
	public static CodeMsg USER_NAME_ALREADY_EXIST = new CodeMsg(-1013, "Tên người dùng này đã được đăng ký");
	public static CodeMsg USER_EMAIL_NOT_EXIST = new CodeMsg(-1013, "Địa chỉ email không tồn tại");
	public static CodeMsg USER_MOBILE_EMPTY = new CodeMsg(-1014, "Số điện thoại không được để trống");
	public static CodeMsg USER_SAVE_ERROR = new CodeMsg(-1015,
			"Lưu thông tin người dùng không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_UPDATE_TIME_ERROR = new CodeMsg(-1016,
			"Lỗi cập nhật thời gian chỉnh sửa cuối cùng! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_SAVE_SELF_DESCRIPTION_ERROR = new CodeMsg(-1017,
			"Lưu thông tin mô tả bản thân không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg USER_MOBILE_NOT_CORRECT = new CodeMsg(-1018, "Vui lòng nhập số điện thoại đúng độ dài");
	public static CodeMsg USER_OLD_PASSWORD_NOT_CORRECT = new CodeMsg(-1019, "Mật khẩu cũ không chính xác");
	public static CodeMsg USER_NEW_PASSWORD_NOT_CORRECT = new CodeMsg(-1020,
			"Mật khẩu mới phải có độ dài từ 6-16 ký tự");
	public static CodeMsg USER_CONFIRM_PASSWORD_NOT_CORRECT = new CodeMsg(-1021,
			"Mật khẩu xác nhận không khớp với mật khẩu mới");
	public static CodeMsg USER_NEW_PASSWORD_SAVE_ERROR = new CodeMsg(-1022,
			"Lưu thông tin mật khẩu mới không thành công! Vui lòng liên hệ quản trị viên");

	// Mã lỗi quản lý nghề nghiệp và lưu trữ vị trí
	public static CodeMsg POSITION_CATEGORY_PARENT_EMPTY = new CodeMsg(-2000,
			"Loại nghề cha không đượcbỏ trống, vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_CATEGORY_ADD_ERROR = new CodeMsg(-2001,
			"Thêm loại nghề không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_CATEGORY_EDIT_ID_EMPTY = new CodeMsg(-2002,
			"Không tìm thấy id loại nghề để chỉnh sửa! Vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_CATEGORY_EDIT_ERROR = new CodeMsg(-2003,
			"Chỉnh sửa loại nghề không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_CATEGORY_DELETE_ID_EMPTY = new CodeMsg(-2004,
			"Không tìm thấy id loại nghề để xóa! Vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_CATEGORY_DELETE_ERROR = new CodeMsg(-2005,
			"Xóa loại nghề không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_CATEGORY_PARENT_DELETE_ERROR = new CodeMsg(-2006,
			"Loại nghề này có chứa loại con, vui lòng xóa loại con trước");
	public static CodeMsg POSITION_SAVE_ERROR = new CodeMsg(-2007,
			"Lưu vị trí không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_MONEY_NOT_CORRECT = new CodeMsg(-2008,
			"Mức lương tối thiểu của vị trí không được lớn hơn mức lương tối đa");
	public static CodeMsg POSITION_DELETE_ERROR = new CodeMsg(-2009,
			"Xóa vị trí không thành công! Vui lòng liên hệ quản trị viên");
	public static CodeMsg POSITION_CHANGE_STATE_TO_OUT_ERROR = new CodeMsg(-2010,
			"Vị trí không thể đưa ra ngoài! Vui lòng liên hệ quản trị viên");

	public static CodeMsg POSITION_CHANGE_STATE_TO_WAIT_ERROR = new CodeMsg(-2010, "Không thể đưa vị trí lên mạng! Vui lòng liên hệ với quản trị viên");
	public static CodeMsg POSITION_CHANGE_STATE_ERROR = new CodeMsg(-2011, "Không thể thay đổi trạng thái của vị trí! Vui lòng liên hệ với quản trị viên");

	public static CodeMsg OPERATERLOG_DELETE_ID_EMPTY = new CodeMsg(-3000,
			"Lỗi khi lấy id nhật ký hoạt động cần xóa! Vui lòng liên hệ quản trị viên");
	public static CodeMsg OPERATERLOG_DELETE_ERROR = new CodeMsg(-3001,
			"Lỗi khi xóa nhật ký hoạt động! Vui lòng liên hệ quản trị viên");

	// Mã lỗi dữ liệu quản lý hồ sơ
	public static CodeMsg RESUME_EXPECT_WORK_ERROR = new CodeMsg(-4000,
			"Lỗi khi lưu thông tin công việc mong muốn! Vui lòng liên hệ quản trị viên");
	public static CodeMsg RESUME_WORK_EXPERIENCE_ERROR = new CodeMsg(-4001,
			"Lỗi khi lưu thông tin kinh nghiệm làm việc! Vui lòng liên hệ quản trị viên");
	public static CodeMsg RESUME_WORK_EXPERIENCE_COMPANY_NAME_EMPTY = new CodeMsg(-4002,
			"Tên công ty trong kinh nghiệm làm việc không được để trống");
	public static CodeMsg RESUME_WORK_EXPERIENCE_POSITION_NAME_EMPTY = new CodeMsg(-4003,
			"Tên vị trí trong kinh nghiệm làm việc không được để trống");
	public static CodeMsg RESUME_WORK_EXPERIENCE_START_TIME_EMPTY = new CodeMsg(-4004,
			"Thời gian bắt đầu trong kinh nghiệm làm việc không được để trống");
	public static CodeMsg RESUME_WORK_EXPERIENCE_END_TIME_EMPTY = new CodeMsg(-4005,
			"Thời gian kết thúc trong kinh nghiệm làm việc không được để trống");
	public static CodeMsg RESUME_WORK_EXPERIENCE_TIME_NOT_CORRECT = new CodeMsg(-4006,
			"Thời gian bắt đầu trong kinh nghiệm làm việc không được lớn hơn thời gian kết thúc");
	public static CodeMsg RESUME_PROJECT_EXPERIENCE_START_TIME_EMPTY = new CodeMsg(-4007,
			"Thời gian bắt đầu trong kinh nghiệm dự án không được để trống");
	public static CodeMsg RESUME_PROJECT_EXPERIENCE_END_TIME_EMPTY = new CodeMsg(-4008,
			"Thời gian kết thúc trong kinh nghiệm dự án không được để trống");
	public static CodeMsg RESUME_PROJECT_EXPERIENCE_TIME_NOT_CORRECT = new CodeMsg(-4009,
			"Thời gian bắt đầu trong kinh nghiệm dự án không được lớn hơn thời gian kết thúc");
	public static CodeMsg RESUME_PROJECT_EXPERIENCE_PROJECT_NAME_EMPTY = new CodeMsg(-4010,
			"Tên dự án trong kinh nghiệm dự án không được để trống");
	public static CodeMsg RESUME_PROJECT_EXPERIENCE_POSITION_NAME_EMPTY = new CodeMsg(-4011,
			"Tên vị trí trong kinh nghiệm dự án không được để trống");
	public static CodeMsg RESUME_PROJECT_EXPERIENCE_ERROR = new CodeMsg(-4012,
			"Lỗi khi lưu thông tin kinh nghiệm dự án! Vui lòng liên hệ quản trị viên");
	public static CodeMsg RESUME_EDUCATION_BACKGROUND_START_TIME_EMPTY = new CodeMsg(-4013,
			"Năm bắt đầu trong học vấn không được để trống");
	public static CodeMsg RESUME_EDUCATION_BACKGROUND_END_TIME_EMPTY = new CodeMsg(-4014,
			"Năm kết thúc trong học vấn không được để trống");
	public static CodeMsg RESUME_EDUCATION_BACKGROUND_SCHOOL_NAME_EMPTY = new CodeMsg(-4015,
			"Tên trường học trong học vấn không được để trống");
	public static CodeMsg RESUME_EDUCATION_BACKGROUND_MAJOR_EMPTY = new CodeMsg(-4016,
			"Tên ngành học trong học vấn không được để trống");
	public static CodeMsg RESUME_EDUCATION_BACKGROUND_ERROR = new CodeMsg(-4017,
			"Lỗi khi lưu thông tin học vấn! Vui lòng liên hệ quản trị viên");
	public static CodeMsg RESUME_EDUCATION_BACKGROUND_TIME_NOT_CORRECT = new CodeMsg(-4018,
			"Năm bắt đầu trong học vấn không được lớn hơn năm kết thúc");
	public static CodeMsg RESUME_WORK_SHOW_SAVE_ERROR = new CodeMsg(-4019,
			"Lỗi khi lưu thông tin hiển thị công việc! Vui lòng liên hệ quản trị viên");
	public static CodeMsg RESUME_ALREADY_SUBMIT = new CodeMsg(-4020,
			"Hồ sơ đã được nộp cho vị trí này! Vui lòng chọn một vị trí khác");
	public static CodeMsg RESUME_STATE_SAVE_ERROR = new CodeMsg(-4021,
			"Lỗi khi lưu trạng thái hồ sơ! Vui lòng liên hệ quản trị viên");
	public static CodeMsg RESUME_DELETE_ERROR = new CodeMsg(-4022, "Lỗi khi xóa hồ sơ! Vui lòng liên hệ quản trị viên");
	public static CodeMsg RESUME_WRITE_NOT_COMPLETE = new CodeMsg(-4023,
			"Hồ sơ chưa hoàn thiện! Vui lòng hoàn thiện hồ sơ trước");

	// Mã lỗi dữ liệu quản lý công ty
	public static CodeMsg COMPANY_NAME_EMPTY = new CodeMsg(-5000, "Tên công ty không được để trống");
	public static CodeMsg COMPANY_VALUE_EMPTY = new CodeMsg(-5001, "Giá trị công ty không được để trống");
	public static CodeMsg COMPANY_NAME_WORD_OVER = new CodeMsg(-5002, "Tên công ty không được vượt quá 30 ký tự");
	public static CodeMsg COMPANY_VALUE_WORD_OVER = new CodeMsg(-5003, "Giá trị công ty không được vượt quá 50 ký tự");
	public static CodeMsg COMPANY_NAME_ALREADY_EXIST = new CodeMsg(-5004,
			"Tên công ty đã tồthành, vui lòng chọn một tên khác");
	public static CodeMsg COMPANY_NAME_AND_VALUE_SAVE_ERROR = new CodeMsg(-5005,
			"Lỗi khi lưu tên công ty và giá trị công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_TAGS_SAVE_ERROR = new CodeMsg(-5006,
			"Lỗi khi lưu thông tin nhãn công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_TAGS_WORD_OVER = new CodeMsg(-5007, "Nhãn công ty không được vượt quá 30 ký tự");
	public static CodeMsg COMPANY_NAME_AND_VALUE_PRIORITY = new CodeMsg(-5008,
			"Vui lòng điền tên công ty và giá trị công ty trước");
	public static CodeMsg COMPANY_PRODUCT_NAME_WORD_OVER = new CodeMsg(-5009,
			"Tên sản phẩm công ty không được vượt quá 11 ký tự");
	public static CodeMsg COMPANY_PRODUCT_SAVE_ERROR = new CodeMsg(-5010,
			"Lỗi khi lưu thông tin sản phẩm công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_INTRODUCTION_SAVE_ERROR = new CodeMsg(-5011,
			"Lỗi khi lưu thông tin giới thiệu công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_LOCALE_EMPTY = new CodeMsg(-5012, "Địa điểm công ty không được để trống");
	public static CodeMsg COMPANY_TERRITORY_EMPTY = new CodeMsg(-5013, "Lĩnh vực công ty không được để trống");
	public static CodeMsg COMPANY_URL_EMPTY = new CodeMsg(-5014, "Địa chỉ website công ty không được để trống");
	public static CodeMsg COMPANY_BASIC_SAVE_ERROR = new CodeMsg(-5015,
			"Lỗi khi lưu thông tin địa điểm, lĩnh vực, quy mô và website công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_SCALE_EMPTY = new CodeMsg(-5016, "Quy mô công ty không được để trống");
	public static CodeMsg COMPANY_FINANCE_SAVE_ERROR = new CodeMsg(-5017,
			"Lỗi khi lưu thông tin loại hình công ty công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_FOUNDER_SAVE_ERROR = new CodeMsg(-5018,
			"Lỗi khi lưu thông tin người sáng lập công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_FOUNDER_NAME_EMPTY = new CodeMsg(-5019,
			"Tên người sáng lập công ty không được để trống");
	public static CodeMsg COMPANY_FOUNDER_POSITION_EMPTY = new CodeMsg(-5020,
			"Chức vụ người sáng lập công ty không được để trống");
	public static CodeMsg COMPANY_FOUNDER_NAME_WORD_OVER = new CodeMsg(-5021,
			"Tên người sáng lập công ty không được vượt quá 10 ký tự");
	public static CodeMsg COMPANY_FOUNDER_POSITION_WORD_OVER = new CodeMsg(-5022,
			"Chức vụ người sáng lập công ty không được vượt quá 15 ký tự");
	public static CodeMsg COMPANY_APPLY_SAVE_ERROR = new CodeMsg(-5023,
			"Lỗi khi lưu thông tin xác thực công ty! Vui lòng liên hệ quản trị viên");
	public static CodeMsg COMPANY_CHANGE_STATE_ERROR = new CodeMsg(-5024,
			"Lỗi khi thay đổi trạng thái công ty! Vui lòng liên hệ quản trị viên");
}