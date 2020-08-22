package agentBased_Epidemic_Simulation;

import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.engine.schedule.ScheduledMethod;

public class Clock {
	
	private int currentTime = 0;
	
	@ScheduledMethod(start = 1, interval = 1, priority = ScheduleParameters.FIRST_PRIORITY)
	public void tick() {
		currentTime = (currentTime + 1) % Scenario.daysToTicks;
	}

	public int getCurrentTime() {
		return currentTime * 24 / Scenario.daysToTicks;
	}	
	
	public boolean isTimeBetween(int start, int end) {
		int time = getCurrentTime();
		if(time < start) time += 24;
		if(end < start) end += 24;
		return start <= time && time < end;
	}

}
