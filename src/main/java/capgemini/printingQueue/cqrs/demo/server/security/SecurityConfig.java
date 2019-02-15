package capgemini.printingQueue.cqrs.demo.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser("admin").password(encoder.encode("admin")).roles("ADMIN")
		.and().withUser("user").password(encoder.encode("user")).roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(getRestAuthenticationEntryPoint())
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

	@Bean
	AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}
}
