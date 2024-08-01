package com.prometheus.demo.controller;

import com.prometheus.demo.feign.*;
import com.prometheus.demo.feign.dto.*;
import com.prometheus.demo.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.prometheus.demo.common.Constants.*;

@RestController
@RequestMapping("/blocks")
@RequiredArgsConstructor
public class BlockController {

    private final StellarClient stellarClient;
    private final IconClient iconClient;
    private final SymbolClient symbolClient;
    private final ProtonClient protonClient;
    private final IostClient iostClient;

    private final MetricService metricService;

    @GetMapping("/{symbol}")
    public Object getLatestBlock(@PathVariable String symbol) {

        switch (symbol) {
            case SYMBOL_STELLAR -> {
                StellarResponse response = stellarClient.getLatestBlock();
                metricService.addMetric(symbol, PORT_STELLAR, response.coreLatestLedger());
                return response;
            }

            case SYMBOL_ICON -> {
                IconResponse response = iconClient.getLatestBlock(
                        IconRequest.of(100, "2.0", "icx_getLastBlock"));
                metricService.addMetric(symbol, PORT_ICON, response.result().height());
                return response;
            }

            case SYMBOL_SYMBOL -> {
                SymbolResponse response = symbolClient.getLatestBlock();
                metricService.addMetric(symbol, PORT_SYMBOL, response.height());
                return response;
            }

            case SYMBOL_PROTON -> {
                ProtonResponse response = protonClient.getLatestBlock();
                metricService.addMetric(symbol, PORT_PROTON, response.headBlockNum());
                return response;
            }

            case SYMBOL_IOST -> {
                IostResponse response = iostClient.getLatestBlock();
//                metricService.addMetric(symbol, PORT_IOST, response.headBlockNum());
                return null;
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
