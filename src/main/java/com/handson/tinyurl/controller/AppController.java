package com.handson.tinyurl.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handson.tinyurl.model.NewTinyRequest;
import com.handson.tinyurl.service.Redis;

@RestController
public class AppController {


        private static final int MAX_RETRIES =4;
                private static final int TINY_LENGTH = 6;
                @Autowired
                Redis redis;
                @Autowired
                ObjectMapper om;
                @Value("${base.url}")
                String baseUrl;
                Random random=new Random();
            
                @RequestMapping(value = "/tiny", method = RequestMethod.POST)
                public String generate(@RequestBody NewTinyRequest request) throws JsonProcessingException {
                    String tinyCode = generateTinyCode();
                    int i = 0;
                    while (!redis.set(tinyCode, om.writeValueAsString(request)) && i < MAX_RETRIES) {
                    tinyCode = generateTinyCode();
                    i++;
                }
                if (i == MAX_RETRIES) throw new RuntimeException("SPACE IS FULL");
                return baseUrl + tinyCode + "/";
            }
        
            @RequestMapping(value = "/{tiny}/", method = RequestMethod.GET)
            public ModelAndView getTiny(@PathVariable String tiny) throws JsonProcessingException {
                System.out.println("getRequest for tiny: " + tiny);
                Object tinyRequestStr = redis.get(tiny);
                NewTinyRequest tinyRequest = om.readValue(tinyRequestStr.toString(),NewTinyRequest.class);
                if (tinyRequest.getLongUrl() != null) {
                    return new ModelAndView("redirect:" + tinyRequest.getLongUrl());
                } else {
                    throw new RuntimeException(tiny + " not found");
                }
            }
            private String generateTinyCode() {
                String charPool = "ABCDEFHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                StringBuilder res = new StringBuilder();
                for (int i = 0; i < TINY_LENGTH; i++) {
            res.append(charPool.charAt(random.nextInt(charPool.length())));
        }
        return res.toString();
    }

}
