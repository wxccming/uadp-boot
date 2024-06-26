package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodesource.vo;

import cn.iocoder.yudao.module.infra.convert.Dict;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 二维码资源 Response VO")
@Data
@ExcelIgnoreUnannotated
public class H5BookQtcodeSourceRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("自增编号")
    private Long id;

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8404")
    @ExcelProperty("资源编号")
    private Long sourceId;

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("资源名称")
    private String sourceName;

    @Dict(dictTypeName = "sourceForm")
    @Schema(description = "资源形式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源形式")
    private String sourceForm;

    //@Dict(dictTypeName = "applicaScens")
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

    @Dict(dictTypeName = "sourceOrigin")
    @Schema(description = "资源来源")
    private String sourceOrigin;

    @Schema(description = "资源地址")
    private String sourceUrl;

    @Schema(description = "资源状态")
    @Dict(dictTypeName = "sourceStatus")
    private String sourceStatus;

    @Schema(description = "学段")
    @Dict(dictTypeName = "period")
    private String period;

    @Schema(description = "年级")
    @Dict(dictTypeName = "grade")
    private String grade;

    @Schema(description = "学科")
    @Dict(dictTypeName = "sudject")
    private String subject;

    @Schema(description = "版本")
    @Dict(dictTypeName = "edition")
    private String edition;

    @Schema(description = "册次")
    @Dict(dictTypeName = "volume")
    private String volume;

    @Schema(description = "章节")
    private String chapter;

    @Schema(description = "知识编目")
    private String sourceKnnm;

    @Dict(dictTypeName = "applicaObjects")
    @Schema(description = "适用对象")
    private String applicaObjects;

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
    private String uploadUserId;

    @Schema(description = "审核用户1")
    private String auditUser1Id;

    @Schema(description = "审核用户2")
    private String auditUser2Id;

    @Dict(dictTypeName = "auditStatus")
    @Schema(description = "审核状态")
    private String auditStatus;

    @Dict(dictTypeName = "sourceCategory")
    @Schema(description = "资源类型(00-教案 | 01-课件 | 02-习题 | 03-素材 | 04-试卷 | 05-微课 | 06-拓展 | 07-其它")
    private String sourceCategory;

    @Schema(description = "图书名称")
    private String bookName;

    @Schema(description = "章节名称")
    private String chapterName;

}