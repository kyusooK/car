package com.posco.carassignment.car.domain.assignmentRequest;

import com.posco.carassignment.car.domain.DriverIncluded;
import com.posco.carassignment.car.domain.File;
import com.posco.carassignment.car.domain.OperationPeriod;
import com.posco.carassignment.car.domain.UsageType;
import com.posco.carassignment.car.domain.VehicleType;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateAssignmentRequestCommand {

    private Long requestId;
    private String purpose;
    private UsageType usageType;
    private OperationPeriod operationPeriod;
    private VehicleType vehicleType;
    private Integer numberOfPassengers;
    private DriverIncluded driverIncluded;
    private String contactNumber;
    private String remarks;
    private List<File> attachedDocuments;
}