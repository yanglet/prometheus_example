package com.prometheus.demo.feign;

import com.prometheus.demo.feign.dto.ProtonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ProtonClient", url = "http://ip_address:8113")
public interface ProtonClient {

    @GetMapping("/v1/chain/get_info")
    ProtonResponse getLatestBlock();
}
