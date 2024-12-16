package com.posco.carassignment.car.domain.assignmentRecord;

import com.posco.carassignment.car.domain.OperationPeriod;
import com.posco.carassignment.car.domain.UsagePurpose;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateAssignmentRecordCommand {

    private Long recordId;
    private OperationPeriod operationPeriod;
    private UsagePurpose usagePurpose;
    private String remarks;
}
