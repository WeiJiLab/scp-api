package com.thoughtworks.security.scpapi.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "saml")
public class SamlProperties {
  private String cerPath;
}
