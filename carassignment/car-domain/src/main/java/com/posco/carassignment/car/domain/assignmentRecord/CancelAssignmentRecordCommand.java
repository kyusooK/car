package com.posco.carassignment.car.domain.assignmentRecord;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancelAssignmentRecordCommand {

    private Long recordId;
    private String cancellationReason;
}
