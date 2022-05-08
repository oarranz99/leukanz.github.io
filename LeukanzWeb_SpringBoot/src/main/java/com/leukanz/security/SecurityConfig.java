package com.leukanz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leukanz.services.MongoUsersDetailsService;

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private MongoUsersDetailsService usersDetailsService;
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() 
		.authorizeRequests()
		.antMatchers("api/**").permitAll()
		.and().httpBasic();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersDetailsService);
	}
    
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(usersDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	@Autowired
	public void setUserDetailsService(@Qualifier("MongoUsersDetailsService") MongoUsersDetailsService userDetailsService ) {
		this.usersDetailsService = userDetailsService;
	}

}
