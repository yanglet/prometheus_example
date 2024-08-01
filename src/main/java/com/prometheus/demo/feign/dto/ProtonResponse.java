package com.prometheus.demo.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProtonResponse(
        @JsonProperty("head_block_num")
        Long headBlockNum
) {
}
