package com.example.apialunos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.escola.service.UserService;
import com.example.escola.model.UserModel;

@SpringBootTest
class ApialunosApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        assertThat(userService).isNotNull();
    }

    @Test
    void loginFailsWithInvalidCredentials() {
        assertThat(userService.login("invalid", "invalid").isPresent()).isFalse();
    }
}
