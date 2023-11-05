package org.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.JobContext;
import org.powerimo.jobs.Result;
import org.powerimo.jobs.Step;
import org.powerimo.jobs.StepResult;

@Slf4j
public class ListStepsStep implements Step {

    @Override
    public StepResult run(JobContext jobContext) throws Exception {
        log.info("Steps state list");
        final StepResult result = new StepResult();

        if (jobContext.getRunner() != null && jobContext.getRunner().getStateRepository() != null) {
            var list = jobContext.getRunner().getStateRepository().getStepStateList();
            list.forEach(item -> {
                result.getCounterRecordsTotal().incrementAndGet();
                log.info("step: {}", item);
            });
        }

        result.setResult(Result.SUCCESS);
        return result;
    }
}
