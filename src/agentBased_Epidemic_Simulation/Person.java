package agentBased_Epidemic_Simulation;

import java.util.List;

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
import repast.simphony.util.SimUtilities;
import agentBased_Epidemic_Simulation.chart.InfectionState;
import repast.simphony.ui.probe.ProbedProperty;

public class Person {
	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
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
	
	@ScheduledMethod(start = 1, interval = 1)
	public void step() {
		GridPoint pt = grid.getLocation(this);
		
		GridCellNgh<Person> nghCreator = new GridCellNgh<>(grid, pt, Person.class, 1, 1);
		
		List<GridCell<Person>> gridCells = nghCreator.getNeighborhood(true);
		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
		
		moveTowards(gridCells.get(0).getPoint());
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
	
	public void infectAdjacent() {
		GridPoint pt = grid.getLocation(this);
		GridCellNgh<Person> nghCreator = new GridCellNgh<>(grid, pt, Person.class, 2, 2);
		List<GridCell<Person>> gridCells = nghCreator.getNeighborhood(true);
		
		for(GridCell<Person> cell : gridCells) {
			for(Person person : cell.items()) {
				person.exposed();
			}
		}
	}
	
	public void exposed() {
		infectionState.receiveMessage("exposed");
	}
	
	public boolean isInfectious() {
		return infectionState.getCurrentStates().stream()
				.map(AbstractState::getId)
				.anyMatch("Infectious"::equals);
	}
}
