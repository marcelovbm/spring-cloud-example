package com.example.adminserver.adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration(proxyBeanMethods = false)
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}

	@Configuration(proxyBeanMethods = false)
	public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

		private final String adminContextPath;

		public SecurityPermitAllConfig(AdminServerProperties adminServerProperties) {
			this.adminContextPath = adminServerProperties.getContextPath();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
					.csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
							.ignoringRequestMatchers(
									new AntPathRequestMatcher(this.adminContextPath + "/instances",
											HttpMethod.POST.toString()),
									new AntPathRequestMatcher(this.adminContextPath + "/instances/*",
											HttpMethod.DELETE.toString()),
									new AntPathRequestMatcher(this.adminContextPath + "/actuator/**")));

		}

	}
}
