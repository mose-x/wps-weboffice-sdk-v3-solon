package cn.ljserver.tool.weboffice.v3.exception;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * err msg
 */
public class CustomErrorMsg {

    @JsonValue
    public static String e_404() {
        return "{\"code\":" + 404 + ",\"message\":\"" + "request not fond！" + "\"}";
    }

    @JsonValue
    public static String e_500() {
        return "{\"code\":" + 500 + ",\"message\":\"" + "server err！" + "\"}";
    }
}
