package org.powerimo.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.JobContext;
import org.powerimo.jobs.Result;
import org.powerimo.jobs.Step;
import org.powerimo.jobs.StepResult;

@Slf4j
public class LogStep implements Step {

    @Override
    public StepResult run(JobContext jobContext) throws Exception {
        log.info("the step execution");

        StepResult result = new StepResult();
        result.setResult(Result.SUCCESS);
        result.setMessage("OK");
        result.getCounterRecordsTotal().set(1);
        return result;
    }
}
