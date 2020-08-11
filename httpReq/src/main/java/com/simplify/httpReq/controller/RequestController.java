package com.simplify.httpReq.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.simplify.httpReq.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {

    @Autowired
    RequestService requestService;

    @RequestMapping(value="/req/form")
    public @ResponseBody String reqForm(){
        JsonNode node = requestService.requestHttpForm();
        return "/req/form";
    }

    @RequestMapping(value="/req/json")
    public @ResponseBody String reqJson(){
        JsonNode node = requestService.requestHttpJson();
        return "/req/json";
    }
}
