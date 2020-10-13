package com.simplify.api.repo;

import com.simplify.api.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJpaRepositoryTest {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void whenFindByUid_thenReturnUser() {
        String uid = "test@gmail.com";
        String name = "test";

        userJpaRepository.save(User.builder()
                .uid(uid)
                .password(passwordEncoder.encode("test"))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

        Optional<User> user = userJpaRepository.findByUid(uid);

        assertNotNull(user);
        assertTrue(user.isPresent());
        assertEquals(user.get().getName(), name);
        //assertThat(user.get().getName(), is(name));
    }

}