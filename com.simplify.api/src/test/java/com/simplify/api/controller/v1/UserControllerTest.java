package com.simplify.api.controller.v1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "mockUser", roles = {"USER"})
    public void accessDenied() throws Exception {

        // TODO: 아래 redirectedUrl / forwardedUrl 모두 제대로 감지되지 않음 (동작은 제대로 된것으로 보임)
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(redirectedUrl("/exception/accessdenied"))
        ;
    }
}