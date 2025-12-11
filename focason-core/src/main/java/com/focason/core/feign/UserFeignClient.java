package com.focason.core.feign;



import com.focason.core.interceptor.FsFeignClientInterceptor;
import com.focason.core.response.ActiveUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    value = "base-service", path = "/api/v1/users", url = "http://localhost:8081",
    configuration = FsFeignClientInterceptor.class)
public interface UserFeignClient
{
    @GetMapping("/active")
    ResponseEntity<ActiveUserResponse> getActiveUserIds();
}
