package cn.iocoder.yudao.module.bookstore.controller.admin.sourceaudithistory.vo;

import cn.iocoder.yudao.module.infra.convert.UserId;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资源审核记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SourceauditHistoryRespVO {

    @Schema(description = "自增编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "7088")
    @ExcelProperty("自增编号")
    private Long id;

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "7523")
    @ExcelProperty("资源编号")
    private Long sourceId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("资源名称")
    private String sourceName;

    @UserId
    @Schema(description = "审核人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("审核人")
    private String auditor;

    @Schema(description = "审核时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核状态", example = "2")
    @ExcelProperty("审核状态")
    private String auditStatus;

    @Schema(description = "审核意见")
    @ExcelProperty("审核意见")
    private String auditMsg;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}