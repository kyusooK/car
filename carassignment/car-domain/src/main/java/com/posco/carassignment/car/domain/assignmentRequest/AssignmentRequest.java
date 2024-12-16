package com.posco.carassignment.car.domain.assignmentRequest;

import com.posco.carassignment.car.domain.Applicant;
import com.posco.carassignment.car.domain.ApprovalInfo;
import com.posco.carassignment.car.domain.File;
import com.posco.carassignment.car.domain.OperationPeriod;
import com.posco.carassignment.car.domain.RequestStatus;
import com.posco.carassignment.car.domain.UsageType;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AssignmentRequest_table")
@Data
public class AssignmentRequest {

    @Id
    private Long requestId;

    @Embedded
    private Applicant applicant;

    @Embedded
    private ApprovalInfo approvalInfo;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private String purpose;

    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    @Embedded
    private OperationPeriod operationPeriod;

    private Integer numberOfPassengers;

    private String contactNumber;

    private String remarks;

    @Embedded
    private File attachedDocuments;

    public void updateAssignmentRequest(
        UpdateAssignmentRequestCommand command
    ) {
        // 비즈니스 로직 구현
    }

    public void cancelAssignmentRequest(
        CancelAssignmentRequestCommand command
    ) {
        // 비즈니스 로직 구현
    }
}
