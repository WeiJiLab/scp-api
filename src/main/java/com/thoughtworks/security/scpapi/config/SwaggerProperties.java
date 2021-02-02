package com.thoughtworks.security.scpapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {
    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    private Boolean enable;

    /**
     * 项目应用名
     */
    private String applicationName;

    /**
     * 项目版本信息
     */
    private String applicationVersion;

    /**
     * 项目描述信息
     */
    private String applicationDescription;

    /**
     * 接口调试地址
     */
    private String tryHost;

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public void setTryHost(String tryHost) {
        this.tryHost = tryHost;
    }

    public Boolean getEnable() {
        return enable;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public String getTryHost() {
        return tryHost;
    }
}
