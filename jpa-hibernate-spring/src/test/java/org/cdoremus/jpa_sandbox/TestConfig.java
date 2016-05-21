package org.cdoremus.jpa_sandbox;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfig.class, PersistenceConfig.class})
public class TestConfig {

}
