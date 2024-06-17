package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 二维码资源 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BookQtcodeSourceRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("自增编号")
    private Long id;

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8404")
    @ExcelProperty("资源编号")
    private Long sourceId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("资源名称")
    private String sourceName;

    @Schema(description = "资源形式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源形式")
    private String sourceForm;

    @Schema(description = "适用场景")
    @ExcelProperty("适用场景")
    private String applicaScens;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "二维码编号", example = "1609")
    @ExcelProperty("二维码编号")
    private Long dtcodeId;

    @Schema(description = "章节编号", example = "13822")
    private Long chapterId;

    @Schema(description = "图书编号")
    private Long bookNo;
}