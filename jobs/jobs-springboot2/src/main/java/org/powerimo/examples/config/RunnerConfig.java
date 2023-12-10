package org.powerimo.examples.config;

import lombok.RequiredArgsConstructor;
import org.powerimo.jobs.boot2.StdSpringJob;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
public class RunnerConfig {
    private final ApplicationContext applicationContext;

    @Bean
    @Scope(value = "prototype")
    public StdSpringJob stdSpringJob() {
        return new StdSpringJob();
    }

}
