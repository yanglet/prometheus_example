package com.prometheus.demo.feign;

import com.prometheus.demo.feign.dto.SymbolResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SymbolClient", url = "http://ip_address:8112")
public interface SymbolClient {

    @GetMapping("/chain/info")
    SymbolResponse getLatestBlock();
}
