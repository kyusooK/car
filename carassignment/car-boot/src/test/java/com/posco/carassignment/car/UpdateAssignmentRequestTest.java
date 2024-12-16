
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

import com.posco.carassignment.car.domain.assignmentRequest.AssignmentRequest;
import com.posco.carassignment.car.domain.assignmentRequest.AssignmentRequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateAssignmentRequestTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAssignmentRequestTest.class);
   
   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   public AssignmentRequestRepository repository;

   @Test
   @SuppressWarnings("unchecked")
   public void test0() {

      //given:  
      AssignmentRequest existingEntity = new AssignmentRequest();

      existingEntity.setRequestId(1L);
      existingEntity.setApplicant([object Object]);
      existingEntity.setApprovalInfo([object Object]);
      existingEntity.setRequestStatus("PENDING");
      existingEntity.setPurpose("업무 회의 참석");
      existingEntity.setUsageType("BUSINESS_SUPPORT");
      existingEntity.setOperationPeriod([object Object]);
      existingEntity.setVehicleType("SEDAN");
      existingEntity.setNumberOfPassengers(3L);
      existingEntity.setDriverIncluded("YES");
      existingEntity.setContactNumber("010-1234-5678");
      existingEntity.setRemarks("기타 요청사항 :");
      existingEntity.setAttachedDocuments(new Object[]{"document1.pdf", "document2.pdf"});

      repository.save(existingEntity);

      //when:  

  
      
      try {


      UpdateAssignmentRequestCommand command = new UpdateAssignmentRequestCommand();

         command.setRequestId(1L);
         command.setPurpose("업무 회의 참석 수정");
         command.setUsageType("EXTERNAL_ACTIVITY");
         command.setOperationPeriod([object Object]);
         command.setVehicleType("VAN");
         command.setNumberOfPassengers(5L);
         command.setDriverIncluded("NO");
         command.setContactNumber("010-5678-1234");
         command.setRemarks("수정 요청사항 :");
         command.setAttachedDocuments(new Object[]{"updatedDocument1.pdf"});
      
      existingEntity.updateAssignmentRequest(command);
         //then:
         AssignmentRequest result = repository.findById(existingEntity.getRequestId()).get();

         LOGGER.info("Response received: {}", result);

         assertEquals(result.getRequestId(), 1L);
         assertEquals(result.getPurpose(), "업무 회의 참석 수정");
         assertEquals(result.getUsageType(), "EXTERNAL_ACTIVITY");
         assertEquals(result.getOperationPeriod(), [object Object]);
         assertEquals(result.getVehicleType(), "VAN");
         assertEquals(result.getNumberOfPassengers(), 5L);
         assertEquals(result.getDriverIncluded(), "NO");
         assertEquals(result.getContactNumber(), "010-5678-1234");
         assertEquals(result.getRemarks(), "수정 요청사항 :");
         assertEquals(result.getAttachedDocuments(), new Object[]{"updatedDocument1.pdf"});


      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
