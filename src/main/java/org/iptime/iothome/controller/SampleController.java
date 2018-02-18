package org.iptime.iothome.controller;

import org.iptime.iothome.domain.SampleVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }
    
    @GetMapping("sample")
    public SampleVo makeSample() {
        SampleVo vo = new SampleVo();
        
        vo.setVal1("v1");
        vo.setVal2("v2");
        vo.setVal3("v3");
        
        return vo;
    }
}
