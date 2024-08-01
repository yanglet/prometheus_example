package com.prometheus.demo.feign;

import com.prometheus.demo.feign.dto.IostResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "IostClient", url = "http://ip_address:8114")
public interface IostClient {

    @GetMapping("/getChainInfo")
    IostResponse getLatestBlock();
}
