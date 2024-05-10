package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.Notify;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.ExtendCapacityService;
import org.noear.solon.annotation.Body;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.MethodType;

/**
 * 事件通知 -> 详见<br>
 * <a href = "https://solution.wps.cn/docs/callback/extend.html#%E4%BA%8B%E4%BB%B6%E9%80%9A%E7%9F%A5">-详见官方文档-</a>
 */
@Controller
@ProviderJsonApi
@Mapping("/v3/3rd/notify")
public class NotifyController extends ProviderBaseController {

    @Inject(required = false)
    private ExtendCapacityService service;

    /**
     * 事件通知 -> 详见<br>
     * <a href = "https://solution.wps.cn/docs/callback/extend.html#%E4%BA%8B%E4%BB%B6%E9%80%9A%E7%9F%A5">-详见官方文档-</a>
     */
    @Mapping(method = MethodType.POST)
    public ProviderResponseEntity<?> notify(@Body Notify notify) {
        checkService(service, "ExtendCapacityService");
        this.service.notify(notify);
        return ProviderResponseEntity.ok();
    }
}
