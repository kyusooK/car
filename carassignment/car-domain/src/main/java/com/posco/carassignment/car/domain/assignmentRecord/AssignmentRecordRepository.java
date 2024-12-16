package com.posco.carassignment.car.domain.assignmentRecord;

import com.posco.carassignment.car.domain.assignmentRecord.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "assignmentRecords",
    path = "assignmentRecords"
)
public interface AssignmentRecordRepository
    extends JpaRepository<AssignmentRecord, Long> {
    //List<AssignmentRecord> assignmentRecordSummary
    //AssignmentRecord assignmentRecordDetails
}
