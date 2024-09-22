package com.hzp.aixiaoda.config;


import com.hzp.aixiaoda.properties.AliOssProperties;
import com.hzp.aixiaoda.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: aixiaoda
 * @BelongsPackage: com.hzp.aixiaoda.config
 * @Author: ASUS
 * @CreateTime: 2023-07-28  12:46
 * @Description: 阿里OSS配置类
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean//如果没有这个aliOssUtil  我就创建这个aliOssUtil
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("开始创建阿里云文件上传工具类对象:{}"+aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }

}
