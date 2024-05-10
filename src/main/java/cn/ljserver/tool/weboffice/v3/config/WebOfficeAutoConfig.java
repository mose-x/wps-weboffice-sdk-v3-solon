package cn.ljserver.tool.weboffice.v3.config;


import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.bean.LifecycleBean;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自动配置，不需要接入端 ComponentScan
 */
@Configuration
public class WebOfficeAutoConfig implements LifecycleBean {

    @Inject
    private WebOfficeProperties webOfficeProperties;

    /**
     * 启动验证配置
     */
    @Override
    public void start() {
        Logger log = Logger.getLogger(this.getClass().getName());
        if (webOfficeProperties.getConvert() == null) {
            log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.convert' is null.");
        } else {
            if (webOfficeProperties.getConvert().getAppid() == null || webOfficeProperties.getConvert().getAppid().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.convert.appid' is null or empty.");
            }
            if (webOfficeProperties.getConvert().getSecret() == null || webOfficeProperties.getConvert().getSecret().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.convert.secret' is null or empty.");
            }
        }

        if (webOfficeProperties.getPreview() == null) {
            log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.preview' is null.");
        } else {
            if (webOfficeProperties.getPreview().getAppid() == null || webOfficeProperties.getPreview().getAppid().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.preview.appid' is null or empty.");
            }
            if (webOfficeProperties.getPreview().getSecret() == null || webOfficeProperties.getPreview().getSecret().isEmpty()) {
                log.log(Level.WARNING, "web-office-v3: WARNING: Required application property 'web-office.preview.secret' is null or empty.");
            }
        }
    }
}
