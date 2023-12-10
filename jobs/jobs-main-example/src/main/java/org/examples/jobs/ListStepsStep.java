package org.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.*;
import org.powerimo.jobs.base.AbstractStep;
import org.powerimo.jobs.std.StdStepResult;

@Slf4j
public class ListStepsStep extends AbstractStep {

    @Override
    protected StepResult doRun() {
        log.info("Steps state list");
        final StdStepResult result = new StdStepResult();

        var list = getContext().getRunner().getConfiguration().getStateRepository().getJobStepsStateList(getContext().getJobState().getId());
        list.forEach(item -> {
            result.getCounterTotal().incrementAndGet();
            log.info("step: {}", item);
        });

        result.setResult(Result.SUCCESS);
        return result;
    }
}
