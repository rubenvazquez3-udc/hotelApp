package es.udc.hotelapp.backend.rest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilter(new JwtFilter(authenticationManager(), jwtGenerator))
			.authorizeRequests()
			.antMatchers("/users/signUp").permitAll()
			.antMatchers("/users/login").permitAll()
			.antMatchers(HttpMethod.PUT, "/users/*").hasAnyRole("USER","MANAGER","HOTEL","ADMIN")
			.antMatchers(HttpMethod.POST, "/users/*/changePassword").hasAnyRole("USER","MANAGER","HOTEL","ADMIN")
			.antMatchers("/users/loginFromServiceToken").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET,"/hotels").permitAll()
			.antMatchers(HttpMethod.GET,"/hotels/*").permitAll()
			.antMatchers(HttpMethod.PUT,"/hotels/*").permitAll()
			.antMatchers(HttpMethod.DELETE,"/hotels/*").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET,"/hotels/*/rooms/*").hasAnyRole("MANAGER","HOTEL")
			.antMatchers(HttpMethod.POST,"/hotels/*/rooms").hasRole("MANAGER")
			.antMatchers(HttpMethod.PUT,"/hotels/*/rooms/*").permitAll()
			.antMatchers(HttpMethod.DELETE,"/hotels/*/rooms/*").permitAll()
			.antMatchers(HttpMethod.GET,"/hotels/roomtypes").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels/*/services").hasAnyRole("MANAGER","HOTEL")
			.antMatchers(HttpMethod.GET,"/hotels/*/services/*").permitAll()
			.antMatchers(HttpMethod.PUT,"/hotels/*/services/*").hasAnyRole("MANAGER","HOTEL")
			.antMatchers(HttpMethod.DELETE,"/hotels/*/services/*").hasRole("MANAGER")
			.antMatchers(HttpMethod.GET,"/hotels/reservationUser/*").hasRole("USER")
			.antMatchers(HttpMethod.GET,"/hotels/*/reservations/*").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels/*/reservations").hasRole("USER")
			.antMatchers(HttpMethod.PUT,"/hotels/*/reservations/*").hasAnyRole("MANAGER","HOTEL","USER")
			.antMatchers(HttpMethod.POST,"/hotels/*/reservations/*/assignRoom").hasAnyRole("MANAGER","HOTEL")
			.antMatchers(HttpMethod.POST,"/hotels/*/reservations/*/guests").hasAnyRole("MANAGER","HOTEL")
			.antMatchers(HttpMethod.GET,"/hotels/*/guests").hasAnyRole("MANAGER","HOTEL")
			.antMatchers(HttpMethod.DELETE,"/hotels/*/reservations/*").permitAll()
			.antMatchers(HttpMethod.GET, "/hotels/*/roomassign").hasAnyRole("HOTEL","MANAGER")
			.anyRequest().denyAll();

			

	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration config = new CorsConfiguration();
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    
	    source.registerCorsConfiguration("/**", config);
	    
	    return source;
	    
	 }

}
