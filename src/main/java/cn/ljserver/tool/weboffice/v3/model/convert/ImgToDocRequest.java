package cn.ljserver.tool.weboffice.v3.model.convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Description;
import lombok.*;

import java.util.List;

/**
 * img转document请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImgToDocRequest {
    @NonNull
    @JsonProperty("img_urls")
    @Description("图片url集合")
    private String[] imgUrls;

    @JsonProperty("text_unify")
    @Description("统一段落字体字号，建议传true")
    @Builder.Default
    private boolean textUnify = true;

    @JsonProperty("sheet_option")
    @Description("转换为表格时配置：sheet转换方式 0表示每页pdf(每页图片)一个sheet, 1表示所有页面（图片）转到一个sheet中， 默认为0")
    @Builder.Default
    private Integer sheetOption = 0;

    public ImgToDocRequest(@NonNull String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

    public ImgToDocRequest(@NonNull List<String> imgUrls) {
        this.imgUrls = imgUrls.toArray(new String[]{});
    }
}
