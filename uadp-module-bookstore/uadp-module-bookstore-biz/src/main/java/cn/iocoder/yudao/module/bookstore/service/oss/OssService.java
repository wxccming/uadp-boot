package cn.iocoder.yudao.module.bookstore.service.oss;

import com.aliyuncs.auth.sts.AssumeRoleResponse;

/**
 * oss上传管理Service
 * @auther 团子
 * @date 2019-07-31 11:31
 */

public interface OssService {
    /**
     * sts签名
     */
    AssumeRoleResponse.Credentials sts();
}
