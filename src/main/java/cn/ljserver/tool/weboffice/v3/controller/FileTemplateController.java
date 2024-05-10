package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.FileTemplateService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Path;
import org.noear.solon.core.handle.MethodType;


/**
 * 文件模板 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/developer/files/get-template.html">wps web office 文件模板</a>
 */
@Controller
@ProviderJsonApi
@Mapping("/v3/files/template")
public class FileTemplateController {

    @Mapping(value = "/{officeType}", method = MethodType.GET)
    public ProviderResponseEntity<?> fileTemplate(@Path("officeType") String officeType) {
        return FileTemplateService.getFileTemplateResponse(officeType);
    }

}
