package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.exception.NotImplementException;
import org.noear.solon.core.handle.Context;

import java.util.Objects;

/**
 * 基础控制器，异常处理
 */
public class ProviderBaseController {

    /**
     * 获取当前请求路径
     */
    public String getRequestPath() {
        Context ctx = Context.current();
        Class<?> declaringClz = ctx.action().method().getDeclaringClz();
        System.out.println(declaringClz.getName());
        return ctx.path();
    }

    public void checkService(Object service, String serviceName) {
        if (Objects.isNull(service)) {
            String msg = String.format("request path %s not implement with interface class %s", getRequestPath(), serviceName);
            throw new NotImplementException(msg);
        }
    }
}
