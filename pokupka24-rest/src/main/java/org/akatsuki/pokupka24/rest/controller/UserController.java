package org.akatsuki.pokupka24.rest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.akatsuki.pokupka24.api.UserApi;
import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.dto.UserAccountDTO;
import org.akatsuki.pokupka24.dto.UserDTO;
import org.akatsuki.pokupka24.mapper.UserAccountMapper;
import org.akatsuki.pokupka24.mapper.UserMapper;
import org.akatsuki.pokupka24.service.UserService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserAccountMapper userAccountMapper;

    @Override
    public ResponseEntity<Page<UserDTO>> findUsers(@ParameterObject Pageable pageable) {
        Page<User> users = userService.findUsers(pageable);
        return ResponseEntity.ok(new PageImpl<>(userMapper.toDTOList(users.getContent()), pageable, users.getTotalElements()));
    }

    @Override
    public ResponseEntity<UserDTO> findUserById(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userMapper.toDTO(userService.findUserById(userId)));
    }

    @Override
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return ResponseEntity.ok(userMapper.toDTO(userService.addUser(user)));
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") UUID userId,
                                              @Valid @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(userId, userMapper.toEntity(userDTO));
        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }

    @Override
    public ResponseEntity<UserAccountDTO> findUserAccountByUserId(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userAccountMapper.toDTO(userService.findUserAccount(userId)));
    }

    @Override
    public ResponseEntity<UserAccountDTO> updateUserAccount(@PathVariable("userId") UUID userId,
                                                            @Valid @RequestBody UserAccountDTO accountDTO) {
        UserAccount updatedAccount = userService.updateUserAccount(userId, userAccountMapper.toEntity(accountDTO));
        return ResponseEntity.ok(userAccountMapper.toDTO(updatedAccount));
    }
}
