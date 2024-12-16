package com.posco.carassignment.car.domain.assignmentRecord.mybatis;

import com.posco.carassignment.car.domain.AssignmentRequestReference;
import com.posco.carassignment.car.domain.AssignmentStatus;
import com.posco.carassignment.car.domain.OperationPeriod;
import com.posco.carassignment.car.domain.UsagePurpose;
import java.util.Date;
import lombok.Data;

@Data
public class AssignmentRecordDetailsDTO {

    private Long recordId;
    private AssignmentRequestReference assignmentRequestReference;
    private OperationPeriod operationPeriod;
    private UsagePurpose usagePurpose;
    private AssignmentStatus status;
    private String remarks;
}
