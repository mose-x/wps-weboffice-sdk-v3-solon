package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 用户不存在
 */
@Getter
public class FileTypeNotSupport extends ProviderException {
    private final int code = ErrorCodes.FileTypeNotSupport.getCode();

    public FileTypeNotSupport() {
        this(ErrorCodes.FileTypeNotSupport.name());
    }

    public FileTypeNotSupport(String message) {
        super(message);
    }
}
