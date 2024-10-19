package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    /**
     * Configures CORS (Cross-Origin Resource Sharing) settings for the application.
     * This method returns a {@link WebMvcConfigurer} instance that customizes the CORS
     * mappings and resource handlers.
     *
     * <p>The CORS configuration allows requests from the specified origin,
     * supports various HTTP methods, and allows credentials to be included in
     * requests. The resource handler is set up to serve static files from a
     * specified file path.</p>
     *
     * <p>Example usage:</p>
     * <pre>
     * WebMvcConfigurer configurer = corsConfigurer();
     * </pre>
     *
     * @return a {@link WebMvcConfigurer} that configures CORS mappings and resource handlers.
     *
     * @throws IllegalArgumentException if the specified resource location is invalid or inaccessible.
     * @throws NullPointerException if the registry or any of its parameters are null.
     */
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override
            /**
             * Configures CORS (Cross-Origin Resource Sharing) mappings for the application.
             *
             * This method adds CORS mappings to the provided {@link CorsRegistry} instance.
             * It allows requests from the specified origin and permits the specified HTTP methods,
             * headers, and credentials.
             *
             * @param registry the {@link CorsRegistry} to which the CORS mappings will be added
             *
             * @throws IllegalArgumentException if the provided registry is null
             *
             * <p>
             * The following CORS settings are applied:
             * <ul>
             *   <li>Allowed origins: "http://localhost:3000"</li>
             *   <li>Allowed methods: GET, POST, PUT, DELETE, OPTIONS</li>
             *   <li>Allowed headers: all headers ("*")</li>
             *   <li>Credentials: allowed</li>
             * </ul>
             * </p>
             */
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
             /**
              * Adds resource handlers for serving static files.
              *
              * This method configures the given {@link ResourceHandlerRegistry} to serve
              * static resources from a specified location. The resources can be accessed
              * via the URL pattern "/upload/**".
              *
              * @param registry the {@link ResourceHandlerRegistry} to which the resource
              *                 handlers will be added
              *
              * @throws IllegalArgumentException if the provided registry is null
              * @throws ResourceLocationException if the resource location is invalid or
              *                                    cannot be accessed
              */
             public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/path/to/static/files/");
}

        };
    }
    
}
