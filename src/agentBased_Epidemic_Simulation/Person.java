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
import agentBased_Epidemic_Simulation.chart.InfectionState;
import repast.simphony.ui.probe.ProbedProperty;

public class Person {
	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private Place home;
	private Place workPlace;
	@ProbedProperty(displayName="InfectionState")
	InfectionState infectionState = InfectionState.createStateChart(this, 0);

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

	@ScheduledMethod(start = 1, interval = 1, priority = 0)
	public void step() {
//		GridPoint pt = grid.getLocation(this);
//		
//		GridCellNgh<Person> nghCreator = new GridCellNgh<>(grid, pt, Person.class, 1, 1);
//		
//		List<GridCell<Person>> gridCells = nghCreator.getNeighborhood(true);
//		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
//		
//		moveTowards(gridCells.get(0).getPoint());
//		if (isInfectious()) infectAdjacent();

		List<Place> places = Arrays.asList(home, workPlace);
		SimUtilities.shuffle(places, RandomHelper.getUniform());
		Place next = places.get(0);
		if (RandomHelper.nextDouble() > 0.8) {
			Context<Object> context = ContextUtils.getContext(this);
			List<Place> leisurePlaces = StreamSupport.stream(context.getObjects(Place.class).spliterator(), false)
					.map(Place.class::cast)
					.filter(place -> place.getType().equals(PlaceType.LEISURE))
					.collect(Collectors.toList());
			Place leisurePlace = leisurePlaces.get(RandomHelper.nextIntFromTo(0, leisurePlaces.size() - 1));
			next = leisurePlace;
		}
		moveTo(next);
		
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
			if (RandomHelper.nextDouble() > 0.5) {
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
}
