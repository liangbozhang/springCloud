package com.test.springcloud.controller;

import com.test.springcloud.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProviderController {

    @Resource
    private ProviderService providerService;

    @GetMapping("sendMessage")
    public String send(){
        return providerService.send();
    }
}
