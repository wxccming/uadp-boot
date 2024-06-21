package cn.iocoder.yudao.module.bookstore.controller.admin.bookqtcodeinfo.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 图书二维码信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BookQtcodeInfoRespVO {

    @Schema(description = "二维码编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8011")
    @ExcelProperty("二维码编号")
    private Long id;

    @Schema(description = "二维码名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("二维码名称")
    private String dtcodeName;

    @Schema(description = "所属项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "12345")
    @ExcelProperty("所属项目编号")
    private String itemId;

    @Schema(description = "二维码地址")
    @ExcelProperty("二维码地址")
    private String dtcodeAddress;

    @Schema(description = "二维码内容(图片流)")
    @ExcelProperty("二维码内容(图片流)")
    private String dtcodeContext;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "二维码分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("二维码分类")
    private String dtcodeCategory;

//    @Schema(description = "二维码编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "29804")
//    @ExcelProperty("二维码编号")
//    private String dtcodeId;

    @Schema(description = "章节编号", example = "13822")
    @ExcelProperty("章节编号")
    private Long chapterId;

    @Schema(description = "图书编号")
    @ExcelProperty("图书编号")
    private Long bookNo;

    @Schema(description = "资源信息")
    private List<SimpleBookQtcodeSourceVO> simpleBookQtcodeSourceVO;
}