package cn.iocoder.yudao.module.bookstore.controller.admin.bookchapter.vo;

import cn.iocoder.yudao.module.infra.convert.Dict;
import cn.iocoder.yudao.module.infra.convert.User;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "H5管理后台 - 图书信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class H5BookRespVO {

    @Schema(description = "图书编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "19979")
    @ExcelProperty("图书编号")
    private Long id;

    @Schema(description = "图书名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "唐诗三百首")
    @ExcelProperty("图书名称")
    private String bookName;

    @Schema(description = "图书审定年份")
    @ExcelProperty("图书审定年份")
    private String auditedYear;

    @Dict(dictTypeName = "bookCategory")
    @Schema(description = "图书分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("图书分类")
    private String bookCategory;

    @Dict(dictTypeName = "period")
    @Schema(description = "学段")
    @ExcelProperty("学段")
    private String period;

    @Dict(dictTypeName = "grade")
    @Schema(description = "年级")
    @ExcelProperty("年级")
    private String grade;

    @Dict(dictTypeName = "sudject")
    @Schema(description = "学科")
    @ExcelProperty("学科")
    private String subject;

    @Dict(dictTypeName = "edition")
    @Schema(description = "版本")
    @ExcelProperty("版本")
    private String edition;

    @Dict(dictTypeName = "volume")
    @Schema(description = "册次")
    @ExcelProperty("册次")
    private String volume;

    @User(type="dept_id")
    @Schema(description = "出版单位")
    @ExcelProperty("出版单位")
    private String publishUnit;

    @Dict(dictTypeName = "bookStatus")
    @Schema(description = "图书状态", example = "1")
    @ExcelProperty("图书状态")
    private String bookStatus;

    @Schema(description = "简介")
    @ExcelProperty("简介")
    private String intro;

    @Schema(description = "图书封面")
    @ExcelProperty("图书封面")
    private String bookPic;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String spareFiled1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String spareFiled2;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}