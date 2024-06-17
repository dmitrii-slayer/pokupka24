package org.akatsuki.pokupka24notification.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import org.akatsuki.pokupka24.api.UserApi;
import org.akatsuki.pokupka24.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

//@FeignClient(name = "pokupka24rest", url = "${client.pokupka24rest.url}")
/*
  url можно не указывать если использовать property
  spring.cloud.openfeign.client.config.<имя сервиса>.url=http://localhost:13000
  (обязательно абсолютный путь c протоколом )
  https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#supported-ways-to-provide-url-to-a-feign-client
*/

@CircuitBreaker(name = "pokupka24-breaker")
@FeignClient(name = "pokupka24rest")
@Retry(name = "pokupka24-retry")
public interface UserClient extends UserApi {
// если просто унаследоваться от UserApi, то CircuitBreaker и Retry и остальное от resilience4j не будет срабатывать
//    @CircuitBreaker(name = "pokupka24-breaker")
//    @FeignClient(name = "pokupka24rest")
//    public interface UserClient extends UserApi{}
// то есть обязательно нужно прописать тут нужные методы
    @Override
    @Operation(summary = "Поиск пользователя по ID")
    @GetMapping(RESOURCE_PATH + "/{userId}")
    ResponseEntity<UserDTO> findUserById(@PathVariable("userId") UUID userId);
}
