package org.example.kafkaspringstream.controllers;

import org.example.kafkaspringstream.Events.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventController {
    @Autowired
    private StreamBridge streamBridge;
    @GetMapping("/publish")
    public PageEvent publish(String name,String topic) {
        PageEvent event= new PageEvent(
                name,
                Math.random()>0.5?"U1":"U2" ,
                new Date(),10+new Random().nextInt(10000));
        streamBridge.send(topic,event);
        return event;

    }
}
