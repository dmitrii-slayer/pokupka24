package org.akatsuki.pokupka24notification.client;

import org.akatsuki.pokupka24.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pokupka24rest", url = "${client.pokupka24rest.url}")
public interface UserClient extends UserApi {
}
