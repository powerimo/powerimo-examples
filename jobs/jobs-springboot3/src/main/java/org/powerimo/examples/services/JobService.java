package org.powerimo.examples.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.powerimo.examples.dto.JobStateDto;
import org.powerimo.examples.jobs.LogStep;
import org.powerimo.examples.jobs.LongBeanStep;
import org.powerimo.examples.jobs.LongStep;
import org.powerimo.jobs.*;
import org.powerimo.jobs.boot3.StdSpringJob;
import org.powerimo.jobs.boot3.repositories.JobRepository;
import org.powerimo.jobs.boot3.repositories.StepRepository;
import org.powerimo.jobs.std.StdJobDescriptor;
import org.powerimo.jobs.std.StdJobDisplayParameters;
import org.powerimo.jobs.std.StdStepDescriptor;
import org.powerimo.jobs.std.steps.StdJobBannerStep;
import org.powerimo.jobs.std.steps.StdLogJobStatStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {
    private final Runner runner;
    private final static String JOB_1 = "JOB_1";
    private JobRepository jobJpaRepository;
    private StepRepository stepJpaRepository;

    public DescriptorRepository getDescriptorRepository() {
        return runner.getConfiguration().getDescriptorRepository();
    }

    public StateRepository geStateRepository() {
        return runner.getConfiguration().getStateRepository();
    }

    @Autowired
    public void setJobJpaRepository(JobRepository jpaRepository) {
        this.jobJpaRepository = jpaRepository;
    }

    @Autowired
    public void setStepJpaRepository(StepRepository jpaRepository) {
        this.stepJpaRepository = jpaRepository;;
    }

    @PostConstruct
    public void init() {
        JobDescriptor jobDescriptor = StdJobDescriptor.create(JOB_1, "Sample Job", StdSpringJob.class);
        getDescriptorRepository().addJobDescriptor(jobDescriptor);

        getDescriptorRepository().addStepDescriptor(StdStepDescriptor.of("JOB_BANNER", "Banner", 5, StdJobBannerStep.class, JOB_1));
        getDescriptorRepository().addStepDescriptor(StdStepDescriptor.of("STEP01", "log", 10, LogStep.class, JOB_1));
        getDescriptorRepository().addStepDescriptor(StdStepDescriptor.of("LONG_STEP_1", "long step 1", 20, LongBeanStep.class, JOB_1));
        getDescriptorRepository().addStepDescriptor(StdStepDescriptor.of("LONG_STEP_2", "long step 2", 30, LongStep.class, JOB_1));
        getDescriptorRepository().addStepDescriptor(StdStepDescriptor.of("STEP03", "log again", 40, LogStep.class, JOB_1));
        getDescriptorRepository().addStepDescriptor(StdStepDescriptor.of("JOB_STAT", "Job statistics", 100, StdLogJobStatStep.class, JOB_1));
    }

    public JobState runJob(String code, Object params) throws NoSuchMethodException {
        StdJobDisplayParameters displayParameters = StdJobDisplayParameters.builder()
                .title("Sample job with code: " + code)
                .build();
        return runner.runArgs(code, displayParameters, params);
    }

    public List<JobState> getRunningJobs() {
        return geStateRepository().getRunningJobStateList();
    }

    public List<JobStateDto> getDbJobs() {
        if (jobJpaRepository == null)
            throw new RuntimeException("Jobs JPA repository is not specified");

        List<JobStateDto> list = new ArrayList<>();
        jobJpaRepository.findAll().forEach(item -> list.add(JobStateDto.of(item)));
        return list;
    }


}
