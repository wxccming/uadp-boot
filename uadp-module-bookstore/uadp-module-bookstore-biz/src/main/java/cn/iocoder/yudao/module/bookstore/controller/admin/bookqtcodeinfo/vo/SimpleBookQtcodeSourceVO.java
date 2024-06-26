package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Data
public class SimpleBookQtcodeSourceVO {

    @Schema(description = "自增编号")
    private Long id;

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long sourceId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "资源名称不能为空")
    private String sourceName;

    @Schema(description = "资源形式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资源形式不能为空")
    private String sourceForm;

    @Schema(description = "适用场景")
    private String applicaScens;
}