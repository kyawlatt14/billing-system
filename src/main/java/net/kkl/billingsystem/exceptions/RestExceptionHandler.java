package net.kkl.billingsystem.exceptions;

import lombok.extern.slf4j.Slf4j;
import net.kkl.billingsystem.common.responses.AllResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ApplicationErrorException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public AllResponse handleBizException(ApplicationErrorException e) {
		String msg = "biz exception";
		if (e != null) {
			msg = e.getMsg();
			log.warn(e.toString());
		}
		return AllResponse.fail(msg);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public AllResponse handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> allMap = new HashMap<>();
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
		allMap.put("errorMsg", errorMap.toString());
		log.error("Param is required!- {}",allMap);
		return AllResponse.fail("Param is required!",allMap);
	}
}
