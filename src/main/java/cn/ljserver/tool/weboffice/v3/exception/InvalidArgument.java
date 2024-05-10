package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 请求参数错误
 */
@Getter
public class InvalidArgument extends ProviderException {
    private final int code = ErrorCodes.InvalidArgument.getCode();

    public InvalidArgument() {
        this(ErrorCodes.InvalidArgument.name());
    }

    public InvalidArgument(String message) {
        super(message);
    }
}
