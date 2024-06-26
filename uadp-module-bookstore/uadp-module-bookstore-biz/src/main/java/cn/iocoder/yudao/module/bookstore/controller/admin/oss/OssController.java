package cn.iocoder.yudao.module.bookstore.controller.admin.oss;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.bookstore.config.OssConfig;
import cn.iocoder.yudao.module.bookstore.service.oss.OssService;
import cn.iocoder.yudao.module.bookstore.controller.admin.oss.vo.OssStsTokenResult;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * Oss相关操作接口
 * @auther 团子
 * @date 2019-07-31 11:31
 */
@Tag(name = "管理后台 - 文件存储-oss")
@RestController
@RequestMapping("/infra/file-oss")
@Validated
@Slf4j
public class OssController {

    public static final Logger logger = LoggerFactory.getLogger(OssController.class);

    @Resource
    private OssService ossService;

    @Resource
    private OssConfig ossConfig;

    @GetMapping("/sts")
    @Operation(summary = "获取sts签名信息", description = "获取sts签名信息")
    public CommonResult<OssStsTokenResult> sts() {
        AssumeRoleResponse.Credentials credentials = ossService.sts();
        // 构建 OSS token 返回给前端
        OssStsTokenResult ossStsTokenResult = OssStsTokenResult.builder()
        .region(ossConfig.getRegionId())
        .accessKeyId(credentials.getAccessKeyId())
        .accessKeySecret(credentials.getAccessKeySecret())
        .securityToken(credentials.getSecurityToken())
        .domainName(ossConfig.getDomainName())
        .expiration(credentials.getExpiration())
        // OSS bucket名称
        .bucket(ossConfig.getBucketName())
        .build();
        return success(ossStsTokenResult);
    }
}
