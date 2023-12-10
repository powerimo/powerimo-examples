package org.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.*;
import org.powerimo.jobs.std.StdStepResult;

@Slf4j
public class SleepStep implements Step {
    final StdStepResult stepResult = new StdStepResult();

    @Override
    public StepResult run(JobContext jobContext, StepDescriptor stepDescriptor) throws Exception {
        // DO SOMETHING
        log.info("Some job here");

        for (int i = 0; i < 100; i++) {
            Thread.sleep(10);
        }

        stepResult.setResult(Result.SUCCESS);
        stepResult.setCountErrors(1L);
        return stepResult;
    }
}

