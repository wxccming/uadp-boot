package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 二维码项目新增/修改 Request VO")
@Data
public class BookQtcodeItemSaveReqVO {

    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "24857")
    private Long id;

//    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "32141")
//    @NotEmpty(message = "项目编号不能为空")
//    private String itemId;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "项目名称不能为空")
    private String itemName;

    @Schema(description = "资源来源")
    private String sourceOrigin;

    @Schema(description = "图书编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long bookNo;

    @Schema(description = "图书名称")
    private String bookName;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "状态不能为空")
    private String dtcodeStatus;

}