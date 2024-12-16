package com.posco.carassignment.car.domain.assignmentRequest;

import com.posco.carassignment.car.domain.assignmentRequest.AssignmentRequest;
import com.posco.carassignment.car.domain.assignmentRequest.AssignmentRequestRepository;
import com.posco.carassignment.car.domain.assignmentRequest.CancelAssignmentRequestCommand;
import com.posco.carassignment.car.domain.assignmentRequest.CreateAssignmentRequestCommand;
import com.posco.carassignment.car.domain.assignmentRequest.UpdateAssignmentRequestCommand;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestDetailsDTO;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestDetailsResponse;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestMapper;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestMapper;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestSummaryDTO;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestSummaryResponse;
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
public class AssignmentRequestService {

    private final AssignmentRequestRepository assignmentRequestRepository;
    private final AssignmentRequestMapper assignmentRequestMapper;

    @Autowired
    public AssignmentRequestService(
        AssignmentRequestRepository assignmentRequestRepository,
        AssignmentRequestMapper assignmentRequestMapper
    ) {
        this.assignmentRequestRepository = assignmentRequestRepository;
        this.assignmentRequestMapper = assignmentRequestMapper;
    }

    public AssignmentRequest create(CreateAssignmentRequestCommand command) {
        AssignmentRequest assignmentRequest = new AssignmentRequest();
        assignmentRequest.setApplicant(command.getApplicant());
        assignmentRequest.setApprovalInfo(command.getApprovalInfo());
        assignmentRequest.setPurpose(command.getPurpose());
        assignmentRequest.setUsageType(command.getUsageType());
        assignmentRequest.setOperationPeriod(command.getOperationPeriod());
        assignmentRequest.setVehicleType(command.getVehicleType());
        assignmentRequest.setNumberOfPassengers(
            command.getNumberOfPassengers()
        );
        assignmentRequest.setDriverIncluded(command.getDriverIncluded());
        assignmentRequest.setContactNumber(command.getContactNumber());
        assignmentRequest.setRemarks(command.getRemarks());
        assignmentRequest.setAttachedDocuments(command.getAttachedDocuments());
        return assignmentRequestRepository.save(assignmentRequest);
    }

    public AssignmentRequest save(AssignmentRequest assignmentRequest) {
        return assignmentRequestRepository.save(assignmentRequest);
    }

    public List<AssignmentRequest> findAll() {
        return assignmentRequestRepository.findAll();
    }

    public AssignmentRequest findById(Long id) {
        return assignmentRequestRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "AssignmentRequest not found"
                )
            );
    }

    //// readModel mybatis
    public AssignmentRequestSummaryResponse assignmentRequestSummary(
        AssignmentRequestSummaryDTO dto
    ) {
        AssignmentRequestSummaryResponse response = assignmentRequestMapper.assignmentRequestSummary(
            dto
        );
        if (response == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "AssignmentRequestSummary not found"
            );
        }
        return response;
    }

    public AssignmentRequestDetailsResponse assignmentRequestDetails(
        AssignmentRequestDetailsDTO dto
    ) {
        AssignmentRequestDetailsResponse response = assignmentRequestMapper.assignmentRequestDetails(
            dto
        );
        if (response == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "AssignmentRequestDetails not found"
            );
        }
        return response;
    }
}
