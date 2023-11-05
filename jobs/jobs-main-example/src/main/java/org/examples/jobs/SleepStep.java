package org.examples.jobs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.JobContext;
import org.powerimo.jobs.Result;
import org.powerimo.jobs.Step;
import org.powerimo.jobs.StepResult;

@Slf4j
public class SleepStep implements Step {
    final StepResult stepResult = new StepResult();

    @Override
    @SneakyThrows
    public StepResult run(JobContext jobContext) {
        // DO SOMETHING
        log.info("Some job here");

        for (int i = 0; i < 100; i++) {
            Thread.sleep(10);
        }

        stepResult.setResult(Result.SUCCESS);
        stepResult.getCounterRecordsTotal().set(1L);
        return stepResult;
    }

}
