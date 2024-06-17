package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 二维码资源新增/修改 Request VO")
@Data
public class BookQtcodeSourceSaveReqVO {

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "27060")
    private Long id;

//    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8404")
//    @NotEmpty(message = "资源编号不能为空")
//    private String sourceId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "资源名称不能为空")
    private String sourceName;

    @Schema(description = "资源形式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资源形式不能为空")
    private String sourceForm;

    @Schema(description = "适用场景")
    private String applicaScens;

    @Schema(description = "二维码编号", example = "1609")
    private String dtcodeId;

}