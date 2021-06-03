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
			.antMatchers("/users/*").permitAll()
			.antMatchers("/users/loginFromServiceToken").permitAll()
			.antMatchers(HttpMethod.POST,"/users/admin/manager").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST,"/users/admin/hotel").hasRole("MANAGER")
			.antMatchers(HttpMethod.POST,"/hotels").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET,"/hotels").permitAll()
			.antMatchers(HttpMethod.GET,"/hotels/*").permitAll()
			.antMatchers(HttpMethod.PUT,"/hotels/*").permitAll()
			.antMatchers(HttpMethod.DELETE,"/hotels/*").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET,"/hotels/*/rooms/*").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels/*/rooms").hasRole("MANAGER")
			.antMatchers(HttpMethod.PUT,"/hotels/*/rooms/*").permitAll()
			.antMatchers(HttpMethod.DELETE,"/hotels/*/rooms/*").permitAll()
			.antMatchers(HttpMethod.GET,"/hotels/roomtypes").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels/*/services").hasRole("MANAGER")
			.antMatchers(HttpMethod.POST,"/hotels/*/services").hasRole("HOTEL")
			.antMatchers(HttpMethod.GET,"/hotels/*/services/*").permitAll()
			.antMatchers(HttpMethod.PUT,"/hotels/*/services/*").permitAll()
			.antMatchers(HttpMethod.DELETE,"/hotels/*/services/*").hasRole("MANAGER")
			.antMatchers(HttpMethod.GET,"/hotels/reservationUser/*").hasRole("USER")
			.antMatchers(HttpMethod.GET,"/hotels/*/reservations/*").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels/*/reservations").permitAll()
			.antMatchers(HttpMethod.PUT,"/hotels/*/reservations/*").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels/*/reservations/*/assignRoom").permitAll()
			.antMatchers(HttpMethod.POST,"/hotels/*/reservations/*/guests").permitAll()
			.antMatchers(HttpMethod.GET,"/hotels/*/guests").permitAll()
			.antMatchers(HttpMethod.DELETE,"/hotels/*/reservations/*").permitAll()
			.antMatchers(HttpMethod.GET, "/hotels/*/roomassign").hasRole("HOTEL")
			.antMatchers(HttpMethod.GET, "/hotels/*/roomassign").hasRole("MANAGER")
			
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
