package com.simplify.httpReq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseController {

    @RequestMapping(value="/test")
    public @ResponseBody String test(){
        return "TEST";
    }
}
