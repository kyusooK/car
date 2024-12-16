package com.posco.carassignment.car.service;

import com.posco.carassignment.car.domain.assignmentRequest.AssignmentRequest;
import com.posco.carassignment.car.domain.assignmentRequest.AssignmentRequestService;
import com.posco.carassignment.car.domain.assignmentRequest.CancelAssignmentRequestCommand;
import com.posco.carassignment.car.domain.assignmentRequest.CreateAssignmentRequestCommand;
import com.posco.carassignment.car.domain.assignmentRequest.UpdateAssignmentRequestCommand;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestDetailsDTO;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestDetailsResponse;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestSummaryDTO;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestSummaryResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class AssignmentRequestController {

    private final AssignmentRequestService assignmentRequestService;

    @Autowired
    public AssignmentRequestController(
        AssignmentRequestService assignmentRequestService
    ) {
        this.assignmentRequestService = assignmentRequestService;
    }

    @GetMapping(path = "/assignmentRequests")
    public ResponseEntity<List<AssignmentRequest>> findAll() {
        return ResponseEntity.ok(assignmentRequestService.findAll());
    }

    @PostMapping(path = "/assignmentRequests")
    public ResponseEntity<AssignmentRequest> create(
        @Valid @RequestBody CreateAssignmentRequestCommand command
    ) {
        return ResponseEntity.ok(assignmentRequestService.create(command));
    }

    @PostMapping(
        path = "/assignmentRequests/updateAssignmentRequest/{requestId}"
    )
    public ResponseEntity<AssignmentRequest> updateAssignmentRequest(
        @PathVariable("requestId") Long requestId,
        @Valid @RequestBody UpdateAssignmentRequestCommand command
    ) {
        AssignmentRequest assignmentRequest = assignmentRequestService.findById(
            requestId
        );

        // 도메인 포트 메서드 직접 호출
        assignmentRequest.updateAssignmentRequest(command);

        return ResponseEntity.ok(
            assignmentRequestService.save(assignmentRequest)
        );
    }

    @PostMapping(
        path = "/assignmentRequests/cancelAssignmentRequest/{requestId}"
    )
    public ResponseEntity<AssignmentRequest> cancelAssignmentRequest(
        @PathVariable("requestId") Long requestId,
        @Valid @RequestBody CancelAssignmentRequestCommand command
    ) {
        AssignmentRequest assignmentRequest = assignmentRequestService.findById(
            requestId
        );

        // 도메인 포트 메서드 직접 호출
        assignmentRequest.cancelAssignmentRequest(command);

        return ResponseEntity.ok(
            assignmentRequestService.save(assignmentRequest)
        );
    }

    //readModel rest api
    @GetMapping(path = "/assignmentRequests/assignmentRequestSummary")
    public ResponseEntity<AssignmentRequestSummaryResponse> assignmentRequestSummary(
        @Valid @RequestBody AssignmentRequestSummaryDTO dto
    ) {
        return ResponseEntity.ok(
            assignmentRequestService.assignmentRequestSummary(dto)
        );
    }

    @GetMapping(path = "/assignmentRequests/assignmentRequestDetails")
    public ResponseEntity<AssignmentRequestDetailsResponse> assignmentRequestDetails(
        @Valid @RequestBody AssignmentRequestDetailsDTO dto
    ) {
        return ResponseEntity.ok(
            assignmentRequestService.assignmentRequestDetails(dto)
        );
    }
}
