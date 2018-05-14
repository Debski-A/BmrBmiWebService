package com.gladigator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BmiBmrCalculatorWsImpl implements BmiBmrCalculatorPortType {
	
	@Override
	public CalculateBMIResponse calculateBMI(CalculateBMIRequest parameters) {
		BigDecimal heightInMeters  = BigDecimal.valueOf(parameters.getHeight()).divide(BigDecimal.valueOf(100)); //w metrach
		BigDecimal weight  = BigDecimal.valueOf(parameters.getWeight()); //w kg
		BigDecimal result = weight.divide(heightInMeters.multiply(heightInMeters), 2, RoundingMode.HALF_UP);
		CalculateBMIResponse response = new CalculateBMIResponse();
		response.setCalculatedBMI(result.toString());
		return response;
	}

	@Override
	public CalculateBMRResponse calculateBMR(CalculateBMRRequest parameters) {
		Gender gender = parameters.getGender();
		BigDecimal heightInCm  = BigDecimal.valueOf(parameters.getHeight()); //w centymetrach
		BigDecimal weight  = BigDecimal.valueOf(parameters.getWeight());
		BigDecimal age = BigDecimal.valueOf(parameters.getAge());
		int order = parameters.getActivityFrequency();
		ActivityFrequencies activityFrequency = ActivityFrequencies.activityFrequenciesFactory(order);
		BigDecimal result = null;
		
		if (Gender.M.equals(gender)) {
			result = weight.multiply(BigDecimal.TEN)
						.add(heightInCm.multiply(BigDecimal.valueOf(6.25)))
						.subtract(age.multiply(BigDecimal.valueOf(5)))
						.add(BigDecimal.valueOf(5))
						.multiply(activityFrequency.getBmrMultiplier());
		} else {
			result = weight.multiply(BigDecimal.TEN)
					.add(heightInCm.multiply(BigDecimal.valueOf(6.25)))
					.subtract(age.multiply(BigDecimal.valueOf(5)))
					.subtract(BigDecimal.valueOf(161))
					.multiply(activityFrequency.getBmrMultiplier());
		}
		CalculateBMRResponse response = new CalculateBMRResponse();
		response.setCalculatedBMRResponse(result.toString());
		return response;
	}

}
