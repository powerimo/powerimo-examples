package org.powerimo.examples.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.powerimo.examples.jobs.LogStep;
import org.powerimo.examples.jobs.LongStep;
import org.powerimo.jobs.JobDescriptor;
import org.powerimo.jobs.JobStateInfo;
import org.powerimo.jobs.Runner;
import org.powerimo.jobs.StepDescriptor;
import org.powerimo.jobs.std.StdJob;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final Runner runner;
    private final static String JOB_1 = "JOB_1";

    @PostConstruct
    public void init() {
        JobDescriptor jobDescriptor = JobDescriptor.create(JOB_1, "Sample Job", StdJob.class);
        runner.getRepository().addJobDescriptor(jobDescriptor);

        runner.getRepository().addStepDescriptor(StepDescriptor.of("STEP01", "log", 10, LogStep.class, JOB_1));
        runner.getRepository().addStepDescriptor(StepDescriptor.of("STEP02", "long step", 20, LongStep.class, JOB_1));
        runner.getRepository().addStepDescriptor(StepDescriptor.of("STEP03", "log again", 30, LogStep.class, JOB_1));
    }

    public JobStateInfo runJob1() throws NoSuchMethodException {
        return runner.run(JOB_1);
    }

    public JobStateInfo runJob(String code) throws NoSuchMethodException {
        return runner.run(code);
    }

}
