package me.sa1zer_.sporterbook.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${common.path}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File(uploadDir);
        if (!file.exists()) {
            file.mkdir();
        }

        registry.addResourceHandler("/api/" + uploadDir + "/**")
                .addResourceLocations("file:///" + file.getAbsolutePath() + "/");
    }
}
