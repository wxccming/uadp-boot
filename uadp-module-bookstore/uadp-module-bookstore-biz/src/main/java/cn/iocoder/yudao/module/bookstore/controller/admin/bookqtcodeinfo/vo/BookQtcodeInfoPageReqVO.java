package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 图书二维码信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookQtcodeInfoPageReqVO extends PageParam {

    @Schema(description = "二维码名称", example = "王五")
    private String dtcodeName;

    @Schema(description = "所属项目编号", example = "12345")
    private String itemId;

    @Schema(description = "二维码地址")
    private String dtcodeAddress;

    @Schema(description = "二维码内容(图片流)")
    private String dtcodeContext;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "二维码分类")
    private String dtcodeCategory;
//
//    @Schema(description = "二维码编号", example = "29804")
//    private String dtcodeId;

    @Schema(description = "章节编号", example = "13822")
    private String chapterId;

    @Schema(description = "图书编号")
    private String bookNo;

}