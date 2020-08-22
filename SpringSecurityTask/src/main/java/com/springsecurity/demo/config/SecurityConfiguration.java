package com.springsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler failureHandler;
	
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/register").hasAnyAuthority("Role1")
				.antMatchers("/api/viewData").hasAnyAuthority("Role2")
				.antMatchers("/api/recomended/data").hasAnyAuthority("Role3")
				.antMatchers("/api/final/data").hasAnyAuthority("Role4")
				.and().formLogin().loginPage("/login").successHandler(successHandler).failureHandler(failureHandler)
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	@Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
