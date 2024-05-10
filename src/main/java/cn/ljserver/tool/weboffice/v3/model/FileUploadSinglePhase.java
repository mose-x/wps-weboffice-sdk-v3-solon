package cn.ljserver.tool.weboffice.v3.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.noear.solon.core.handle.UploadedFile;

/**
 * 单阶段文件上传
 **/
public class FileUploadSinglePhase {
    @Data
    @NoArgsConstructor
    public static class Request {
        private String fileId;

        private UploadedFile file;

        private String name;

        private int size;

        private String sha1;

        private boolean isManual;

        private int attachmentSize;

        private String contentType;
    }
}
