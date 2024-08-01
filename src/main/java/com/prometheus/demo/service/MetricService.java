package com.prometheus.demo.service;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetricService {

    public final String INSTANCE_IP = "ip_address";

    public Map<String, BlockMetric> blockMetrics = new HashMap<>();

    public void addMetric(String symbol, String port, Long blockHeight) {
        BlockMetric blockMetric = BlockMetric.of(symbol, blockHeight);
        blockMetrics.put(symbol, blockMetric);

        List<Tag> tags = List.of(
                Tag.of("SYMBOL", symbol),
                Tag.of("INSTANCE_IP", INSTANCE_IP),
                Tag.of("INSTANCE_PORT", port));

        Gauge.builder("block.height", blockMetric, BlockMetric::getDoubleBlockHeight)
                .tags(tags)
                .register(Metrics.globalRegistry);
    }

    public void updateMetric(String symbol, Long blockHeight) {
        BlockMetric blockMetric = blockMetrics.get(symbol);
        blockMetric.updateBlockHeight(blockHeight);
    }
}
