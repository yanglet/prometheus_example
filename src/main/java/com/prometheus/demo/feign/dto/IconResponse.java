package com.prometheus.demo.feign.dto;

public record IconResponse(
        Result result
) {

    public record Result(Long height) {}
}