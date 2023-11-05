package org.powerimo.examples.dto;

import lombok.Data;
import lombok.Getter;
import org.powerimo.jobs.JobResult;
import org.powerimo.jobs.JobStateInfo;
import org.powerimo.jobs.Status;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class JobStateDto {
    private final String id;
    private final JobResult result;
    private final Status status;
    private final Instant startedAt;
    private final Instant completedAt;

    public JobStateDto(JobStateInfo info) {
        id = info.getId();
        result = info.getResult();
        startedAt = info.getStartedAt();
        completedAt = info.getCompletedAt();
        status = info.getStatus();
    }

    public static List<JobStateDto> of(List<JobStateInfo> list) {
        return list.stream()
                .map(JobStateDto::new)
                .collect(Collectors.toList());
    }

    public static JobStateDto of (JobStateInfo info) {
        return new JobStateDto(info);
    }
}
