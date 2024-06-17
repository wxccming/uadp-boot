package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeitem.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 二维码项目分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookQtcodeItemPageReqVO extends PageParam {

//    @Schema(description = "项目编号", example = "32141")
//    private String itemId;

    @Schema(description = "项目名称", example = "王五")
    private String itemName;

    @Schema(description = "资源来源")
    private String sourceOrigin;

    @Schema(description = "图书编号")
    private Long bookNo;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "状态", example = "2")
    private String dtcodeStatus;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}