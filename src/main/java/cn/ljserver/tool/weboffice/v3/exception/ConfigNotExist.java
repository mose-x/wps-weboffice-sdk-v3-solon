package cn.ljserver.tool.weboffice.v3.exception;

import lombok.Getter;

@Getter
public class ConfigNotExist extends ProviderException {
    private final int code = ErrorCodes.ConfigNotExist.getCode();

    public ConfigNotExist() {
        this(ErrorCodes.ConfigNotExist.name());
    }

    public ConfigNotExist(String message) {
        super(message);
    }
}