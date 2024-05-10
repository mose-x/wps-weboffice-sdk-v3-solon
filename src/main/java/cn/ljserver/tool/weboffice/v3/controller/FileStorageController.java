package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.FileInfo;
import cn.ljserver.tool.weboffice.v3.model.FileUploadMultiPhase;
import cn.ljserver.tool.weboffice.v3.model.FileUploadSinglePhase;
import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.service.MultiPhaseFileStorageService;
import cn.ljserver.tool.weboffice.v3.service.SinglePhaseFileStorageService;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.MethodType;

import java.util.Collections;
import java.util.Map;


/**
 * 文档编辑 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/save.html">wps web office 文档编辑</a>
 */
@Controller
@ProviderJsonApi
@Mapping("/v3/3rd/files")
public class FileStorageController extends ProviderBaseController {

    @Inject(required = false)
    private MultiPhaseFileStorageService multiPhase;
    @Inject(required = false)
    private SinglePhaseFileStorageService singlePhase;

    /**
     * 三阶段保存 - 准备上传阶段
     *
     * @param fileId 文件id <br>
     *               <a href = "https://solution.wps.cn/docs/callback/save.html#准备上传阶段">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/upload/prepare", method = MethodType.GET)
    public ProviderResponseEntity<Map<String, Object>> uploadPrepare(@Path("file_id") String fileId) {
        checkService(multiPhase, "MultiPhaseFileStorageService");
        return ProviderResponseEntity.ok(Collections
                .singletonMap("digest_types", this.multiPhase.uploadPrepare(fileId)));
    }

    /**
     * 三阶段保存 - 获取上传地址
     *
     * @param fileId  文件id
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#获取上传地址">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/upload/address", method = MethodType.POST)
    public ProviderResponseEntity<FileUploadMultiPhase.FileUploadAddress.Response> uploadAddress(@Path("file_id") String fileId,
                                                                                                 @Body FileUploadMultiPhase.FileUploadAddress.Request request) {
        checkService(multiPhase, "MultiPhaseFileStorageService");
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.multiPhase.uploadAddress(request));
    }

    /**
     * 三阶段保存 - 上传完成后，回调通知上传结果
     *
     * @param fileId  文件id
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#上传完成后，回调通知上传结果">-详见官方文档-</a>
     */
    @Mapping(value = "/{file_id}/upload/complete", method = MethodType.POST)
    public ProviderResponseEntity<FileInfo> uploadComplete(@Path("file_id") String fileId,
                                                           @Body FileUploadMultiPhase.FileUploadComplete.Request request) {
        checkService(multiPhase, "MultiPhaseFileStorageService");
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.multiPhase.uploadComplete(request));
    }

    /**
     * 单阶段保存 - 上传文件
     *
     * @param fileId  文件id
     * @param request 上传的文件 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/save.html#单阶段提交">-详见官方文档-</a>
     */
    // @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Mapping(value = "/{file_id}/upload", method = MethodType.POST)
    public ProviderResponseEntity<FileInfo> uploadFile(@Path("file_id") String fileId,
                                                       @Param FileUploadSinglePhase.Request request) {
        checkService(singlePhase, "SinglePhaseFileStorageService");
        request.setFileId(fileId);
        return ProviderResponseEntity.ok(this.singlePhase.uploadFile(request));
    }
}
