package com.posco.carassignment.car.domain.assignmentRecord;

import com.posco.carassignment.car.domain.AssignmentRequestReference;
import com.posco.carassignment.car.domain.OperationPeriod;
import com.posco.carassignment.car.domain.UsagePurpose;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateAssignmentRecordCommand {

    private AssignmentRequestReference assignmentRequestReference;
    private OperationPeriod operationPeriod;
    private UsagePurpose usagePurpose;
    private String remarks;
}
