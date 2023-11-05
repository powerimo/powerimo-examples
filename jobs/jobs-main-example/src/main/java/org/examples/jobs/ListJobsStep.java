package org.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.JobContext;
import org.powerimo.jobs.Result;
import org.powerimo.jobs.Step;
import org.powerimo.jobs.StepResult;

@Slf4j
public class ListJobsStep implements Step {
    @Override
    public StepResult run(JobContext jobContext) throws Exception {
        log.info("Jobs state list");
        final StepResult result = new StepResult();
        if (jobContext.getRunner() != null && jobContext.getRunner().getStateRepository() != null) {
            var list = jobContext.getRunner().getStateRepository().getJobStateList();
            list.forEach(item -> {
                result.getCounterRecordsTotal().incrementAndGet();
                log.info("job: {}", item);
            });
        }

        result.setResult(Result.SUCCESS);
        return result;
    }
}
