package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 系统错误导致的请求不能正常响应
 */
@Getter
public class InternalError extends ProviderException {
    private final int code = ErrorCodes.InternalError.getCode();

    public InternalError() {
        this(ErrorCodes.InternalError.name());
    }

    public InternalError(String message) {
        super(message);
    }
}
