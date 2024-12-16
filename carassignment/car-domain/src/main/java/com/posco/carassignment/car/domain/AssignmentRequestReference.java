package com.posco.carassignment.car.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentRequestReference {

    private String ApplicantName;

    private Integer NumberOfPassengers;

    private AssignmentStatus assignmentStatus;
}
//>>> DDD / Value Object
