package org.powerimo.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.Result;
import org.powerimo.jobs.StepResult;
import org.powerimo.jobs.base.AbstractStep;
import org.powerimo.jobs.std.StdStepResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
@Slf4j
public class LongBeanStep extends AbstractStep {

    @Override
    protected StepResult doRun() throws Exception {
        StdStepResult result = new StdStepResult();

        for (int i=0; i<1000; i++) {
            Thread.sleep(10);
            result.getCounterTotal().incrementAndGet();
            if (i % 100 == 0) {
                log.info("i={}", i);
            }
        }

        result.setResult(Result.SUCCESS);
        return result;
    }
}
