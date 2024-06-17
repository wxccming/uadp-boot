package cn.iocoder.yudao.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * @author 晟云鑫通
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${yudao.info.base-package}
@SpringBootApplication(scanBasePackages = {"${yudao.info.base-package}.server", "${yudao.info.base-package}.module"})
@Slf4j
public class UadpServerApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(UadpServerApplication.class, args);
        }catch (Exception e){
            log.error("服务启动异常，容器退出，异常信息如下 :" , e);
            System.exit(-1);
        }
    }

}
