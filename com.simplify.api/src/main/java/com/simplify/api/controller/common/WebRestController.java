package com.simplify.api.controller.common;

import com.simplify.api.model.response.SingleResult;
import com.simplify.api.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/common")
@RestController
public class WebRestController {

    private Environment environment;

    private final ResponseService responseService;

    @GetMapping("/profile")
    public SingleResult<String> getProfile() {

        log.debug("######################################");
        for(String profile: environment.getActiveProfiles()){
            log.debug(profile);
        }

        Arrays.stream(environment.getActiveProfiles()).forEach(log::debug);

        //log.debug(Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("?"));

        return responseService.getSingleResult(Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("?"));
    }
}

