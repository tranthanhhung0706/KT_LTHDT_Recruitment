package com.tttn.ptithcm.bean;

public class Result<T> {

  private int code;
	
	private String msg;
	
	private T data;
	
	
	private Result(CodeMsg codeMsg){
		if(codeMsg != null){
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}
	
	
	private Result(T data,CodeMsg codeMsg){
		if(codeMsg != null){
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
		this.data = data;
	} 
	

	public static <T>Result<T> success(T data){
		return new Result<T>(data,CodeMsg.SUCCESS);
	}

	
	public static <T>Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
