package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.model.*;
import cn.ljserver.tool.weboffice.v3.service.ExtendCapacityService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.MethodType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 额外扩展功能 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/extend.html">wps web office 扩展能力</a>
 */
@Controller
@ProviderJsonApi
@Mapping("/v3/3rd/files")
public class ExtendCapacityController extends ProviderBaseController {

    @Inject(required = false)
    private ExtendCapacityService service;

    /**
     * 文件重命名
     *
     * @param fileId  文件id
     * @param request 请求参数 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#文档重命名">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/name", method = MethodType.PUT)
    public ProviderResponseEntity<Map<String, String>> fileRename(@Path("file_id") String fileId,
                                                                  @Body FileRenameRequest request) {
        checkService(service, "ExtendCapacityService");
        final String name = request.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidArgument("new filename is empty");
        }
        this.service.renameFile(fileId, name);
        return ProviderResponseEntity.ok(Collections.emptyMap());
    }

    /**
     * 文档历史版本列表
     *
     * @param fileId 文件id
     * @param offset 偏移量
     * @param limit  限制数量 <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#文档历史版本列表">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/versions", method = MethodType.GET)
    public ProviderResponseEntity<List<FileInfo>> fileVersions(@Path("file_id") String fileId,
                                                               @Param(value = "offset", required = false, defaultValue = "0") int offset,
                                                               @Param(value = "limit", required = false, defaultValue = "100") int limit) {
        checkService(service, "ExtendCapacityService");
        return ProviderResponseEntity.ok(this.service.fileVersions(fileId, offset, limit));
    }

    /**
     * 获取文档指定历史版本
     *
     * @param fileId  文件id
     * @param version 版本号 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#获取文档指定历史版本">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/versions/{version}", method = MethodType.GET)
    public ProviderResponseEntity<FileInfo> fileVersion(@Path("file_id") String fileId,
                                                        @Path("version") int version) {
        checkService(service, "ExtendCapacityService");
        return ProviderResponseEntity.ok(this.service.fileVersion(fileId, version));
    }

    /**
     * 获取历史版本下载信息
     *
     * @param fileId  文件id
     * @param version 版本号 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/extend.html#获取历史版本下载信息">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/versions/{version}/download", method = MethodType.GET)
    public ProviderResponseEntity<DownloadInfo> fileVersionDownload(@Path("file_id") String fileId,
                                                                    @Path("version") int version) {
        checkService(service, "ExtendCapacityService");
        return ProviderResponseEntity.ok(this.service.fileVersionDownload(fileId, version));
    }

    /**
     * 获取文件版本水印信息
     *
     * @param fileId 文件id <br>
     *               <a href = "https://solution.wps.cn/docs/callback/extend.html#文档水印">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/watermark", method = MethodType.GET)
    public ProviderResponseEntity<Watermark> fileWatermark(@Path("file_id") String fileId) {
        checkService(service, "ExtendCapacityService");
        return ProviderResponseEntity.ok(this.service.fileWatermark(fileId));
    }
}
