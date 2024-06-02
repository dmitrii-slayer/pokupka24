package org.akatsuki.pokupka24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.dto.UserAccountDTO;
import org.akatsuki.pokupka24.dto.UserDTO;
import org.akatsuki.pokupka24.mapper.UserAccountMapper;
import org.akatsuki.pokupka24.mapper.UserMapper;
import org.akatsuki.pokupka24.service.UserService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Пользователи", description = "Операции по пользователям")
@RequestMapping(UserController.RESOURCE_PATH)
@RestController
public class UserController {

    private static final String BASE_PATH = "/api/pokupka24";
    private static final String API_VERSION = "/v1";
    private static final String RESOURCE = "/users";
    private static final String ACCOUNTS = "/account";

    public static final String RESOURCE_PATH = BASE_PATH + API_VERSION + RESOURCE;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Operation(summary = "Поиск пользователей")
    @ApiResponse(description = "Результат поиска",
            responseCode = "200"

//            c этой строкой не будет тела если не указать атрибут schema, поэтому ее надо убрать
//            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),

            // использовать возвращаемый тип метода для определения тела ответа
//            useReturnTypeSchema = true // хз нужно ли, вроде и без нее работает хотя по умолчанию false
    )
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserDTO>> findUsers(@ParameterObject Pageable pageable) {
        Page<User> users = userService.findUsers(pageable);
        return ResponseEntity.ok(new PageImpl<>(userMapper.toDTOList(users.getContent()), pageable, users.getTotalElements()));
    }

    @Operation(summary = "Поиск пользователя по ID")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userMapper.toDTO(userService.findUserById(userId)));
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping("")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return ResponseEntity.ok(userMapper.toDTO(userService.addUser(user)));
    }

    @Operation(summary = "Редактирование пользователя")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") UUID userId,
                                              @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(userId, userMapper.toEntity(userDTO));
        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }

    @Operation(summary = "Поиск счета по ID пользователя")
    @GetMapping("/{userId}" + ACCOUNTS)
    public UserAccountDTO findUserAccountByUserId(@PathVariable("userId") UUID userId) {
        return userAccountMapper.toDTO(userService.findUserAccount(userId));
    }

    @Operation(summary = "Создание счета пользователя")
    @PostMapping("/{userId}" + ACCOUNTS)
    public UserAccountDTO addUserAccount(@PathVariable("userId") UUID userId,
                                         @RequestBody UserAccountDTO accountDTO) {
        UserAccount userAccount = userService.addUserAccount(userId, userAccountMapper.toEntity(accountDTO));
        return userAccountMapper.toDTO(userAccount);
    }
}
