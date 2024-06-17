package cn.iocoder.yudao.module.bookstore.config;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * oss配置文件
 * @auther cuiming
 * @date 2024-05-31 10:03
 */
@Configuration
@Data
public class OssConfig {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.stsEndpoint}")
    private String stsEndpoint;

    @Value("${aliyun.oss.roleArn}")
    private String roleArn;

    @Value("${aliyun.oss.roleSessionName}")
    private String roleSessionName;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.policy.expire}")
    private int expire;

    @Value("${aliyun.oss.maxSize}")
    private int maxSize;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.dir.prefix:/}")
    private String prefix;

//    @Value("${aliyun.oss.callback}")
//    private String callback;

    @Value("${aliyun.oss.regionId}")
    private String regionId;

    @Value("${aliyun.oss.domainName}")
    private String domainName;
    @Bean
    public OSSClient ossClient(){

        return new OSSClient(endpoint, accessKeyId, accessKeySecret);

    }
}