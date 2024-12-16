package com.posco.carassignment.car.domain.assignmentRecord.mybatis;

import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordDetailsDTO;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordDetailsResponse;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordSummaryDTO;
import com.posco.carassignment.car.domain.assignmentRecord.mybatis.AssignmentRecordSummaryResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssignmentRecordMapper {
    AssignmentRecordSummaryResponse assignmentRecordSummary(
        AssignmentRecordSummaryDTO dto
    );
    AssignmentRecordDetailsResponse assignmentRecordDetails(
        AssignmentRecordDetailsDTO dto
    );
}
