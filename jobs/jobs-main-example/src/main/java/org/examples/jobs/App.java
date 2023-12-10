package org.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.DescriptorRepository;
import org.powerimo.jobs.JobDescriptor;
import org.powerimo.jobs.RunnerConfiguration;
import org.powerimo.jobs.StepDescriptor;
import org.powerimo.jobs.std.*;

@Slf4j
public class App {

    public static void main(String[] args) {
        final RunnerConfiguration runnerConfiguration = StdRunnerConfiguration.builder()
                .createMissing(true)
                .build();
        final DescriptorRepository descriptorRepository = runnerConfiguration.getDescriptorRepository();
        var runner = new StdRunner(runnerConfiguration);

        descriptorRepository.addJobDescriptor(StdJobDescriptor.create("Job1", "Job 1 name", StdJob.class));
        descriptorRepository.addStepDescriptor(StdStepDescriptor.of("Step01", "Step 1", 1, SleepStep.class, "Job1"));
        descriptorRepository.addStepDescriptor(StdStepDescriptor.of("Step02", "Step 2 (one more time)", 2, SleepStep.class, "Job1"));
        descriptorRepository.addStepDescriptor(StdStepDescriptor.of("JobStateList", "Print job state", 30, ListJobsStep.class, "Job1"));
        descriptorRepository.addStepDescriptor(StdStepDescriptor.of("StepStateList", "Print step state", 40, ListStepsStep.class, "Job1"));

        try {
            runner.run("Job1");

            Thread.sleep(10000L);
        } catch (Exception ex) {
            log.error("Exception on run job.", ex);
        }
    }

}
