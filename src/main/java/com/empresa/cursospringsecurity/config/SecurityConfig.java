package com.empresa.cursospringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	
	// paths liberadas para acesso
	private static final String[] PUBLIC_MATCHERS = {};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()  // aplica as configurações CORS, caso haja um bean definido
			.and().csrf().disable();  // desabilita o csrf
			
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()  // todas as paths no vetor serão permitidas
			.anyRequest().authenticated();  // para todo o resto, exige autenticação
		
		// assegura que o back-end não irá criar sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	// configurações CORS
	@Bean
	CorsConfigurationSource configurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		// permite acesso cross-origin a todos os endpoints com as configurações básicas
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		
		return source;
	}
}
