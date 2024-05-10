package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.DownloadInfo;
import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.model.UserPermission;
import cn.ljserver.tool.weboffice.v3.service.PreviewService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Path;
import org.noear.solon.core.handle.MethodType;

/**
 * 文档预览 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/preview.html">wps web office 文档预览</a>
 */
@Controller
@ProviderJsonApi
@Mapping("/v3/3rd/files")
public class PreviewController extends ProviderBaseController {

    @Inject(required = false)
    private PreviewService service;

    /**
     * 获取文件信息
     *
     * @param fileId 文件id <br>
     *               <a href="https://solution.wps.cn/docs/callback/preview.html#获取文件信息">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}", method = MethodType.GET)
    public ProviderResponseEntity<FileInfo> fetchFile(@Path("file_id") final String fileId) {
        checkService(service, "PreviewService");
        return ProviderResponseEntity.ok(this.service.fetchFileInfo(fileId));
    }

    /**
     * 获取下载信息
     *
     * @param fileId 文件id <br>
     *               <a href="https://solution.wps.cn/docs/callback/preview.html#获取文件下载地址">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/download", method = MethodType.GET)
    public ProviderResponseEntity<DownloadInfo> fetchDownloadInfo(@Path("file_id") final String fileId) {
        checkService(service, "PreviewService");
        return ProviderResponseEntity.ok(this.service.fetchDownloadInfo(fileId));
    }

    /**
     * 获取文档用户权限
     *
     * @param fileId 文件id <br>
     *               <a href="https://solution.wps.cn/docs/callback/preview.html#文档用户权限">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/permission", method = MethodType.GET)
    public ProviderResponseEntity<UserPermission> fetchUserPermission(@Path("file_id") final String fileId) {
        checkService(service, "PreviewService");
        return ProviderResponseEntity.ok(this.service.fetchUserPermission(fileId));
    }
}
