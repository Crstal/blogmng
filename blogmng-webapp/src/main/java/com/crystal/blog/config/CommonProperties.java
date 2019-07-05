package com.crystal.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "blog.common")
@Data
public class CommonProperties {

    private String imagesPath;


}
