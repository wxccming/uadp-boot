package cn.iocoder.yudao.module.bookstore.controller.admin.oss.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 获取OSS临时访问凭证
 * @auther
 * @date 2019-07-31 11:31
 */

@Schema(description = "管理后台 - 获取OSS临时访问凭证返回结果")
@Data
@Builder
public class OssStsTokenResult {
    @Schema(description = "访问身份验证中用到用户标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "访问身份验证中用到用户标识")
    private String accessKeyId;

    @Schema(description = "区域", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotNull(message = "区域")
    private String region;

    @Schema(description = "密钥", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "密钥")
    private String accessKeySecret;

    @Schema(description = "安全访问token", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "安全访问token")
    private String securityToken;

    @Schema(description = "存储桶", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @NotNull(message = "存储桶")
    private String bucket;

    @Schema(description = "过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "过期时间")
    private String expiration;

    @Schema(description = "自有域名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "自有域名")
    private String domainName;

}