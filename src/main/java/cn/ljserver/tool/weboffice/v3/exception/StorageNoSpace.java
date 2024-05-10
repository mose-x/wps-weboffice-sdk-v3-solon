package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

@Getter
public class StorageNoSpace extends ProviderException {
    private final int code = ErrorCodes.StorageNoSpace.getCode();

    public StorageNoSpace() {
        this(ErrorCodes.StorageNoSpace.name());
    }

    public StorageNoSpace(String message) {
        super(message);
    }
}
