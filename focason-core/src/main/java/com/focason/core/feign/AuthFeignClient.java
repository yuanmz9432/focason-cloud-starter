package com.focason.core.feign;


import com.focason.core.interceptor.FsFeignClientInterceptor;
import com.focason.core.request.TokenValidationRequest;
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
    /**
     * Validates a user's existence and status.
     *
     * @param request The validation request containing email and uid.
     * @return ResponseEntity containing true if user is valid, false otherwise.
     */
    @PostMapping("/validate-user")
    ResponseEntity<Boolean> validateUser(@RequestBody UserValidationRequest request);

    /**
     * Validates if an access token exists in the database.
     *
     * @param request The validation request containing the access token.
     * @return ResponseEntity containing true if token exists, false otherwise.
     */
    @PostMapping("/validate-token")
    ResponseEntity<Boolean> validateToken(@RequestBody TokenValidationRequest request);
}
