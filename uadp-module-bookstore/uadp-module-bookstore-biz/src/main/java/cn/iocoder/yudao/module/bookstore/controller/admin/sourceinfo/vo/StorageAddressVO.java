package cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 资源信息新增/修改 Request VO")
@Data
public class StorageAddressVO {

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "资源名称不能为空")
    private String sourceName;

    @Schema(description = "格式")
    private String format;

    @Schema(description = "文件大小")
    private Integer size;

    @Schema(description = "大小单位")
    private String sizeUnit;


    @Schema(description = "存储地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "存储地址不能为空")
    private String storageUrl;

}