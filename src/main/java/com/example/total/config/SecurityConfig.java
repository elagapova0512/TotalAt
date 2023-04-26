package com.example.total.config;

import com.example.total.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
    private final PersonDetailsService personDetailsService;
@Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    @Bean
    public PasswordEncoder getPasswordEncode(){
    return new BCryptPasswordEncoder();
    }
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncode());
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpS) throws Exception {
httpS
        .authorizeHttpRequests()
        .requestMatchers("/admin","/admin/**").hasRole("ADMIN")
        .requestMatchers("/logIn","/registration", "/error","/resources/**","/script/**","/static/**","/img/**","/style/**","/product","/product/info/{id}","/product/search").permitAll()
        .anyRequest().hasAnyRole("ADMIN","USER")
        .and()
        .formLogin().loginPage("/product")//был logIn
        .loginProcessingUrl("/process_logIn")
        .defaultSuccessUrl("/pers_account",true)
        .failureUrl("/logIn?error")
        .and()
        .logout().logoutUrl("/logOut").logoutSuccessUrl("/logIn");
return httpS.build();
    }
}
