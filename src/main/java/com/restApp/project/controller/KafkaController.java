package com.restApp.project.controller;

import com.restApp.project.entity.Cat;
import com.restApp.project.kafka.KafkaProducer;
import com.restApp.project.repository.CatRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class KafkaController {

    private final KafkaProducer kafkaProducer;
    private final CatRepo catRepo;

    public KafkaController(KafkaProducer kafkaProducer, CatRepo catRepo) {
        this.kafkaProducer = kafkaProducer;
        this.catRepo = catRepo;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestParam int id){
        Optional<Cat> cat = catRepo.findById(id);
        kafkaProducer.sendMessage(cat.toString());
        return "Done";
    }
}
