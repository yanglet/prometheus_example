package com.prometheus.demo.service;

public class BlockMetric {

    private String symbol;
    private Long blockHeight;

    public BlockMetric(String symbol, Long blockHeight) {
        this.symbol = symbol;
        this.blockHeight = blockHeight;
    }

    public static BlockMetric of(String symbol, Long blockHeight) {
        return new BlockMetric(symbol, blockHeight);
    }

    public void updateBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public Double getDoubleBlockHeight() {
        return this.blockHeight.doubleValue();
    }
}
