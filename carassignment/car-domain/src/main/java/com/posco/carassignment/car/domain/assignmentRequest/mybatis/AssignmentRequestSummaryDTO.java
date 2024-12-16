package com.posco.carassignment.car.domain.assignmentRequest.mybatis;

import com.posco.carassignment.car.domain.OperationPeriod;
import com.posco.carassignment.car.domain.RequestStatus;
import com.posco.carassignment.car.domain.VehicleType;
import java.util.Date;
import lombok.Data;

@Data
public class AssignmentRequestSummaryDTO {

    private Long requestId;
    private String applicantName;
    private VehicleType vehicleType;
    private OperationPeriod operationPeriod;
    private RequestStatus status;
}
