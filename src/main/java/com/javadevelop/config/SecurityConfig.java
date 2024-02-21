package com.javadevelop.config;

import com.javadevelop.jwt.JwtFilter;
import com.javadevelop.jwt.JwtToken;
import com.javadevelop.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .cors() // Ngăn chặn request từ một domain khác
                .and()
                .authorizeRequests()
                .antMatchers("/", "/hazelcast", "/listUser", "/login", "/mongodb/**").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers(HttpMethod.POST,"/user").permitAll() // Cho phép tất cả mọi người truy cập vào địa chỉ này
                .antMatchers(HttpMethod.GET, "/user").hasAnyAuthority("MANAGER","ADMIN") // Cho phép quyền admin, mânger
                .antMatchers(HttpMethod.PUT, "/user").hasAnyAuthority("MANAGER","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user").hasAuthority("ADMIN") // Chỉ cho phép admin
                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // phải sử dụng token (đc cấp sau khi login) để request các api khác

        http.logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("chuoibimat", "JSESSIONID")
                .permitAll();

        // lớp Filter kiểm tra jwt
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
