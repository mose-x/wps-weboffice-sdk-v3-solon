package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 用户不存在
 */
@Getter
public class UserNotExist extends ProviderException {
    private final int code = ErrorCodes.UserNotExist.getCode();

    public UserNotExist() {
        this(ErrorCodes.UserNotExist.name());
    }

    public UserNotExist(String message) {
        super(message);
    }
}
