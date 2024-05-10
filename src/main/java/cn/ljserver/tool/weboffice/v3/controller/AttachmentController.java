package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.ExtendCapacityService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.MethodType;
import org.noear.solon.core.handle.UploadedFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 附件对象相关接口 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/extend.html#%E9%99%84%E4%BB%B6%E5%AF%B9%E8%B1%A1%E7%9B%B8%E5%85%B3%E6%8E%A5%E5%8F%A3">-官方文档-</a>
 */
@Controller
@ProviderJsonApi
@Mapping("/v3/3rd/object")
public class AttachmentController extends ProviderBaseController {

    @Inject(required = false)
    private ExtendCapacityService service;

    @Mapping(value = "/{key}", method = MethodType.PUT)
    public ProviderResponseEntity<?> uploadAttachment(@Path String key,
                                                      @Param String name,
                                                      @Body UploadedFile files) {
        checkService(service, "ExtendCapacityService");
        this.service.uploadAttachment(key, name, files);
        return ProviderResponseEntity.ok("");
    }

    @Mapping(value = "/{key}/url", method = MethodType.GET)
    public ProviderResponseEntity<?> getAttachment(@Path String key,
                                                   @Param int scale_max_fit_width,
                                                   @Param int scale_max_fit_height,
                                                   @Param int scale_long_edge) {
        checkService(service, "ExtendCapacityService");
        Map<String, String> res = new HashMap<>();
        String url = this.service.getAttachment(key, scale_max_fit_width, scale_max_fit_height, scale_long_edge);
        res.put("url", url);
        return ProviderResponseEntity.ok(res);
    }

    @Mapping(value = "/copy", method = MethodType.POST)
    public ProviderResponseEntity<?> copyAttachment(@Body Map<String, String> keyDict) {
        checkService(service, "ExtendCapacityService");
        this.service.copyAttachment(keyDict);
        return ProviderResponseEntity.ok();
    }
}
