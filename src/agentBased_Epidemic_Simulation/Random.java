package agentBased_Epidemic_Simulation;

import java.util.Arrays;

import repast.simphony.random.RandomHelper;

public class Random {
	

	public static double nextLogNormal(double mean , double stddev) {
		return Math.exp(RandomHelper.getNormal().nextDouble(mean, stddev));
	}
	
	public static double nextSymmetricTriangular(double peak, double range) {
		double x = RandomHelper.getUniform().nextDoubleFromTo(peak - range, peak + range);
		double y = RandomHelper.getUniform().nextDoubleFromTo(peak - range, peak + range);
		return (x + y) / 2;
	}
	
	public static double nextTriangular(double lower, double peak, double upper) {
		assert lower <= peak;
		assert upper >= peak;
		assert lower < upper;
		
		double u = RandomHelper.getUniform().nextDoubleFromTo(0, 1);
		double tilt = (peak - lower) / (upper - lower);
		if(u < tilt) {
			return lower + Math.sqrt(u * (upper - lower) * (peak - lower));
		} else {
			return upper - Math.sqrt((1 - u) * (upper - lower) * (upper - peak));
		}
	}
	
	public static <T> T discrete(T[] values, double[] probabilities) {
		assert values.length == probabilities.length;
		double ran = RandomHelper.getUniform().nextDoubleFromTo(0, 1);
		double sum = 0;
		for(int i = 0; i < values.length; i++) {
			double nextSum = sum + probabilities[i];
			if(sum <= ran && ran < nextSum) return values[i];
			sum = nextSum;
		}
		throw new RuntimeException("End of distribution reached");
	}
	
	
}
