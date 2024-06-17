package cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 图书章节分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookChapterPageReqVO extends PageParam {

//    @Schema(description = "章节编号", example = "4649")
//    private String chapterId;

    @Schema(description = "章节名称", example = "王五")
    private String chapterName;

    @Schema(description = "父节点ID(上级章节ID)章节名称", example = "21395")
    private String chapterPid;

    @Schema(description = "父章节名称")
    private String chapterPtitle;

    @Schema(description = "图书编号")
    private String bookNo;

    @Schema(description = "章节等级")
    private Long depth;

    @Schema(description = "是否最小节点")
    private String isLeaf;

    @Schema(description = "排序")
    private String chapterSeq;

    @Schema(description = "状态", example = "1")
    private String chapterStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}