package org.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.JobDescriptor;
import org.powerimo.jobs.Repository;
import org.powerimo.jobs.StepDescriptor;
import org.powerimo.jobs.std.StdInMemoryStateRepository;
import org.powerimo.jobs.std.StdJob;
import org.powerimo.jobs.std.StdRepository;
import org.powerimo.jobs.std.StdRunner;

@Slf4j
public class App {

    public static void main(String[] args) {
        final Repository repository = new StdRepository();
        var runner = new StdRunner(repository);
        runner.setStateRepository(new StdInMemoryStateRepository());

        repository.addJobDescriptor(JobDescriptor.create("Job1", "Job 1 name", StdJob.class));
        repository.addStepDescriptor(StepDescriptor.of("Step01", "Step 1", 1, SleepStep.class, "Job1"));
        repository.addStepDescriptor(StepDescriptor.of("Step02", "Step 2 (one more time)", 2, SleepStep.class, "Job1"));
        repository.addStepDescriptor(StepDescriptor.of("JobStateList", "Print job state", 30, ListJobsStep.class, "Job1"));
        repository.addStepDescriptor(StepDescriptor.of("StepStateList", "Print step state", 40, ListStepsStep.class, "Job1"));

        try {
            runner.run("Job1");

            Thread.sleep(10000L);
        } catch (Exception ex) {
            log.error("Exception on run job.", ex);
        }
    }

}
