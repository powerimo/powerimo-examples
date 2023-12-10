package org.powerimo.examples.jobs;

import lombok.extern.slf4j.Slf4j;
import org.powerimo.jobs.*;
import org.powerimo.jobs.std.StdStepResult;

@Slf4j
public class LongStep implements Step, IdSupport {
    private String id;

    @Override
    public StepResult run(JobContext jobContext, StepDescriptor stepDescriptor) throws Exception {
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
        id = s;
    }
}
