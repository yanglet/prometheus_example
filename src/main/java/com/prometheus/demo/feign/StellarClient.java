package com.prometheus.demo.feign;

import com.prometheus.demo.feign.dto.StellarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "StellarClient", url = "http://ip_address:8110")
public interface StellarClient {

    @GetMapping
    StellarResponse getLatestBlock();
}
