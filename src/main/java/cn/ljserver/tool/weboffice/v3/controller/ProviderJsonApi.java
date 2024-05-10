package cn.ljserver.tool.weboffice.v3.controller;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProviderJsonApi {
}
