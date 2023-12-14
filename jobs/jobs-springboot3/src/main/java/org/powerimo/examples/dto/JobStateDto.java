package org.powerimo.examples.dto;

import lombok.Data;
import org.powerimo.jobs.JobResult;
import org.powerimo.jobs.JobState;
import org.powerimo.jobs.Status;
import org.powerimo.jobs.boot3.entities.JobEntity;
import org.powerimo.jobs.std.StdJobResult;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JobStateDto {
    private final String id;
    private final String title;
    private final JobResult result;
    private final Status status;
    private final Instant startedAt;
    private final Instant completedAt;
    private final StepStateDto currentStep;
    private final String parameters;

    public JobStateDto(JobState state) {
        id = state.getId();
        result = state.getJobResult();
        startedAt = state.getStartedAt();
        completedAt = state.getCompletedAt();
        status = state.getStatus();
        title = state.getTitle();
        if (state.getArguments() != null) {
            parameters = state.getArguments().toString();
        } else {
            parameters = null;
        }
        if (state.getCurrentStep() != null) {
            currentStep = new StepStateDto(state.getCurrentStep());
        } else {
            currentStep = null;
        }
    }

    public JobStateDto(JobEntity entity) {
        id = entity.getId();
        startedAt = entity.getStartedAt();
        completedAt = entity.getCompletedAt();
        status = entity.getStatus();
        parameters = entity.getParameters();
        title = entity.getTitle();
        result = new StdJobResult(entity.getResult(), false, entity.getResultMessage());
        currentStep = null;
    }

    public static List<JobStateDto> of(List<JobState> list) {
        return list.stream()
                .map(JobStateDto::new)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new JobStateDto object from a JobState.
     *
     * @param state The JobState object to convert.
     * @return The JobStateDto object created from the JobState.
     */
    public static JobStateDto of (JobState state) {
        return new JobStateDto(state);
    }

    /**
     * Calculates the duration of the job execution.
     *
     * @return The duration of the job execution as a Duration object.
     */
    public Duration getDuration() {
        var endTime = completedAt != null ? completedAt : Instant.now();
        return Duration.between(startedAt, endTime);
    }

    public static JobStateDto of(JobEntity entity) {
        return new JobStateDto(entity);
    }

}
