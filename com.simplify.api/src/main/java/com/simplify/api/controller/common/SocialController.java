package com.simplify.api.controller.common;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/social/login")
@Controller
public class SocialController {

    private final Environment environment;
    private final RestTemplate restTemplate;
    private final Gson gson;
    //private final KakaoService kakaoService;

    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.social.kakao.redirect}")
    private String kakaoRedirect;

    @GetMapping
    public ModelAndView socialLogin(ModelAndView mav) {

        StringBuilder loginUrl = new StringBuilder()
                .append(environment.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

        mav.addObject("loginUrl", loginUrl);
        mav.setViewName("social/login");

        return mav;
    }

    @GetMapping(value = "/kakao")
    public ModelAndView redirectKakao(ModelAndView mav, @RequestParam String code) {

        log.debug("code=" + code);
        return null;

//        mav.addObject("authInfo", kakaoService.getKakaoTokenInfo(code));
//        mav.setViewName("social/redirectKakao");
//        return mav;
    }
}
