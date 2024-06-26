package cn.iocoder.yudao.module.bookstore.controller.admin.bookinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

@Schema(description = "管理后台 - 图书信息新增/修改 Request VO")
@Data
public class BookInfoSaveReqVO {

    @Schema(description = "图书编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "19979")
    private Long id;

//    @Schema(description = "图书编号", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "图书编号不能为空")
//    private String bookNo;

    @Schema(description = "图书名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "唐诗三百首")
    @NotEmpty(message = "图书名称不能为空")
    private String bookName;

    @Schema(description = "图书审定年份")
    private String auditedYear;

    @Schema(description = "图书分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "图书分类不能为空")
    private String bookCategory;

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

    @Schema(description = "出版单位")
    private String publishUnit;

    @Schema(description = "图书状态", example = "1")
    private String bookStatus;

    @Schema(description = "排序")
    private String bookSeq;

    @Schema(description = "简介")
    private String intro;

    @Schema(description = "图书封面")
    private String bookPic;

    @Schema(description = "备用字段1")
    private String spareFiled1;

    @Schema(description = "备用字段2")
    private String spareFiled2;

    @Schema(description = "图书地址")
    private String bookAddress;

    @Schema(description = "图书Base64内容")
    private String bookBase;

}