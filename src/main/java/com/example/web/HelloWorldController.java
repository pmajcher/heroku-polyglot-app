package com.example.web;

import com.example.service.MongoDbService;
import com.example.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class HelloWorldController {

    @Autowired private MongoDbService mongoDbService;
    @Autowired private SmsService smsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        long result = mongoDbService.doSomeMongoOperations();
        model.addAttribute("result", result);
        return "index";
    }

    @RequestMapping(value = "/sendSms", method = RequestMethod.GET)
    public String sendSms(@RequestParam(value = "phoneNumber") String number,
                          @RequestParam(value = "message") String message) throws IOException {
        smsService.sendSms(number, message);
        return "index";
    }

}
