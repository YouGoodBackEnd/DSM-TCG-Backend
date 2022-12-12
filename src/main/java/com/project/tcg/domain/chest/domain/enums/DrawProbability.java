package com.project.tcg.domain.chest.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DrawProbability {

    FREE_CHEST    (0.01, 0.02, 0.10, 0.20, 0.67, 100, 300, 1, 3),
    SPECIAL_CHEST (0.02, 0.08, 0.15, 0.30, 0.45, 500, 1000, 2, 7),
    SILVER_CHEST  (0.03, 0.17, 0.30, 0.40, 0.10, 500, 1000, 2, 7),
    GOLD_CHEST    (0.05, 0.15, 0.50, 0.25, 0.05, 750, 2000, 7, 15),
    LEGEND_CHEST  (0.70, 0.20, 0.10, 0.00, 0.00, 1000, 3000, 10, 20);

    private final Double SGradeProbability;
    private final Double AGradeProbability;
    private final Double BGradeProbability;
    private final Double CGradeProbability;
    private final Double DGradeProbability;
    private final int minCoin;
    private final int maxCoin;
    private final int minDiamond;
    private final int mazDiamond;

    public double getSGradeTotalProbability(){
        return SGradeProbability;
    }
    public double getAGradeTotalProbability(){
        return AGradeProbability + getSGradeTotalProbability();
    }
    public double getBGradeTotalProbability(){
        return BGradeProbability + getAGradeTotalProbability();
    }
    public double getCGradeTotalProbability(){
        return CGradeProbability + getBGradeTotalProbability();
    }
}