package net.kkl.billingsystem.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ParamValidException extends RuntimeException {
	private String code;
	private String msg;
}
