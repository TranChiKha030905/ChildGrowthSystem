package com.example.childgrowthsystem.config;

import com.example.childgrowthsystem.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration  // Đánh dấu là file cấu hình
public class securityconfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Bạn có thể dùng các thuật toán mã hóa khác như bcrypt, argon2...
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth                                                   // yêu cầu chứng thực
                        .requestMatchers("/", "/index", "/assets/**", "/forms/**", "/Admin/assets/**").permitAll()       //cho phép cấu hình vô
                        .anyRequest().authenticated()                                                                             //còn các request còn lại phải chứng thực
                )
                .formLogin(form -> form                                                             // cho phép vào trang login
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/login?success=true")
                        .failureUrl("/login?success=false")
                        .loginProcessingUrl("/j_spring_security_check")                                                             // chech login (có sẵn) url
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {                                                                                     // cài đặt 1 tải khoảng bộ nhớ để auto đăng nhập đúng
        UserDetails user1 = User.withUsername("user@gmail.com")
                .password(passwordEncoder().encode("123456"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }
}
