package cn.iocoder.yudao.module.bookstore.controller.admin.sourceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 资源信息新增/修改 Request VO")
@Data
public class StorageAddressVO {

    @Schema(description = "资源名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "资源名称不能为空")
    private String sourceName;

    @Schema(description = "格式", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotEmpty(message = "格式不能为空")
    private String format;

    @Schema(description = "文件大小", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotNull(message = "文件大小不能为空")
    private Integer size;

    @Schema(description = "大小单位", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotEmpty(message = "大小单位不能为空")
    private String sizeUnit;


    @Schema(description = "存储地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "存储地址不能为空")
    private String storageUrl;

}