package com.prometheus.demo.feign;

import com.prometheus.demo.feign.dto.IconRequest;
import com.prometheus.demo.feign.dto.IconResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "IconClient", url = "http://ip_address:8111/api/v3/icon_dex")
public interface IconClient {

    @GetMapping
    IconResponse getLatestBlock(@RequestBody IconRequest iconRequest);
}
