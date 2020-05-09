package com.herokuapp.skmtodoapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/","/signup","/processSignup" , "/resources/**","/account/**")
			.permitAll()
			.antMatchers("/todo/**", "/profile/**").hasAnyRole("USER","ADMIN")
			.antMatchers("/actuator/**").hasAnyRole("ADMIN");
		http.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/processLogin") 
				.defaultSuccessUrl("/todo/upcoming", true);
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

}
