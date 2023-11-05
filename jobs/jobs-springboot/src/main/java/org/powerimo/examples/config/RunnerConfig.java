package org.powerimo.examples.config;

import lombok.RequiredArgsConstructor;
import org.powerimo.jobs.Runner;
import org.powerimo.jobs.generators.LocalLongIdGenerator;
import org.powerimo.jobs.std.StdRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RunnerConfig {

    @Bean
    public Runner getRunner() {
        var runner = new StdRunner();
        runner.setStepIdGenerator(new LocalLongIdGenerator(0));
        runner.setJobIdGenerator(new LocalLongIdGenerator(0));
        return runner;
    }


}
