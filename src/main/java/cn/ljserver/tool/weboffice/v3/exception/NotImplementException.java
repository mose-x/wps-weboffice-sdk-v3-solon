package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 接口未实现异常
 */
@Getter
public class NotImplementException extends ProviderException {
    // a special error code only works with provider sdk
    private final int code = ErrorCodes.NotImplementException.getCode();

    public NotImplementException() {
        super(ErrorCodes.NotImplementException.name());
    }

    public NotImplementException(String message) {
        super(message);
    }
}
