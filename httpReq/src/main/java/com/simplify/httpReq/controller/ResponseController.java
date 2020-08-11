package com.simplify.httpReq.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ResponseController {

    @RequestMapping(value="/testForForm", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> testForForm(@RequestParam Map<String, String> reqParam){

        for(Map.Entry<String, String> entry : reqParam.entrySet()){
            entry.setValue(entry.getValue() + "_testForForm");
        }

        return reqParam;
    }

    @RequestMapping(value="/testForJson", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Map<String, Object> testForJson(@RequestBody Map<String, Object> reqParam){

        for(Map.Entry<String, Object> entry : reqParam.entrySet()){
            entry.setValue(entry.getValue() + "_testForJson");
        }

        return reqParam;
    }
}
