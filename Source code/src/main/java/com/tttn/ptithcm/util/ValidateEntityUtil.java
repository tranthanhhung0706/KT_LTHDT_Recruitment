package com.tttn.ptithcm.util;

import java.lang.reflect.Field;

import com.tttn.ptithcm.annotion.ValidateEntity;
import com.tttn.ptithcm.bean.CodeMsg;


public class ValidateEntityUtil {
	
	public static CodeMsg validate(Object object){
		Field[] declaredFields = object.getClass().getDeclaredFields();  //Tất cả các trường trong các đối tượng (không bao gồm các trường cha)
		//Đi qua tất cả các lĩnh vực
		for(Field field : declaredFields){
			ValidateEntity annotation = field.getAnnotation(ValidateEntity.class);   //Nhận bình luận Validatentity trên trường
			if(annotation != null){
				if(annotation.required()){
					//Nó có nghĩa là trường là một trường phải xảy ra
					field.setAccessible(true);    //Chức năng là cho phép chúng tôi truy cập các biến riêng tư khi được phản ánh
					try {
						Object o = field.get(object);   //Lấy ra giá trị của mỗi trường
						//Đầu tiên xác định xem nó có trống không
						if(o == null){
							CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
							codeMsg.setMsg(annotation.errorRequiredMsg());
							return codeMsg;
						}
						//Đối với điều này, nó có nghĩa là giá trị của biến không phải là null
						//Đánh giá đầu tiên xem đó là chuỗi
						if(o instanceof String){
							//Nếu đó là loại chuỗi, hãy kiểm tra độ dài của nó
							if(annotation.requiredMaxLength()){
								if(o.toString().length() < annotation.minLength()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMinLengthMsg());
									return codeMsg;
								}
							}
							if(annotation.requiredMinLength())
							{
								if(o.toString().length() > annotation.maxLength()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMaxLengthMsg());
									return codeMsg;
								}
							}
						}
						//Thứ hai, đánh giá xem đó có phải là một số
						if(isNumberObject(o)){
							//Xác định xem có nên kiểm tra giá trị tối thiểu hay không
							if(annotation.requiredMinValue()){
								if(Double.valueOf(o.toString()) < annotation.minValue()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMinValueMsg());
									return codeMsg;
								}
							}
							//Xác định xem có nên kiểm tra giá trị tối đa
							if(annotation.requiredMaxValue()){
								if(Double.valueOf(o.toString()) > annotation.maxValue()){
									CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
									codeMsg.setMsg(annotation.errorMaxValueMsg());
									return codeMsg;
								}
							}
						}
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return CodeMsg.SUCCESS;
	}
	

	public static boolean isNumberObject(Object object){
		if(object instanceof Integer)return true;
		if(object instanceof Long)return true;
		if(object instanceof Float)return true;
		if(object instanceof Double)return true;
		return false;
	}
}
