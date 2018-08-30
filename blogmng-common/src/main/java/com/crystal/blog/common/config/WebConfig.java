package com.crystal.blog.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@EnableConfigurationProperties(value = {CommonProperties.class})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CommonProperties commonProperties;

    // 访问图片方法
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String mImagesPath = "file:" + commonProperties.getImagesPath();
//        if (mImagesPath.equals("") || mImagesPath.equals("${blog.imagesPath}")) {
//            String imagesPath = WebConfig.class.getClassLoader().getResource("").getPath();
//            if (imagesPath.indexOf(".jar") > 0) {
//                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
//            } else if (imagesPath.indexOf("classes") > 0) {
//                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
//            }
//            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
//            mImagesPath = imagesPath;
//        }
//        log.info("imagesPath:{}", mImagesPath);
//        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
//    }
}
