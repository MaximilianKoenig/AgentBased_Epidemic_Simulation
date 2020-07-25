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
	private Place home;
	private Place workPlace;
	@ProbedProperty(displayName="InfectionState")
	InfectionState infectionState = InfectionState.createStateChart(this, 0);
	
	@ProbedProperty(displayName="DailyRoutine")
	DailyRoutine dailyRoutine = DailyRoutine.createStateChart(this, 0);
	
	static int foocount = 0;
	
	private double incubationTime = Random.nextLogNormal(1.621, 0.418);
	private double preInfectious = Math.max(0.1, incubationTime - RandomHelper.getUniform().nextDoubleFromTo(0, 3));
	{
		if(preInfectious == 0) {
			foocount ++;
			System.out.println(foocount);
		}
	}
	private int workStart = (int) RandomHelper.getNormal().nextDouble(8, 2) % 24;
	private int workEnd = (workStart + (int) Random.nextSymmetricTriangular(8, 4) + 24) % 24;
	private int sleepStart = (workStart - (int) Random.nextSymmetricTriangular(8, 3) + 24) % 24;


	public Person(ContinuousSpace<Object> space, Grid<Object> grid, boolean exposed) {
		this.space = space;
		this.grid = grid;
		if (exposed) this.exposed();
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
		return Clock.isTimeBetween(workStart, workEnd);
	}
	
	public boolean isTimeToSleep() {
		return Clock.isTimeBetween(sleepStart, workStart);
	}
	
	public boolean isFreeTime() {
		return Clock.isTimeBetween(workEnd, sleepStart);
	}
	
	public void goToWork() {
		if(isSymptomatic()) {
			moveTo(home);
			return;
		}
		moveTo(workPlace);
	}
	
	public void goToBed() {
		moveTo(home);
	}
	
	public void goToSpendFreeTime() {
		if(isSymptomatic()) {
			moveTo(home);
			return;
		}
		
		if(RandomHelper.nextDoubleFromTo(0, 1) > 0.33) {
			Place leisurePlace = null;
			double distanceFromHome = Integer.MAX_VALUE;
			for(int i = 10; i < distanceFromHome; i++) {
				leisurePlace = ScenarioBuilder.leisurePlaces.get(RandomHelper.nextIntFromTo(0, ScenarioBuilder.leisurePlaces.size() - 1));
				distanceFromHome = space.getDistance(space.getLocation(home), space.getLocation(leisurePlace));
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
	
	public void moveTowards(GridPoint pt) {
		if (!pt.equals(grid.getLocation(this))) {
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint =  space.getLocation(this);
			grid.moveTo(this, (int)myPoint.getX(), (int)myPoint.getY());
		}
	}
	
	public void moveTo(Place place) {
		GridPoint location = grid.getLocation(place);
		grid.moveTo(this, location.getX(), location.getY());
		space.moveTo(this, location.getX() + RandomHelper.nextDoubleFromTo(0.2, 0.8), location.getY() + RandomHelper.nextDoubleFromTo(0.2, 0.8));
	}
	
	public void infectAdjacent() {
		GridPoint pt = grid.getLocation(this);
		Iterable<Object> others = grid.getObjectsAt(pt.getX(), pt.getY());
		for(Object other : others) {
			if(!(other instanceof Person)) continue;
			Person person = (Person) other;
			if (RandomHelper.nextDouble() > 0.99) {
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

	public void setHome(Place home) {
		this.home = home;
	}

	public Place getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(Place workPlace) {
		this.workPlace = workPlace;
	}

	public double getIncubationTime() {
		return incubationTime;
	}

	public double getPreInfectious() {
		return preInfectious;
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

}
