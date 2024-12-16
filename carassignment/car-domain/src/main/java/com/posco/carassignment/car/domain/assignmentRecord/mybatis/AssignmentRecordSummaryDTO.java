package com.posco.carassignment.car.domain.assignmentRecord.mybatis;

import com.posco.carassignment.car.domain.AssignmentStatus;
import com.posco.carassignment.car.domain.OperationPeriod;
import com.posco.carassignment.car.domain.VehicleType;
import java.util.Date;
import lombok.Data;

@Data
public class AssignmentRecordSummaryDTO {

    private Long recordId;
    private String applicantName;
    private VehicleType vehicleType;
    private OperationPeriod operationPeriod;
    private AssignmentStatus status;
}
