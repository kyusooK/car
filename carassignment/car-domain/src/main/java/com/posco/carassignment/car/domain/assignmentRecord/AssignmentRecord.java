package com.posco.carassignment.car.domain.assignmentRecord;

import com.posco.carassignment.car.domain.AssignmentRequestReference;
import com.posco.carassignment.car.domain.AssignmentStatus;
import com.posco.carassignment.car.domain.OperationPeriod;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AssignmentRecord_table")
@Data
public class AssignmentRecord {

    @Id
    private Long recordId;

    @Embedded
    private AssignmentRequestReference assignmentRequestReference;

    @Embedded
    private OperationPeriod operationPeriod;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    private String remarks;

    public void updateAssignmentRecord(UpdateAssignmentRecordCommand command) {
        // 비즈니스 로직 구현
    }

    public void cancelAssignmentRecord(CancelAssignmentRecordCommand command) {
        // 비즈니스 로직 구현
    }
}
