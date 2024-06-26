package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 图书二维码信息新增/修改 Request VO")
@Data
public class BookQtcodeInfoSaveReqVO {

    @Schema(description = "二维码编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8011")
    private Long id;

    @Schema(description = "二维码名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "二维码名称不能为空")
    private String dtcodeName;

    @Schema(description = "所属项目编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long itemId;

    @Schema(description = "二维码地址")
    private String dtcodeAddress;

    @Schema(description = "二维码内容(图片流)")
    private String dtcodeContext;

    @Schema(description = "二维码分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "二维码分类不能为空")
    private String dtcodeCategory;

//    @Schema(description = "二维码编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "29804")
//    @NotEmpty(message = "二维码编号不能为空")
//    private String dtcodeId;

    @Schema(description = "章节编号", example = "13822")
    private Long chapterId;

    @Schema(description = "图书编号")
    private Long bookNo;

}