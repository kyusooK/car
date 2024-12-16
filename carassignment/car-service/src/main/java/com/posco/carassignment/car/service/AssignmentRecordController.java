package com.posco.carassignment.car.service;

import com.posco.carassignment.car.domain.assignmentRecord.AssignmentRecord;
import com.posco.carassignment.car.domain.assignmentRecord.AssignmentRecordService;
import com.posco.carassignment.car.domain.assignmentRecord.CancelAssignmentRecordCommand;
import com.posco.carassignment.car.domain.assignmentRecord.CreateAssignmentRecordCommand;
import com.posco.carassignment.car.domain.assignmentRecord.UpdateAssignmentRecordCommand;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordDetailsDTO;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordDetailsResponse;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordSummaryDTO;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordSummaryResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class AssignmentRecordController {

    private final AssignmentRecordService assignmentRecordService;

    @Autowired
    public AssignmentRecordController(
        AssignmentRecordService assignmentRecordService
    ) {
        this.assignmentRecordService = assignmentRecordService;
    }

    @GetMapping(path = "/assignmentRecords")
    public ResponseEntity<List<AssignmentRecord>> findAll() {
        return ResponseEntity.ok(assignmentRecordService.findAll());
    }

    @PostMapping(path = "/assignmentRecords")
    public ResponseEntity<AssignmentRecord> create(
        @Valid @RequestBody CreateAssignmentRecordCommand command
    ) {
        return ResponseEntity.ok(assignmentRecordService.create(command));
    }

    @PostMapping(path = "/assignmentRecords/updateAssignmentRecord/{recordId}")
    public ResponseEntity<AssignmentRecord> updateAssignmentRecord(
        @PathVariable("recordId") Long recordId,
        @Valid @RequestBody UpdateAssignmentRecordCommand command
    ) {
        AssignmentRecord assignmentRecord = assignmentRecordService.findById(
            recordId
        );

        // 도메인 포트 메서드 직접 호출
        assignmentRecord.updateAssignmentRecord(command);

        return ResponseEntity.ok(
            assignmentRecordService.save(assignmentRecord)
        );
    }

    @PostMapping(path = "/assignmentRecords/cancelAssignmentRecord/{recordId}")
    public ResponseEntity<AssignmentRecord> cancelAssignmentRecord(
        @PathVariable("recordId") Long recordId,
        @Valid @RequestBody CancelAssignmentRecordCommand command
    ) {
        AssignmentRecord assignmentRecord = assignmentRecordService.findById(
            recordId
        );

        // 도메인 포트 메서드 직접 호출
        assignmentRecord.cancelAssignmentRecord(command);

        return ResponseEntity.ok(
            assignmentRecordService.save(assignmentRecord)
        );
    }

    //readModel rest api
    @GetMapping(path = "/assignmentRecords/assignmentRecordSummary")
    public ResponseEntity<AssignmentRecordSummaryResponse> assignmentRecordSummary(
        @Valid @RequestBody AssignmentRecordSummaryDTO dto
    ) {
        return ResponseEntity.ok(
            assignmentRecordService.assignmentRecordSummary(dto)
        );
    }

    @GetMapping(path = "/assignmentRecords/assignmentRecordDetails")
    public ResponseEntity<AssignmentRecordDetailsResponse> assignmentRecordDetails(
        @Valid @RequestBody AssignmentRecordDetailsDTO dto
    ) {
        return ResponseEntity.ok(
            assignmentRecordService.assignmentRecordDetails(dto)
        );
    }
}
