package com.focason.core.feign;



import com.focason.core.interceptor.FsFeignClientInterceptor;
import com.focason.core.request.UserValidationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    value = "base-service", path = "/api/v1/auth", url = "http://localhost:8081",
    configuration = FsFeignClientInterceptor.class)
public interface AuthFeignClient
{
    @PostMapping("/validate-user")
    ResponseEntity<Boolean> validateUser(@RequestBody UserValidationRequest request);

    @PostMapping("/validate-user")
    ResponseEntity<Boolean> findAccessToken(@RequestBody UserValidationRequest request);
}
