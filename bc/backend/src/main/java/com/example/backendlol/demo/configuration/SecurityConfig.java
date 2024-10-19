package com.example.backendlol.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    /**
     * Creates and returns a new instance of {@link BCryptPasswordEncoder}.
     *
     * <p>This method is used to encode passwords using the BCrypt hashing function,
     * which is a strong and adaptive hashing algorithm designed for securely storing passwords.</p>
     *
     * @return a new instance of {@link BCryptPasswordEncoder}
     *
     * @throws IllegalArgumentException if any argument passed to the constructor of
     *         {@link BCryptPasswordEncoder} is invalid (not applicable in this case as
     *         the default constructor does not take any parameters).
     */
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    /**
     * Configures a {@link SecurityFilterChain} for HTTP security settings.
     *
     * <p>This method customizes the security settings for HTTP requests, including
     * CSRF protection, authorization rules for different user roles, and CORS settings.
     * The following rules are applied:</p>
     * <ul>
     *     <li>CSRF protection is disabled.</li>
     *     <li>Requests to "/api/users/**" require the "USER" role.</li>
     *     <li>Requests to "/api/admin/**" require the "ADMIN" role.</li>
     *     <li>Requests to "/api/vendors/**" require the "VENDOR" role.</li>
     *     <li>Requests to "/api/feedback/**" are permitted for all users.</li>
     *     <li>Any other request must be authenticated.</li>
     * </ul>
     *
     * <p>Additionally, form login is disabled, and CORS is also disabled.</p>
     *
     * @param http the {@link HttpSecurity} object to be configured
     * @return a configured {@link SecurityFilterChain} object
     * @throws Exception if an error occurs while configuring the security settings
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
   .csrf(csrf -> csrf.disable())
   .authorizeHttpRequests(authz -> authz
   .requestMatchers("/api/users/**").hasRole("USER")
   .requestMatchers("/api/admin/**").hasRole("ADMIN")
   .requestMatchers("/api/vendors/**").hasRole("VENDOR") 
   .requestMatchers("/api/feedback/**").permitAll()
   .anyRequest().authenticated()
   )
   .formLogin(form -> form.disable())
   .cors(cors -> cors.disable());
    
        return http.build();
    }
    
}