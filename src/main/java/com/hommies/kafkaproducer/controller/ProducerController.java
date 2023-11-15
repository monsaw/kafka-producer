package com.hommies.kafkaproducer.controller;

import com.hommies.kafkaproducer.dto.Customer;
import com.hommies.kafkaproducer.service.KafkaMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaMessageProducer messageProducer;

    @GetMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody Customer message){

        try {


                messageProducer.sendKafkaMessage(message);


            return ResponseEntity.ok("message sent successfully..");
        } catch (Exception e) {
            System.out.println("because " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}
