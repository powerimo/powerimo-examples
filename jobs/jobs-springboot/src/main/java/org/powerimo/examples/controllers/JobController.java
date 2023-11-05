package org.powerimo.examples.controllers;

import lombok.RequiredArgsConstructor;
import org.powerimo.examples.dto.JobStateDto;
import org.powerimo.examples.dto.StepStateDto;
import org.powerimo.examples.services.JobService;
import org.powerimo.jobs.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final Runner runner;
    private final JobService jobService;

    @GetMapping("running")
    public List<JobStateDto> getJobs() {
        var list = runner.getStateRepository().getJobStateList();
        return JobStateDto.of(list);
    }

    @GetMapping("running/steps")
    public List<StepStateDto> getSteps() {
        var list = runner.getStateRepository().getStepStateList();
        return StepStateDto.of(list);
    }

    @GetMapping("job-descriptors")
    public List<JobDescriptor> getJobDescriptors() {
        return runner.getRepository().getJobDescriptors();
    }

    @GetMapping("job-descriptors/{code}/steps")
    public List<StepDescriptor> getStepDescriptors(@PathVariable String code) {
        return runner.getRepository().getStepDescriptors(code);
    }

    @PostMapping("{code}/run")
    public JobStateDto postJobRun(@PathVariable String code) throws NoSuchMethodException {
        var info = jobService.runJob(code);
        return JobStateDto.of(info);
    }
}
