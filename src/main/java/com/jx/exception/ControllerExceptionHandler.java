package com.jx.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx.entity.Employee;
import com.jx.entity.MessageResult;


/**全局异常处理对象:
 * 希望通过此类实现所有Controller中的异常处理
 * @ControllerAdvice 声明的类可以作为统一异常处理对象
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public MessageResult handleServiceException(ServiceException e){
		e.printStackTrace();
		return new MessageResult(e);
	}
}
