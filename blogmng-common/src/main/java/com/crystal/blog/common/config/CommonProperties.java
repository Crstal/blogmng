package com.crystal.blog.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "blog")
@Data
public class CommonProperties {

    private String imagesPath;
}
