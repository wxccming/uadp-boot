package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 二维码项目 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BookQtcodeItemRespVO {

    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "24857")
    @ExcelProperty("项目编号")
    private Long id;

//    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "32141")
//    @ExcelProperty("项目编号")
//    private String itemId;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("项目名称")
    private String itemName;

    @Schema(description = "资源来源")
    @ExcelProperty("资源来源")
    private String sourceOrigin;

    @Schema(description = "图书编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("图书编号")
    private Long bookNo;

    @Schema(description = "图书名称")
    @ExcelProperty("图书名称")
    private String bookName;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remarks;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
    private String dtcodeStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}