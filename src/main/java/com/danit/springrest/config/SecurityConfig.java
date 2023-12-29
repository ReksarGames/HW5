package com.danit.springrest.config;

import com.danit.springrest.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.logging.Logger;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig  {
    private static final Logger logger = Logger.getLogger(SecurityConfig.class.getName());
    private PasswordEncoder passwordEncoder;
    private JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, JwtFilter jwtFilter) {
        this.passwordEncoder = passwordEncoder;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder().passwordEncoder(password -> passwordEncoder.encode(password));
        UserDetails user = User.builder()
                .username("sa")
                .password("sa")
                .roles("ADMIN")
                .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("1").password("1").roles("USER", "ADMIN").build());
        manager.createUser(user);

        logger.warning("User password: " + users.username("user").password("password").roles("USER").build().getPassword());
        logger.warning("Admin password: " + users.username("admin").password("password").roles("USER", "ADMIN").build().getPassword());
        return manager;
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/login").permitAll()
//                .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                .requestMatchers("/api/user/**").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        return http.build();
//    }

//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//
//            .httpBasic().disable()
//            .csrf().disable()
//            .authorizeHttpRequests(authorization -> authorization
//                    .requestMatchers("/login", "/token","/oauth/**").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .formLogin().permitAll()
//                .defaultSuccessUrl("/dashboard")
//                .and()
//            .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login").permitAll()
//            .and()
//            .exceptionHandling()
//                .accessDeniedPage("/403");
//
//    return http.build();
//}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/login", "/token", "/css/**", "/js/**", "/registration", "/new", "http://localhost:9000/h2-console" ).permitAll()
                        .requestMatchers("/customers/**", "/employers/**").hasAnyAuthority(JwtUtils.Roles.USER.name())
                        .requestMatchers("/**").hasAnyAuthority(JwtUtils.Roles.ADMIN.name())
                        .anyRequest().permitAll()
//                        .requestMatchers("/login", "/token","/oauth/**").permitAll()
//                        .anyRequest().authenticated()
                )
                .rememberMe()
                .tokenValiditySeconds(86400) // 24h // 7d default
                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .formLogin().permitAll()
                    .defaultSuccessUrl("/dashboard")
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutSuccessUrl("/login").permitAll()
                .and()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                    .accessDeniedPage("/403");

        return http.build();
    }

}
