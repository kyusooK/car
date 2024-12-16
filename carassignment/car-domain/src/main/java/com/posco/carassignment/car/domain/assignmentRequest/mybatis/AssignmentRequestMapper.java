package com.posco.carassignment.car.domain.assignmentRequest.mybatis;

import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestDetailsDTO;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestDetailsResponse;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestSummaryDTO;
import com.posco.carassignment.car.domain.assignmentRequest.mybatis.AssignmentRequestSummaryResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssignmentRequestMapper {
    AssignmentRequestSummaryResponse assignmentRequestSummary(
        AssignmentRequestSummaryDTO dto
    );
    AssignmentRequestDetailsResponse assignmentRequestDetails(
        AssignmentRequestDetailsDTO dto
    );
}
