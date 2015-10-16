package org.angularsecurity.authentication;

import org.angularsecurity.filter.StatelessAuthenticationFilter;
import org.angularsecurity.filter.StatelessLoginFilter;
import org.angularsecurity.service.TokenAuthenticationService;
import org.angularsecurity.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	public SecurityConfig() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.exceptionHandling().and()
				.anonymous().and()
				.servletApi().and()
				.headers().cacheControl().and()
				.authorizeRequests()
				
								
				//allow anonymous resource requests
				.antMatchers("/").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/resources/**").permitAll()
				
				//allow anonymous POSTs to login
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				
				//allow anonymous GETs to API
				.antMatchers(HttpMethod.GET, "/api/**").permitAll()
				
				//defined Admin only API area
				.antMatchers("/admin/**").hasRole("ADMIN")
				
				//all other request need to be authenticated
				.anyRequest().hasRole("USER").and()				
		
				.addFilterBefore(new StatelessLoginFilter("/api/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

				// custom Token based authentication based on the header previously given to the client
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
}
