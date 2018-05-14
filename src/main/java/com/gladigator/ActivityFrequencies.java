package com.gladigator;

import java.math.BigDecimal;

public enum ActivityFrequencies {
	NONE(new BigDecimal(1)),
	VERY_LOW(new BigDecimal(1.2)),
	LOW(new BigDecimal(1.375)),
	MEDIUM(new BigDecimal(1.55)),
	HIGH(new BigDecimal(1.725)),
	VERY_HIGH(new BigDecimal(1.9));
	
	private BigDecimal bmrMultiplier;
	
	ActivityFrequencies(BigDecimal bmrMultiplier) {
		this.bmrMultiplier = bmrMultiplier;
	}

	public BigDecimal getBmrMultiplier() {
		return bmrMultiplier;
	}
	
	public static ActivityFrequencies activityFrequenciesFactory(int order) {
		switch (order) {
		case 1: return ActivityFrequencies.NONE;
		case 2: return ActivityFrequencies.VERY_LOW;
		case 3: return ActivityFrequencies.LOW;
		case 4: return ActivityFrequencies.MEDIUM;
		case 5: return ActivityFrequencies.HIGH;
		case 6: return ActivityFrequencies.VERY_HIGH;
		default: throw new IllegalArgumentException("No constant with order " + order + " found");
		}
	}
	
}
