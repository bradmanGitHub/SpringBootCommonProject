package com.in28minutes.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	//Authentication : User - Roles
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
//        		.withUser("user").password("user").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("USER","ADMIN");
//    }
//	
//	
//	//Authorization: Role -> Access
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.httpBasic().and().authorizeRequests()
////        .antMatchers("/login").permitAll()
////        .antMatchers("/", "/*todo*/**").access("hasRole('USER')")
////        .antMatchers("/*todo*/**").access("hasRole('USER')")
////        .antMatchers("/surveys/**").hasRole("USER")
////        .antMatchers("/customer/**").hasRole("USER")
////        .antMatchers("/welcome/**").hasRole("USER")
////        .antMatchers("/**").hasRole("ADMIN")
////        .and()
////        .formLogin().defaultSuccessUrl("/welcome")
////		.loginPage("/login").and().logout().permitAll();
//        
//        http.authorizeRequests().antMatchers("/login").permitAll()
//		.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
//		.formLogin().loginPage("/login");
//    }
//    
    
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login")).and().authorizeRequests()
//				.antMatchers("/").hasRole("USER")
//				.antMatchers("/*todo*/**").access("hasRole('USER')")
//				.antMatchers("/welcome/**").hasRole("USER")
//		        .antMatchers("/surveys/**").hasRole("USER")
//		        .antMatchers("/customer/**").hasRole("ADMIN")
				.and().formLogin().defaultSuccessUrl("/")
				.loginPage("/login").and().logout().permitAll();
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
		.withUser("user").password("user").roles("USER")
        .and()
        .withUser("admin").password("admin").roles("USER","ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/*.css");
		web.ignoring().antMatchers("/*.js");
	}
	
}
