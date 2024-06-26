package cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资源审核记录新增/修改 Request VO")
@Data
public class SourceauditHistorySaveReqVO {

    @Schema(description = "自增编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "7088")
    private Long id;

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "7523")
    @NotNull(message = "资源编号不能为空")
    private Long sourceId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "资源名称不能为空")
    private String sourceName;

    @Schema(description = "审核人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "审核人不能为空")
    private String auditor;

    @Schema(description = "审核时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "审核时间不能为空")
    private LocalDateTime auditTime;

    @Schema(description = "审核状态", example = "2")
    private String auditStatus;

    @Schema(description = "审核意见")
    private String auditMsg;

}