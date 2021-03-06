package com.innovatehub.inventorymgmt.site.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.innovatehub.inventorymgmt.services.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	LoginSuccessHandler loginSuccessHandler;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/dbc/**").permitAll()
		.antMatchers("/static/**").permitAll()
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home").successHandler(loginSuccessHandler)
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
		.logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID")
		.invalidateHttpSession(true) 
		.and().exceptionHandling().accessDeniedPage("/error403");
		
		
		// ToDo: Comment this later.
		 httpSecurity.csrf().disable();
	     httpSecurity.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
}
