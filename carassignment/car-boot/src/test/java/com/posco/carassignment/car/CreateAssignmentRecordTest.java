
package com.posco.carassignment.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.posco.carassignment.car.domain.assignmentRecord.AssignmentRecord;
import com.posco.carassignment.car.domain.assignmentRecord.AssignmentRecordRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateAssignmentRecordTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateAssignmentRecordTest.class);
   
   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   public AssignmentRecordRepository repository;

   @Test
   @SuppressWarnings("unchecked")
   public void test0() {

      //given:  
      AssignmentRecord existingEntity = new AssignmentRecord();

      existingEntity.setRequestId(1001L);
      existingEntity.setApplicant([object Object]);
      existingEntity.setApprovalInfo([object Object]);
      existingEntity.setRequestStatus("PENDING");
      existingEntity.setPurpose("업무 지원");
      existingEntity.setUsageType("BUSINESS_SUPPORT");
      existingEntity.setOperationPeriod([object Object]);
      existingEntity.setVehicleType("SEDAN");
      existingEntity.setNumberOfPassengers(3L);
      existingEntity.setDriverIncluded("YES");
      existingEntity.setContactNumber("010-1234-5678");
      existingEntity.setRemarks("N/A");
      existingEntity.setAttachedDocuments(new Object[]{"document1.pdf"});

      repository.save(existingEntity);

      //when:  

  
      
      try {

      AssignmentRecord newEntity = new AssignmentRecord();

         newEntity.setAssignmentRequestReference([object Object]);
         newEntity.setOperationPeriod([object Object]);
         newEntity.setUsagePurpose([object Object]);
         newEntity.setRemarks("N/A");

      repository.save(newEntity);


   

         //then:
         AssignmentRecord result = repository.findById(existingEntity.getRecordId()).get();

         LOGGER.info("Response received: {}", result);

         assertEquals(result.getRecordId(), 2001L);
         assertEquals(result.getAssignmentRequestReference(), [object Object]);
         assertEquals(result.getOperationPeriod(), [object Object]);
         assertEquals(result.getUsagePurpose(), [object Object]);
         assertEquals(result.getStatus(), "PENDING");
         assertEquals(result.getRemarks(), "N/A");


      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
