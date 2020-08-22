package agentBased_Epidemic_Simulation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.statecharts.AbstractState;
import repast.simphony.util.ContextUtils;
import repast.simphony.util.SimUtilities;
import agentBased_Epidemic_Simulation.chart.DailyRoutine;
import agentBased_Epidemic_Simulation.chart.InfectionState;
import repast.simphony.ui.probe.ProbedProperty;

public class Person {
	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private HomePlace home;
	private WorkPlace workPlace;
	@ProbedProperty(displayName="InfectionState")
	InfectionState infectionState = InfectionState.createStateChart(this, 0);
	
	@ProbedProperty(displayName="DailyRoutine")
	DailyRoutine dailyRoutine = DailyRoutine.createStateChart(this, 0);
	
	
	public static final double meanOfIncubationTime = 5.057;
	private double incubationTime = Random.nextLogNormal(1.621, 0.418);

	public static final double meanOfPreSymptomatic = 2;
	private double latentTime = Math.max(0.1, incubationTime - RandomHelper.getUniform().nextDoubleFromTo(1, 3));
	
	public static final double meanOfPostSymptomaticInfectiousDuration = 8;
	private double postSymptomaticInfectiousDuration = Math.max(0.5, RandomHelper.getNormal().nextDouble(8, 3.5));
	
	public static final double meanOfInfectious = meanOfPreSymptomatic + meanOfPostSymptomaticInfectiousDuration;
	public static double infectionChance = 0.01;


	private int workStart = (int) (RandomHelper.getNormal().nextDouble(8, 1) + 24) % 24;
	private int workEnd = (workStart + (int) Random.nextTriangular(3, 8, 12) + 24) % 24;
	private int sleepStart = (workStart - (int) Random.nextSymmetricTriangular(8, 3) + 24) % 24;


	public Person(ContinuousSpace<Object> space, Grid<Object> grid, boolean initiallyExposed) {
		this.space = space;
		this.grid = grid;
		if (initiallyExposed) this.exposed();
	}
	
	public String getInfectionStateState(){
		if (infectionState == null) return "";
		Object result = infectionState.getCurrentSimpleState();
		return result == null ? "" : result.toString();
	}
	
	public String getDailyRoutineState(){
		if (dailyRoutine == null) return "";
		Object result = dailyRoutine.getCurrentSimpleState();
		return result == null ? "" : result.toString();
	}

	public boolean isTimeToWork() {
		return getContext().clock.isTimeBetween(workStart, workEnd);
	}
	
	public boolean isTimeToSleep() {
		return getContext().clock.isTimeBetween(sleepStart, workStart);
	}
	
	public boolean isFreeTime() {
		return getContext().clock.isTimeBetween(workEnd, sleepStart);
	}
	
	public void goToWork() {
		if(workPlace.hasHomeOffice() && getContext().homeOfficeEnabled) {
			moveTo(home);
		} else if(isSymptomatic() && getContext().healthInsuranceEnabled) {
			moveTo(home);
		} else {
			moveTo(workPlace);
		}
	}
	
	public void goToBed() {
		moveTo(home);
	}
	
	public void goToSpendFreeTime() {
		if(isSymptomatic()) {
			moveTo(home);
			return;
		}
		
		if(RandomHelper.nextDoubleFromTo(0, 1) > 0.66) {
			LeisurePlace leisurePlace = null;
			double distanceFromHome = Integer.MAX_VALUE;
			for(int i = 10; i < distanceFromHome; i++) {
				leisurePlace = (LeisurePlace) getContext().getRandomObjects(LeisurePlace.class, 1).iterator().next();
				distanceFromHome = grid.getDistance(grid.getLocation(home), grid.getLocation(leisurePlace));
			}
			moveTo(leisurePlace);
		} else {
			moveTo(home);
		}
		currentLeisureDuration = RandomHelper.nextIntFromTo(1, 3);
		
		//moveTo(home);
	}	

	private int currentLeisureDuration;
	public int getCurrentLeisureDuration() {
		return currentLeisureDuration;
	}

	@ScheduledMethod(start = 1, interval = 1, priority = -1)
	public void sneeze() {
		if (isInfectious()) infectAdjacent();
	}
	
	
	public void moveTo(Place place) {
		GridPoint location = grid.getLocation(place);
		grid.moveTo(this, location.getX(), location.getY());
		space.moveTo(this, location.getX() + RandomHelper.nextDoubleFromTo(0.2, 0.8), location.getY() + RandomHelper.nextDoubleFromTo(0.2, 0.8));
	}
	
	public void infectAdjacent() {
		getContext().infectedThisTick++;
		GridPoint pt = grid.getLocation(this);
		Iterable<Object> others = grid.getObjectsAt(pt.getX(), pt.getY());
		for(Object other : others) {
			if(!(other instanceof Person)) continue;
			Person person = (Person) other;
			if(person.isSusceptible()) getContext().infectedMeetingsThisTick ++;
			if (RandomHelper.nextDoubleFromTo(0, 1) < infectionChance) {
				person.exposed();
			}
		}
	}
	
	public void exposed() {
		
		infectionState.receiveMessage("exposed");
	}
	
	public boolean is(String state) {
		return infectionState.getCurrentStates().stream()
				.map(AbstractState::getId)
				.anyMatch(state::equals);
	}

	public boolean isSusceptible() {
		return is("Susceptible");
	}
	
	public boolean isExposed() {
		return is("Exposed");
	}
	
	public boolean isInfectious() {
		return is("Infectious");
	}
	
	public boolean isSymptomatic() {
		return is("Symptomatic");
	}
	
	public boolean isNonSymptomatic() {
		return is("Non-Symptomatic");
	}
	
	public boolean isRemoved() {
		return is("Removed");
	}
	
	public Place getHome() {
		return home;
	}

	public void setHome(HomePlace home) {
		this.home = home;
	}

	public Place getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(WorkPlace workPlace) {
		this.workPlace = workPlace;
	}

	public double getIncubationTime() {
		return incubationTime;
	}

	public double getLatentTime() {
		return latentTime;
	}

	public double getPostSymptomaticInfectiousDuration() {
		return postSymptomaticInfectiousDuration;
	}

	public int getWorkStart() {
		return workStart;
	}

	public void setWorkStart(int workStart) {
		this.workStart = workStart;
	}

	public int getWorkEnd() {
		return workEnd;
	}

	public void setWorkEnd(int workEnd) {
		this.workEnd = workEnd;
	}

	public int getSleepStart() {
		return sleepStart;
	}

	public void setSleepStart(int sleepStart) {
		this.sleepStart = sleepStart;
	}
	
	public Scenario getContext() {
		return (Scenario) ContextUtils.getContext(this);
	}

}
