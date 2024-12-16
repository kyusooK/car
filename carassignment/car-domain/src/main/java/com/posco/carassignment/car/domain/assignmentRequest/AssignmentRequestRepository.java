package com.posco.carassignment.car.domain.assignmentRequest;

import com.posco.carassignment.car.domain.assignmentRequest.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "assignmentRequests",
    path = "assignmentRequests"
)
public interface AssignmentRequestRepository
    extends JpaRepository<AssignmentRequest, Long> {
    //List<AssignmentRequest> assignmentRequestSummary
    //AssignmentRequest assignmentRequestDetails
}
