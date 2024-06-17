package cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 图书章节 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BookChapterRespVO {

    @Schema(description = "章节编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13160")
    @ExcelProperty("章节编号")
    private Long id;

//    @Schema(description = "章节编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "4649")
//    @ExcelProperty("章节编号")
//    private String chapterId;

    @Schema(description = "章节名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("章节名称")
    private String chapterName;

    @Schema(description = "父节点ID(上级章节ID)章节名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "21395")
    @ExcelProperty("父节点ID(上级章节ID)章节名称")
    private String chapterPid;

    @Schema(description = "父章节名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("父章节名称")
    private String chapterPtitle;

    @Schema(description = "图书编号")
    @ExcelProperty("图书编号")
    private String bookNo;

    @Schema(description = "章节等级")
    @ExcelProperty("章节等级")
    private Long depth;

    @Schema(description = "是否最小节点", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否最小节点")
    private String isLeaf;

    @Schema(description = "排序")
    @ExcelProperty("排序")
    private String chapterSeq;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态")
    private String chapterStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}