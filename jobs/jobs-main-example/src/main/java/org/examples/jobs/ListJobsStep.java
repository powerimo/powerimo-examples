package org.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.JobContext;
import org.powerimo.jobs.Result;
import org.powerimo.jobs.Step;
import org.powerimo.jobs.StepResult;
import org.powerimo.jobs.base.AbstractStep;
import org.powerimo.jobs.std.StdStepResult;

@Slf4j
public class ListJobsStep extends AbstractStep {

    @Override
    protected StepResult doRun() {
        log.info("Jobs state list");
        final StdStepResult result = new StdStepResult();


/*
        if (jobContext.getRunner() != null && jobContext.getRunner().getStateRepository() != null) {
            var list = jobContext.getRunner().getStateRepository().getJobStateList();
            list.forEach(item -> {
                result.getCounterRecordsTotal().incrementAndGet();
                log.info("job: {}", item);
            });
        }
*/

        result.setResult(Result.SUCCESS);
        return result;
    }
}
