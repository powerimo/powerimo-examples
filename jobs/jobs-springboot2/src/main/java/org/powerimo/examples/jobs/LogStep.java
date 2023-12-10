package org.powerimo.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.*;
import org.powerimo.jobs.std.StdStepResult;

@Slf4j
public class LogStep implements Step {


    @Override
    public StepResult run(JobContext jobContext, StepDescriptor stepDescriptor) {
        log.info("the step execution");

        StdStepResult result = new StdStepResult();
        result.setResult(Result.SUCCESS);
        result.setMessage("OK");
        result.getCounterTotal().set(1);
        return result;
    }
}
