package com.prometheus.demo.feign.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.prometheus.demo.feign")
public class FeignConfiguration {
}
