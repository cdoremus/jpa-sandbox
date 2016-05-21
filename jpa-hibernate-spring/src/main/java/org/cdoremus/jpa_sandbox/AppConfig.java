package org.cdoremus.jpa_sandbox;

import org.cdoremus.jpa_sandbox.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"org.cdoremus.jpa_sandbox"})
public class AppConfig {

	@Bean
	public UserRepository userRepository() {
		return new UserRepository();
	}
	
}
