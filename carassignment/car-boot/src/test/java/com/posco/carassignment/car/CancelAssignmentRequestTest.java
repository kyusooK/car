
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
public class CancelAssignmentRequestTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(CancelAssignmentRequestTest.class);
   
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


      CancelAssignmentRequestCommand command = new CancelAssignmentRequestCommand();

         command.setRequestId(1L);
         command.setCancellationReason("회의 일정 변경으로 취소");
      
      existingEntity.cancelAssignmentRequest(command);
         //then:
         AssignmentRequest result = repository.findById(existingEntity.getRequestId()).get();

         LOGGER.info("Response received: {}", result);

         assertEquals(result.getRequestId(), 1L);
         assertEquals(result.getCancellationReason(), "회의 일정 변경으로 취소");
         assertEquals(result.getCancelledAt(), "2024-03-02T12:00:00Z");


      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
