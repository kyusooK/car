package com.posco.carassignment.car.domain.assignmentRecord;

import com.posco.carassignment.car.domain.assignmentRecord.AssignmentRecord;
import com.posco.carassignment.car.domain.assignmentRecord.AssignmentRecordRepository;
import com.posco.carassignment.car.domain.assignmentRecord.CancelAssignmentRecordCommand;
import com.posco.carassignment.car.domain.assignmentRecord.CreateAssignmentRecordCommand;
import com.posco.carassignment.car.domain.assignmentRecord.UpdateAssignmentRecordCommand;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordDetailsDTO;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordDetailsResponse;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordMapper;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordMapper;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordSummaryDTO;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordSummaryResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class AssignmentRecordService {

    private final AssignmentRecordRepository assignmentRecordRepository;
    private final AssignmentRecordMapper assignmentRecordMapper;

    @Autowired
    public AssignmentRecordService(
        AssignmentRecordRepository assignmentRecordRepository,
        AssignmentRecordMapper assignmentRecordMapper
    ) {
        this.assignmentRecordRepository = assignmentRecordRepository;
        this.assignmentRecordMapper = assignmentRecordMapper;
    }

    public AssignmentRecord create(CreateAssignmentRecordCommand command) {
        AssignmentRecord assignmentRecord = new AssignmentRecord();
        assignmentRecord.setAssignmentRequestReference(
            command.getAssignmentRequestReference()
        );
        assignmentRecord.setOperationPeriod(command.getOperationPeriod());
        assignmentRecord.setUsagePurpose(command.getUsagePurpose());
        assignmentRecord.setRemarks(command.getRemarks());
        return assignmentRecordRepository.save(assignmentRecord);
    }

    public AssignmentRecord save(AssignmentRecord assignmentRecord) {
        return assignmentRecordRepository.save(assignmentRecord);
    }

    public List<AssignmentRecord> findAll() {
        return assignmentRecordRepository.findAll();
    }

    public AssignmentRecord findById(Long id) {
        return assignmentRecordRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "AssignmentRecord not found"
                )
            );
    }

    //// readModel mybatis
    public AssignmentRecordSummaryResponse assignmentRecordSummary(
        AssignmentRecordSummaryDTO dto
    ) {
        AssignmentRecordSummaryResponse response = assignmentRecordMapper.assignmentRecordSummary(
            dto
        );
        if (response == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "AssignmentRecordSummary not found"
            );
        }
        return response;
    }

    public AssignmentRecordDetailsResponse assignmentRecordDetails(
        AssignmentRecordDetailsDTO dto
    ) {
        AssignmentRecordDetailsResponse response = assignmentRecordMapper.assignmentRecordDetails(
            dto
        );
        if (response == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "AssignmentRecordDetails not found"
            );
        }
        return response;
    }
}
