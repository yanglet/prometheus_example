package com.prometheus.demo.feign.dto;

public record IconRequest(
        Integer id,
        String jsonrpc,
        String method
) {

    public static IconRequest of(Integer id, String jsonrpc, String method) {
        return new IconRequest(id, jsonrpc, method);
    }
}
