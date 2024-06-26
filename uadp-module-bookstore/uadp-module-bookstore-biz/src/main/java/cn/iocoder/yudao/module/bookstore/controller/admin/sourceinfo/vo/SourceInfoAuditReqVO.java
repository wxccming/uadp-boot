package cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 资源信息新增/修改 Request VO")
@Data
public class SourceInfoAuditReqVO {

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8528")
    private Long id;

    @Schema(description = "审核人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "审核人不能为空")
    private String auditor;

    @Schema(description = "审核状态(0-未审核|1-审核不通过|2-审核通过)" , requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "审核状态不能为空")
    private String auditStatus;

    @Schema(description = "审核意见")
    private String auditMsg;

}