package com.batarang.api.Security;

import com.batarang.api.Security.JWTAuthentication.JWTAuthEntryPoint;
import com.batarang.api.Security.JWTAuthentication.JWTAuthenticationFilter;
import com.batarang.api.Security.JWTAuthentication.JWTAuthenticationProvider;
import com.batarang.api.Security.JWTAuthentication.JWTSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationProvider autheticationProvider;

    @Autowired
    private JWTAuthEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(autheticationProvider));
    }

    @Bean
    public JWTAuthenticationFilter authTokenFilter() {

        JWTAuthenticationFilter filter =new JWTAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JWTSuccessHandler());
        return filter;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers("**/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        http.headers().cacheControl();
    }

}