package com.focason.core.feign;



import com.focason.core.interceptor.FsFeignClientInterceptor;
import com.focason.core.resource.ClientResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "focason-management", configuration = FsFeignClientInterceptor.class)
public interface StoreFeignClient
{
    @GetMapping("/api/v1/store/{storeCode}")
    ResponseEntity<ClientResource> fetchStore(@PathVariable("storeCode") String storeCode);
}
