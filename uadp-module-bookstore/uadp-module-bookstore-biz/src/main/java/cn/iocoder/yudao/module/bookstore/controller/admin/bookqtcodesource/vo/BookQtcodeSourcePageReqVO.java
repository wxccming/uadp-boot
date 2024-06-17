package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 二维码资源分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookQtcodeSourcePageReqVO extends PageParam {

    @Schema(description = "资源编号", example = "8404")
    private Long sourceId;

    @Schema(description = "资源名称", example = "李四")
    private String sourceName;

    @Schema(description = "资源形式")
    private String sourceForm;

    @Schema(description = "适用场景")
    private String applicaScens;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "二维码编号", example = "1609")
    private Long dtcodeId;

    @Schema(description = "章节编号", example = "13822")
    private Long chapterId;

    @Schema(description = "图书编号")
    private Long bookNo;
}