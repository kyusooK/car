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
public class Applicant {

    private String Name;

    private String Department;

    private String EmployeeId;

    private String PhoneNumber;

    private String MobileNumber;

    private Date ApplicationDate;
}
//>>> DDD / Value Object
