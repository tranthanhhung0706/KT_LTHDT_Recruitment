package com.tttn.ptithcm.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) 
public @interface ValidateEntity {
	
	public boolean required() default false;
	
	public boolean requiredMaxLength() default false;
	
	public boolean requiredMinLength() default false;
	
	public boolean requiredMaxValue() default false;
	
	public boolean requiredMinValue() default false;
	
	
	
	
	public int maxLength() default -1;//Chiều dài tối đa
	
	public int minLength() default -1;//Chiều dài tối thiểu
	
	public long maxValue() default -1;//Giá trị cao
	
	public long minValue() default -1;//Tối thiểu
	
	
	
	
	
	public String errorRequiredMsg() default "";//Thông tin nhắc lỗi khi giá trị là null
	
	public String errorMinLengthMsg() default "";//Thông tin nhanh chóng khi độ dài tối thiểu không được thỏa mãn
	
	public String errorMaxLengthMsg() default "";//Thông tin nhanh chóng khi độ dài tối đa không được thỏa mãn
	
	public String errorMinValueMsg() default "";//Không có gì thông tin khi giá trị tối thiểu không được thỏa mãn
	
	public String errorMaxValueMsg() default "";//Thông tin nhanh chóng khi giá trị tối đa không được thỏa mãn
}
