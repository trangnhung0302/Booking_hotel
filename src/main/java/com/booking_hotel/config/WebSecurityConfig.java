package com.booking_hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.booking_hotel.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/", "/register", "/css/**", "/images/**", "/api/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin(login -> login.loginPage("/login").permitAll()
                        .loginProcessingUrl("/j_spring_security_check")
                        .defaultSuccessUrl("/booking")
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")).logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
		//auth.authenticationProvider(authenticationProvider());
	}
	
	protected void checkAuthentication() {
		System.out.println("xxxxxxxxxxxxxxxxxx");
	}
}
