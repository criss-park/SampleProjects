package com.simplify.api.controller.v1;

import com.simplify.api.entity.User;
import com.simplify.api.repo.UserJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup(){
        userJpaRepository.save(User.builder()
        .name("name")
        .uid("test@naver.com")
        .password(passwordEncoder.encode("1234"))
        .roles(Collections.singletonList("ROLE_USER"))
        .build());
    }

    @Test
    public void signin() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "test@naver.com");
        params.add("password", "1234");
        mockMvc.perform(post("/v1/signin").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").exists())
                .andExpect(jsonPath("$.data").exists())
        ;
    }

    @Test
    public void signup() throws Exception{
        long epochTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "test_" + epochTime + "@test.com");
        params.add("password", "1234");
        params.add("name", "test_" + epochTime);

        mockMvc.perform(post("/v1/signup").params(params))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").exists());
    }



}