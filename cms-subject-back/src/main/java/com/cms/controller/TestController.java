package com.cms.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :zhanhaoze
 * @description :
 * @create :2021-07-22
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "hello";
    }
}
