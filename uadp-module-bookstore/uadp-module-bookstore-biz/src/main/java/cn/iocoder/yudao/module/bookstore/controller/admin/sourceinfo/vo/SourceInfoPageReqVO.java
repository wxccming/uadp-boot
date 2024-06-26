package cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资源信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SourceInfoPageReqVO extends PageParam {

//    @Schema(description = "资源编号", example = "15044")
//    private String sourceId;

    @Schema(description = "资源名称", example = "张三")
    private String sourceName;

    @Schema(description = "资源形式")
    private String sourceForm;

    @Schema(description = "资源来源")
    private String sourceOrigin;

    @Schema(description = "资源地址", example = "https://www.iocoder.cn")
    private String sourceUrl;

    @Schema(description = "资源状态", example = "2")
    private String sourceStatus;

    @Schema(description = "学段")
    private String period;

    @Schema(description = "年级")
    private String grade;

    @Schema(description = "学科")
    private String subject;

    @Schema(description = "版本")
    private String edition;

    @Schema(description = "册次")
    private String volume;

    @Schema(description = "章节")
    private String chapter;

    @Schema(description = "知识编目")
    private String sourceKnnm;

    @Schema(description = "适用对象")
    private String applicaObjects;

    @Schema(description = "适用场景")
    private String applicaScens;

    @Schema(description = "格式")
    private String format;

    @Schema(description = "文件大小")
    private Integer size;

    @Schema(description = "大小单位")
    private String sizeUnit;

    @Schema(description = "标签")
    private String lableTag;

    @Schema(description = "备注")
    private String remaks;

    @Schema(description = "存储地址")
    private String storageAddress;

    @Schema(description = "备用字段1")
    private String spareFiled1;

    @Schema(description = "备用字段2")
    private String spareFiled2;

    @Schema(description = "上传用户(与创建人一致)")
    private Long uploadUserId;

    @Schema(description = "审核用户1", example = "7649")
    private String auditUser1Id;

    @Schema(description = "审核用户2", example = "5749")
    private String auditUser2Id;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "审核状态(0-未审核	|1-审核不通过|	2-审核通过)")
    private String auditStatus;

    @Schema(description = "资源类型(00-教案 | 01-课件 | 02-习题 | 03-素材 | 04-试卷 | 05-微课 | 06-拓展 | 07-其它")
    private String sourceCategory;

}