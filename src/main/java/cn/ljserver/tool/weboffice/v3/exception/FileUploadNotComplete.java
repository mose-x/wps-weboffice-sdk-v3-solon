package cn.ljserver.tool.weboffice.v3.exception;


import lombok.Getter;

/**
 * 文件未正确上传，例如保存文档时
 */
@Getter
public class FileUploadNotComplete extends ProviderException {
    private final int code = ErrorCodes.FileUploadNotComplete.getCode();

    public FileUploadNotComplete() {
        this(ErrorCodes.FileUploadNotComplete.name());
    }

    public FileUploadNotComplete(String message) {
        super(message);
    }
}
