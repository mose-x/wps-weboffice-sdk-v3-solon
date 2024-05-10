package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 文件不存在
 */
@Getter
public class FileNotExist extends ProviderException {
    private final int code = ErrorCodes.FileNotExist.getCode();

    public FileNotExist() {
        this(ErrorCodes.FileNotExist.name());
    }

    public FileNotExist(String message) {
        super(message);
    }
}
