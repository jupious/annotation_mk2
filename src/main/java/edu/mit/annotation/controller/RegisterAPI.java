package edu.mit.annotation.controller;

import edu.mit.annotation.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reg")
public class RegisterAPI {

    @Autowired
    RegisterService service;

    @PostMapping(value="/contract/searchBusinessNumber", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, String>> auto_business_number(@RequestParam("business_number") String business_number)    {
        System.out.println("입력값 : " + business_number);
        List<Map<String, String>> result = service.auto_business_number(business_number);
        System.out.println(result);

        return result;
    }
}