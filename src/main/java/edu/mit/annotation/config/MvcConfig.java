package edu.mit.annotation.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/blueprint/**")
                .addResourceLocations("file:C:/upload/blueprint/"); // 실제 파일 경로에 맞게 수정
        registry.addResourceHandler("/upload/contract/**")
                .addResourceLocations("file:C:/upload/contract/"); // 실제 파일 경로에 맞게 수정
    }
}