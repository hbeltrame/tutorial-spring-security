package com.empresa.cursospringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.empresa.cursospringsecurity.security.JWTAuthenticationFilter;
import com.empresa.cursospringsecurity.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;  // o framework irá buscar a implementação da interface
	
	@Autowired
	private JWTUtil jwtUtil;
	
	// paths liberadas para acesso
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			"/login/**"
	};
	
	// configurações HTTP
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();  // libera o acesso ao h2
		
		http.cors()  // aplica as configurações CORS, caso haja um bean definido
			.and().csrf().disable();  // desabilita o csrf
			
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()  // todas as paths no vetor serão permitidas
			.anyRequest().authenticated();  // para todo o resto, exige autenticação
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		// assegura que o back-end não irá criar sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	// sobrecarga com configurações de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	// configurações CORS
	@Bean
	CorsConfigurationSource configurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		// permite acesso cross-origin a todos os endpoints com as configurações básicas
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		
		return source;
	}
	
	// criptografia para as senhas
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
