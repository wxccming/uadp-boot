package cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 资源信息新增/修改 Request VO")
@Data
public class ExtraSourceInfoSaveReqVO {

    @Schema(description = "资源编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8528")
    private Long id;

    @Schema(description = "资源形式")
    private String sourceForm;

    @Schema(description = "资源来源")
    private String sourceOrigin;

    @Schema(description = "资源地址")
    private String sourceUrl;

    @Schema(description = "资源状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "资源状态不能为空")
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

    @Schema(description = "附件信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "附件信息")
    private List<StorageAddressVO> storageInfos;

    @Schema(description = "备用字段1")
    private String spareFiled1;

    @Schema(description = "备用字段2")
    private String spareFiled2;

    @Schema(description = "上传用户(与创建人一致)", example = "6099")
    private String uploadUserId;

    @Schema(description = "审核用户1", example = "7649")
    private String auditUser1Id;

    @Schema(description = "审核用户2", example = "5749")
    private String auditUser2Id;

    @Schema(description = "审核状态(0-未审核	|1-审核不通过|	2-审核通过)")
    private String auditStatus;
}