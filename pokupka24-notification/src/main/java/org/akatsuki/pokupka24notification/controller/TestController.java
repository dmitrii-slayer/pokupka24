package org.akatsuki.pokupka24notification.controller;

import org.akatsuki.pokupka24.dto.UserDTO;
import org.akatsuki.pokupka24notification.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    UserClient userClient;

    @GetMapping("/findMyUser")
    public void findMyUser() {
        System.out.println("Пытаемся найти пользователя");
        UserDTO userDTO = userClient.findUserById(UUID.randomUUID()).getBody();
        System.out.println(userDTO);
    }
}
