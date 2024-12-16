package com.posco.carassignment.car.domain.assignmentRequest.mybatis;

import lombok.Data;
import java.util.Date;

import com.posco.carassignment.car.domain.Applicant;import com.posco.carassignment.car.domain.ApprovalInfo;import com.posco.carassignment.car.domain.UsageType;import com.posco.carassignment.car.domain.OperationPeriod;import com.posco.carassignment.car.domain.VehicleType;import com.posco.carassignment.car.domain.DriverIncluded;import com.posco.carassignment.car.domain.List&lt;File&gt;;


@Data
public class AssignmentRequestDetailsDTO {
        private Long requestId;
        private Applicant applicant;
        private ApprovalInfo approvalInfo;
        private String purpose;
        private UsageType usageType;
        private OperationPeriod operationPeriod;
        private VehicleType vehicleType;
        private Integer numberOfPassengers;
        private DriverIncluded driverIncluded;
        private String contactNumber;
        private String remarks;
        private List&lt;File&gt; attachedDocuments;
}
