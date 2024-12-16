package com.posco.carassignment.car.domain.assignmentRequest;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancelAssignmentRequestCommand {

    private Long requestId;
    private String cancellationReason;
}
