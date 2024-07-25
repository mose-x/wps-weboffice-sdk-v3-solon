package cn.ljserver.tool.weboffice.v3.plugin;

import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * 插件
 */
public class WebOfficePluginImpl implements Plugin {
    @Override
    public void start(AppContext context) throws Throwable {
        context.beanScan("cn.ljserver.tool.weboffice.v3");
    }
}
