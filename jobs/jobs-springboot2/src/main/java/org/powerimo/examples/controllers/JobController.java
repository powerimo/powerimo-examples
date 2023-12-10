package org.powerimo.examples.controllers;

import lombok.RequiredArgsConstructor;
import org.powerimo.examples.dto.JobStateDto;
import org.powerimo.examples.dto.StepStateDto;
import org.powerimo.examples.services.JobService;
import org.powerimo.jobs.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final Runner runner;
    private final JobService jobService;

    @GetMapping("running")
    public List<JobStateDto> getJobs() {
        var list = jobService.getRunningJobs();
        return JobStateDto.of(list);
    }

    @GetMapping("job-descriptors")
    public List<JobDescriptor> getJobDescriptors() {
        return jobService.getDescriptorRepository().getJobDescriptors();
    }

    @GetMapping("job-descriptors/{code}/steps")
    public List<StepDescriptor> getStepDescriptors(@PathVariable String code) {
        return jobService.getDescriptorRepository().getStepDescriptors(code);
    }

    @PostMapping("{code}/run")
    public JobStateDto postJobRun(@PathVariable String code, @RequestBody HashMap<String, String> params) throws NoSuchMethodException {
        var state = jobService.runJob(code, params);
        return JobStateDto.of(state);
    }

    @GetMapping("db")
    public List<JobStateDto> getJDbobStateList() {
        return jobService.getDbJobs();
    }
}
