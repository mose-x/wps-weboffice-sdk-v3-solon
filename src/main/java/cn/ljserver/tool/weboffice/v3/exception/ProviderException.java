package cn.ljserver.tool.weboffice.v3.exception;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 错误信息抽象层
 */
public abstract class ProviderException extends RuntimeException {
    public ProviderException(String message) {
        super(message);
    }

    public abstract int getCode();

    @JsonValue
    public String jsonResult() {
        return "{\"code\":" + getCode() + ",\"message\":\"" + getMessage() + "\"}";
    }

}
