package com.hzp.aixiaoda.config;

import com.zhipu.oapi.ClientV4;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: aixiaoda-backend
 * @BelongsPackage: com.hzp.aixiaoda.config
 * @Author: ASUS
 * @CreateTime: 2024-08-31  17:03
 * @Description: AI配置管理类
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "ai")
@Data
public class AiConfig {
    /*
     * @description:apiKey需要从开发平台获取
     * @author:  HZP
     * @date: 2024/8/31 17:04
     * @param:
     * @return:
     **/
    private String apiKey;

    @Bean
    public ClientV4 getClientV4() {
        return new ClientV4.Builder(apiKey).build();
    }

}
