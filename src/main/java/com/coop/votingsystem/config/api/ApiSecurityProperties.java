package com.coop.votingsystem.config.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties("api-security")
public class ApiSecurityProperties {
    private boolean enabled;
    private List<String> apiKeys;
}
