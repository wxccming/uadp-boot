package cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资源信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SourceInfoRespVO {

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8528")
    @ExcelProperty("资源编号")
    private Long id;

//    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "15044")
//    @ExcelProperty("资源编号")
//    private String sourceId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("资源名称")
    private String sourceName;

    @Schema(description = "资源形式")
    @ExcelProperty("资源形式")
    private String sourceForm;

    @Schema(description = "资源来源")
    @ExcelProperty("资源来源")
    private String sourceOrigin;

    @Schema(description = "资源地址", example = "https://www.iocoder.cn")
    @ExcelProperty("资源地址")
    private String sourceUrl;

    @Schema(description = "资源状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("资源状态")
    private String sourceStatus;

    @Schema(description = "学段")
    @ExcelProperty("学段")
    private String period;

    @Schema(description = "年级")
    @ExcelProperty("年级")
    private String grade;

    @Schema(description = "学科")
    @ExcelProperty("学科")
    private String subject;

    @Schema(description = "版本")
    @ExcelProperty("版本")
    private String edition;

    @Schema(description = "册次")
    @ExcelProperty("册次")
    private String volume;

    @Schema(description = "章节")
    @ExcelProperty("章节")
    private String chapter;

    @Schema(description = "知识编目")
    @ExcelProperty("知识编目")
    private String sourceKnnm;

    @Schema(description = "适用对象")
    @ExcelProperty("适用对象")
    private String applicaObjects;

    @Schema(description = "适用场景")
    @ExcelProperty("适用场景")
    private String applicaScens;

    @Schema(description = "格式")
    @ExcelProperty("格式")
    private String format;

    @Schema(description = "文件大小")
    @ExcelProperty("文件大小")
    private Integer size;

    @Schema(description = "大小单位")
    @ExcelProperty("大小单位")
    private String sizeUnit;

    @Schema(description = "标签")
    @ExcelProperty("标签")
    private String lableTag;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remaks;

    @Schema(description = "存储地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("存储地址")
    private String storageAddress;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String spareFiled1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String spareFiled2;

    @Schema(description = "上传用户(与创建人一致)", example = "6099")
    @ExcelProperty("上传用户(与创建人一致)")
    private String uploadUserId;

    @Schema(description = "审核用户1", example = "7649")
    @ExcelProperty("审核用户1")
    private String auditUser1Id;

    @Schema(description = "审核用户2", example = "5749")
    @ExcelProperty("审核用户2")
    private String auditUser2Id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "审核状态(0-未审核	|1-审核不通过|	2-审核通过)")
    @ExcelProperty("审核状态(0-未审核	|1-审核不通过|	2-审核通过)")
    private String auditStatus;

    @Schema(description = "资源类型(00-教案 | 01-课件 | 02-习题 | 03-素材 | 04-试卷 | 05-微课 | 06-拓展 | 07-其它")
    @ExcelProperty("资源类型(00-教案 | 01-课件 | 02-习题 | 03-素材 | 04-试卷 | 05-微课 | 06-拓展 | 07-其它")
    private String sourceCategory;

}