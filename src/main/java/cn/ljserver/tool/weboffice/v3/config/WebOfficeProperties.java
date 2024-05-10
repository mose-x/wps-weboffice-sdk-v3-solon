package cn.ljserver.tool.weboffice.v3.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * 开放平台配置对象
 */
@Data
@Configuration
@Inject(value = "${web-office}", required = false)
public class WebOfficeProperties {

    /**
     * 转换配置
     */
    private ConvertConfig convert;

    /**
     * 预览配置
     */
    private Config preview;

    @Data
    public static class Config {

        private String appid;
        private String secret;

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ConvertConfig extends Config {
        private String domain = "https://solution.wps.cn";
    }

}
