package com.crystal.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "")
@Data
public class CommonProperties {

    @Value("blog.imagesPath")
    private String imagesPath;


}
