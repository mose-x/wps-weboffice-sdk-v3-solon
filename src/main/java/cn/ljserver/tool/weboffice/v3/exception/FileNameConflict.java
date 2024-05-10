package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

/**
 * 文档名称冲突，例如重命名文档时
 */
@Getter
public class FileNameConflict extends ProviderException {
    private final int code = ErrorCodes.FileNameConflict.getCode();

    public FileNameConflict() {
        this(ErrorCodes.FileNameConflict.name());
    }

    public FileNameConflict(String message) {
        super(message);
    }
}
