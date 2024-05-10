package cn.ljserver.tool.weboffice.v3.model;

import cn.ljserver.tool.weboffice.v3.exception.ConvertErrorCodes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 文件模板
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileTemplateResponse {

    /**
     * 响应状态码
     */
    @JsonProperty("code")
    private int code = ConvertErrorCodes.Unknown.getCode();

    /**
     * 响应数据
     */
    @JsonProperty("data")
    private FileTemplateInfo data = null;
}
