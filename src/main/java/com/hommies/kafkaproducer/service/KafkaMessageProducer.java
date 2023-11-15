package com.hommies.kafkaproducer.service;

import com.hommies.kafkaproducer.dto.Customer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessageProducer {

    private final KafkaTemplate<String, Object> template;

    public KafkaMessageProducer(KafkaTemplate<String, Object> template) {
        this.template = template;
    }


    public void sendKafkaMessage(Customer customer){


       try {

           CompletableFuture<SendResult<String, Object>> future = template.send("hommies-demo-7", customer);

           future.whenComplete((result, ex) -> {
               if(ex == null){
                   System.out.println("Sent Message = [ " + customer.toString() + " ] with offset =[ "
                           + result.getRecordMetadata().offset() + " ]");
               }

               else {

                   System.out.println("Unable to send message : [" + customer +
                           "] due to [ " + ex.getMessage() + " ]");
               }

           });
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }
}
