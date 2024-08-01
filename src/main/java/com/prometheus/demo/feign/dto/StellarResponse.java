package com.prometheus.demo.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StellarResponse(
        @JsonProperty("core_latest_ledger")
        Long coreLatestLedger
) {
}
