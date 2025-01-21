package com.project.userservice.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "dwomg8q1c");
        config.put("api_key", "792265442116982");
        config.put("api_secret", "3fQBrZ-wDFE-z12URgL_NI62ypc");
        return new Cloudinary(config);
    }
}
