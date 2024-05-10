package cn.ljserver.tool.weboffice.v3.model.convert;

import cn.ljserver.tool.weboffice.v3.util.FileUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Description;
import lombok.*;

/**
 * 文档转换为IMG图片请求对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocToImgRequest {
    @NonNull
    @JsonProperty("url")
    @Description("文档下载地址")
    public String url;

    @NonNull
    @JsonProperty("filename")
    @Description("文档名称，包含扩展名，例如： 文字文稿.docx")
    public String filename;

    @JsonProperty("password")
    @Description("文档打开密码(如果文档有加密，该项则必填)")
    public String password;

    @JsonProperty("long_pic")
    @Description("是否转换成单张长图，设置为 true 时，最多仅支持将 20 标准页面合成单张长图，超过可能会报错")
    @Builder.Default
    private boolean longPic = false;

    @JsonProperty("image_dpi")
    @Description("按指定 dpi 渲染图片，该参数与 scale 共同作用，取值范围 96-600。默认值为 96。")
    @Builder.Default
    private int image_dpi = 96;

    @JsonProperty("scale")
    @Description("缩放参数，允许范围是 20-200，100 表示不缩放，小于 100 表示是缩小，大于 100 表示是放大。 默认值为 100")
    @Builder.Default
    private int scale = 100;

    @JsonProperty("quality")
    @Description("质量参数，范围是 0-100，越大表示质量越好")
    @Builder.Default
    private int quality = 20;

    @JsonProperty("show_comments")
    @Description("是否显示批注。默认值为 false，不显示批注")
    @Builder.Default
    private boolean show_comments = false;

    @JsonProperty("fit_to_width")
    @Description("值为 1 表示将所有列放到 一 页中进行排版，默认值为 0 (该字段只在 ET 表格中生效)")
    @Builder.Default
    private int fit_to_width = 0;

    @JsonProperty("fit_to_height")
    @Description("值为 1 表示将所有行放到 一 页进行排版，默认值为 0 (该字段只在 ET 表格中生效)")
    @Builder.Default
    private int fit_to_height = 0;

    @JsonProperty("is_horizontal")
    @Description("值为 true 表示纸张是水平放置。默认值为 false，纸张垂直放置 (该字段只在 ET 表格中生效)")
    @Builder.Default
    private boolean is_horizontal = false;

    @JsonProperty("paper_size")
    @Description("设置纸张（画布）大小，对应信息为：0 → A4; 1 → A2; 2 → A0。默认 A4 纸张 (该字段只在 ET 表格中生效)")
    @Builder.Default
    private int paper_size = 0;

    @JsonProperty("ranges")
    @Description("自定义需要转换的分页范围，例如：1,2-4,7，则表示转换文档的 1、2、4、7 页面 (与 from_page和to_page 互斥)")
    private String ranges;

    @JsonProperty("from_page")
    @Description("转换起始页，从 1 开始计数(与 ranges互斥)")
    private Integer fromPage;

    @JsonProperty("to_page")
    @Description("转换结束页，需要大于 from_page， (与 ranges互斥)")
    private Integer toPage;

    public DocToImgRequest(@NonNull String url) {
        this.url = url;
        this.filename = FileUtils.name(url);
    }

    public DocToImgRequest(@NonNull String url, @NonNull String filename) {
        this.url = url;
        this.filename = filename;
    }

    public DocToImgRequest(@NonNull String url, @NonNull String filename, String password) {
        this.url = url;
        this.filename = filename;
        this.password = password;
    }
}
