
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
public class CancelAssignmentRecordTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(CancelAssignmentRecordTest.class);
   
   @Autowired
   private ApplicationContext applicationContext;

   @Autowired
   public AssignmentRecordRepository repository;

   @Test
   @SuppressWarnings("unchecked")
   public void test0() {

      //given:  
      AssignmentRecord existingEntity = new AssignmentRecord();

      existingEntity.setRecordId(2001L);
      existingEntity.setAssignmentRequestReference([object Object]);
      existingEntity.setOperationPeriod([object Object]);
      existingEntity.setUsagePurpose([object Object]);
      existingEntity.setStatus("PENDING");
      existingEntity.setRemarks("N/A");

      repository.save(existingEntity);

      //when:  

  
      
      try {


      CancelAssignmentRecordCommand command = new CancelAssignmentRecordCommand();

         command.setRecordId(2001L);
         command.setCancellationReason("일정 변경으로 인한 취소");
      
      existingEntity.cancelAssignmentRecord(command);
         //then:
         AssignmentRecord result = repository.findById(existingEntity.getRecordId()).get();

         LOGGER.info("Response received: {}", result);

         assertEquals(result.getRecordId(), 2001L);
         assertEquals(result.getCancellationReason(), "일정 변경으로 인한 취소");
         assertEquals(result.getCancelledAt(), "2023-10-16T00:00:00Z");


      } catch (JsonProcessingException e) {
         e.printStackTrace();
         assertTrue(e.getMessage(), false);
      }

     
   }

}
