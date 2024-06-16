package org.akatsuki.pokupka24.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.akatsuki.pokupka24.dto.UserAccountDTO;
import org.akatsuki.pokupka24.dto.UserDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Пользователи", description = "Операции по пользователям")
//@RequestMapping(UserApi.RESOURCE_PATH) feign не поддерживает RequestMapping над типом
public interface UserApi {

    String BASE_PATH = "/api/pokupka24";
    String API_VERSION = "/v1";
    String RESOURCE = "/users";
    String ACCOUNTS = "/account";
    String RESOURCE_PATH = BASE_PATH + API_VERSION + RESOURCE;

    @Operation(summary = "Поиск пользователей")
    @ApiResponse(description = "Результат поиска",
            responseCode = "200"
    )
    @GetMapping(value = RESOURCE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<UserDTO>> findUsers(@ParameterObject Pageable pageable);

    @Operation(summary = "Поиск пользователя по ID")
    @GetMapping(RESOURCE_PATH + "/{userId}")
    ResponseEntity<UserDTO> findUserById(@PathVariable("userId") UUID userId);

    @Operation(summary = "Создание пользователя")
    @PostMapping(RESOURCE_PATH)
    ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO);

    @Operation(summary = "Редактирование пользователя")
    @PutMapping(RESOURCE_PATH + "/{userId}")
    ResponseEntity<UserDTO> updateUser(@PathVariable("userId") UUID userId,
                                       @Valid @RequestBody UserDTO userDTO);

    @Operation(summary = "Поиск счета по ID пользователя")
    @GetMapping(RESOURCE_PATH + "/{userId}" + ACCOUNTS)
    ResponseEntity<UserAccountDTO> findUserAccountByUserId(@PathVariable("userId") UUID userId);

    @Operation(summary = "Редактирование счета пользователя")
    @PutMapping(RESOURCE_PATH + "/{userId}" + ACCOUNTS)
    ResponseEntity<UserAccountDTO> updateUserAccount(@PathVariable("userId") UUID userId,
                                                     @Valid @RequestBody UserAccountDTO accountDTO);

    @Operation(summary = "Получение покупок пользователя")
    @GetMapping(RESOURCE_PATH + "/{userId}/purchases")
    ResponseEntity<List<PurchaseDTO>> findUserPurchases(@PathVariable("userId") UUID userId);
}
