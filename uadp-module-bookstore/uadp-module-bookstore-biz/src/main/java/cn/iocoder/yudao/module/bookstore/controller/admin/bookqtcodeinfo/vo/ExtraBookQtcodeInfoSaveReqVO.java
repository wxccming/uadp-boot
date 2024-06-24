package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 图书二维码信息(包含资源信息) Request VO")
@Data
public class ExtraBookQtcodeInfoSaveReqVO {

    @Schema(description = "二维码编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8011")
    private Long id;

    @Schema(description = "二维码名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "二维码名称不能为空")
    private String dtcodeName;

    @Schema(description = "所属项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "12345")
    @NotEmpty(message = "所属项目编号不能为空")
    private String itemId;

    @Schema(description = "二维码地址")
    private String dtcodeAddress;

    @Schema(description = "二维码内容(图片流)")
    private String dtcodeContext;

    @Schema(description = "二维码分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "二维码分类不能为空")
    private String dtcodeCategory;

    @Schema(description = "章节编号")
    private Long chapterId;

    @Schema(description = "图书编号")
    private Long bookNo;

    @Schema(description = "资源信息")
    private List<SimpleBookQtcodeSourceVO> simpleBookQtcodeSourceVO;

}