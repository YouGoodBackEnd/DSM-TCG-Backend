package com.project.tcg.domain.chest.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DrawProbability {

    FREE_CHEST    (0.02, 0.08, 0.10, 0.30, 0.50, 100, 300, 1, 3),
    SPECIAL_CHEST (0.05, 0.10, 0.20, 0.15, 0.50, 500, 1000, 2, 7),
    SILVER_CHEST  (0.08, 0.12, 0.15, 0.15, 0.50, 500, 1000, 2, 7),
    GOLD_CHEST    (0.15, 0.10, 0.20, 0.25, 0.30, 750, 2000, 7, 15),
    LEGEND_CHEST  (0.30, 0.25, 0.20, 0.15, 0.10, 1000, 3000, 10, 20);

    private final Double SSGradeProbability;
    private final Double SGradeProbability;
    private final Double AGradeProbability;
    private final Double BGradeProbability;
    private final Double CGradeProbability;
    private final int minCoin;
    private final int maxCoin;
    private final int minDiamond;
    private final int mazDiamond;

    public double getSSGradeTotalProbability(){
        return SSGradeProbability;
    }
    public double getSGradeTotalProbability(){
        return SGradeProbability + getSSGradeTotalProbability();
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