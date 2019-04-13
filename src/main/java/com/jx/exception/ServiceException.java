package com.jx.exception;

/**细化运行时异常,自己定义一个业务异常*/
public class ServiceException extends RuntimeException {
	private int code;
	public ServiceException() {
		super();
	}
	public ServiceException(int code,String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code=code;
	}
	public ServiceException(int code,String message, Throwable cause) {
		super(message, cause);
		this.code=code;
		// TODO Auto-generated constructor stub
	}

	public ServiceException(int code,String message) {
		super(message);
		this.code=code;
		// TODO Auto-generated constructor stub
	}

	public ServiceException(int code,Throwable cause) {
		super(cause);
		this.code=code;
		// TODO Auto-generated constructor stub
	}

}
