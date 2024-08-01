package com.prometheus.demo.scheduler;

import com.prometheus.demo.feign.IconClient;
import com.prometheus.demo.feign.ProtonClient;
import com.prometheus.demo.feign.StellarClient;
import com.prometheus.demo.feign.SymbolClient;
import com.prometheus.demo.feign.dto.*;
import com.prometheus.demo.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.prometheus.demo.common.Constants.*;

@Component
@RequiredArgsConstructor
public class MetricScheduler {

    private static final Logger log = LoggerFactory.getLogger(MetricScheduler.class);

    private final StellarClient stellarClient;
    private final IconClient iconClient;
    private final SymbolClient symbolClient;
    private final ProtonClient protonClient;

    private final MetricService metricService;

    @Scheduled(fixedDelay = 1000L, initialDelay = 1000L)
    public void metricScheduler() {
        StellarResponse stellarResponse = stellarClient.getLatestBlock();
        metricService.updateMetric(SYMBOL_STELLAR, stellarResponse.coreLatestLedger());

        SymbolResponse symbolResponse = symbolClient.getLatestBlock();
        metricService.updateMetric(SYMBOL_SYMBOL, symbolResponse.height());

        ProtonResponse protonResponse = protonClient.getLatestBlock();
        metricService.updateMetric(SYMBOL_PROTON, protonResponse.headBlockNum());

        log.info("[BLOCK_HEIGHT] stellar = {}, symbol = {}, proton = {}",
                stellarResponse.coreLatestLedger(), symbolResponse.height(), protonResponse.headBlockNum());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        StellarResponse stellarResponse = stellarClient.getLatestBlock();
        metricService.addMetric(SYMBOL_STELLAR, PORT_STELLAR, stellarResponse.coreLatestLedger());

        SymbolResponse symbolResponse = symbolClient.getLatestBlock();
        metricService.addMetric(SYMBOL_SYMBOL, PORT_SYMBOL, symbolResponse.height());

        ProtonResponse protonResponse = protonClient.getLatestBlock();
        metricService.addMetric(SYMBOL_PROTON, PORT_PROTON, protonResponse.headBlockNum());
    }
}
