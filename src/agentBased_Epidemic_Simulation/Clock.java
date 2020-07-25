package agentBased_Epidemic_Simulation;

import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.engine.schedule.ScheduledMethod;

public class Clock {
	
	private int currentTime = 0;
	
	@ScheduledMethod(start = 1, interval = 1, priority = ScheduleParameters.FIRST_PRIORITY)
	public void tick() {
		currentTime = (currentTime + 1) % ScenarioBuilder.daysToTicks;
	}

	public int getCurrentTime() {
		return currentTime * 24 / ScenarioBuilder.daysToTicks;
	}	
	
	public static boolean isTimeBetween(int start, int end) {
		int time = ScenarioBuilder.clock.getCurrentTime();
		if(time < start) time += 24;
		if(end < start) end += 24;
		return start <= time && time < end;
	}

}
