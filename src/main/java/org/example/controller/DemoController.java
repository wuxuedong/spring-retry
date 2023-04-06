package org.example.controller;

import org.example.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/api")
    public String callApi() {
        try {
            return apiService.callApi();
        } catch (Exception e) {
            e.printStackTrace();
            return "调用接口失败";
        }
    }
}
