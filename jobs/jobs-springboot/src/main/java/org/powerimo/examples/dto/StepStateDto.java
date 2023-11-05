package org.powerimo.examples.dto;

import lombok.Data;
import org.powerimo.jobs.Status;
import org.powerimo.jobs.StepResult;
import org.powerimo.jobs.StepStateInfo;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StepStateDto {
    private StepResult result;
    private Instant startedAt;
    private Instant completedAt;
    private Status status;

    public StepStateDto(StepStateInfo info) {
        result = info.getResult();
        status = info.getStatus();
        startedAt = info.getStartedAt();
        completedAt = info.getCompletedAt();
    }

    public static List<StepStateDto> of(List<StepStateInfo> list) {
        return list.stream()
                .map(StepStateDto::new)
                .collect(Collectors.toList());
    }
}
