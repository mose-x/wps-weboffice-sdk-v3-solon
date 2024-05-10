package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CustomError extends ProviderException {
    private final int code = ErrorCodes.CustomError.getCode();

    public CustomError() {
        this(ErrorCodes.CustomError.name());
    }

    public CustomError(String message) {
        super(message);
    }
}
