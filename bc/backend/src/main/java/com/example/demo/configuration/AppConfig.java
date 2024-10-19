package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.util.JwtUtil;

@Configuration
public class AppConfig {

    @Bean
    /**
     * Creates and returns a new instance of the {@link JwtUtil} class.
     *
     * @return a new instance of {@link JwtUtil}
     * @throws IllegalStateException if the JwtUtil cannot be created due to some internal error.
     */
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
