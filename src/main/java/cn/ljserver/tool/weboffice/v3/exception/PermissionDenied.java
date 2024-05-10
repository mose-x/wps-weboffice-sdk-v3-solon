package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 用户操作权限不足
 */
@Getter
public class PermissionDenied extends ProviderException {
    private final int code = ErrorCodes.PermissionDenied.getCode();

    public PermissionDenied() {
        this(ErrorCodes.PermissionDenied.name());
    }

    public PermissionDenied(String message) {
        super(message);
    }
}
