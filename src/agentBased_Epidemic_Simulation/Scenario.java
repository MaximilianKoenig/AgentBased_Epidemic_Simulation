package agentBased_Epidemic_Simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridAdder;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.util.SimUtilities;
import repast.simphony.valueLayer.GridValueLayer;

public class Scenario extends DefaultContext<Object> {
	
	
	public static final int daysToTicks = 24;
	
	private static final int gridsize = 100;
	private static final int initialInfectedCount = 10;
	private static final int population = 4000;
	private static final double averageWorkersPerPlace = 6.5;
	private static final int workPlaceCount = (int)(population / averageWorkersPerPlace);
	private static final int leisureCount = 100;
	public boolean homeOfficeEnabled = true;
	public boolean healthInsuranceEnabled = true;
	
	public Clock clock;
	
	public int infectionsTotal = initialInfectedCount;
	public int susceptible = population - initialInfectedCount;
	
	public double reproductionNumber;

	public double infectedMeetingsThisTick = 0;
	public double infectedThisTick = 0;
	
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private GridValueLayer placeMatrix;
	
	public Context<Object> build() {
		RandomHelper.createNormal(1000, 5000);
		
		this.setId("AgentBased_Epidemic_Simulation");
		
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		space = spaceFactory.createContinuousSpace("space", this,
				(grid, obj) -> {},
				new repast.simphony.space.continuous.WrapAroundBorders(),
				gridsize, gridsize);
		
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		grid = gridFactory.createGrid("grid", this,
				new GridBuilderParameters<Object>(
						new repast.simphony.space.grid.WrapAroundBorders(),
						(grid, obj) -> {}, 
						true, gridsize, gridsize)
				);
		
		placeMatrix = new GridValueLayer("Place Matrix", true, 
				new repast.simphony.space.grid.WrapAroundBorders(), gridsize, gridsize);
		
		this.addValueLayer(placeMatrix);
		
		clock = new Clock();
		this.add(clock);

		regenerateRandomPositions();
		for (int i = 0; i < leisureCount; i++) {
			Place leisurePlace = new LeisurePlace(grid, placeMatrix);
			addPlace(leisurePlace);
			GridPoint firstLocation = grid.getLocation(leisurePlace);
			for(int j = 1; j < 4 && i < leisureCount; i++,j++) {
				int dx = j & 1;
				int dy = (j & 2) / 2;
				addPlace(new LeisurePlace(grid, placeMatrix), Optional.of(new GridPoint(firstLocation.getX()+dx, firstLocation.getY()+dy)));
			}
		}
		regenerateRandomPositions();
				
		for (int i = 0; i < population; i++) {
			this.add(new Person(space, grid, false));
		}
		
		
		for (int i = 0; i < workPlaceCount; i++) {
			addPlace(new WorkPlace(grid, placeMatrix));
		}
		
		getRandomObjects(WorkPlace.class, workPlaceCount * 2/3).forEach(each -> ((WorkPlace) each).setHasHomeOffice(true));

		
		HomePlace home = Place.home(grid, placeMatrix);
		addPlace(home);

		
		for(Person person : StreamSupport.stream(this.getObjects(Person.class).spliterator(), false).map(Person.class::cast).collect(Collectors.toList())) {
			if(!home.hasCapacity()) {
				home = Place.home(grid, placeMatrix);
				addPlace(home);
			}
			person.setHome(home);
			home.addPerson(person);
			
			WorkPlace workPlace = null;
			double distanceFromHome = Integer.MAX_VALUE;
			for(int i = 0; i < distanceFromHome; i++) {
				workPlace = (WorkPlace) getRandomObjects(WorkPlace.class, 1).iterator().next();
				distanceFromHome = grid.getDistance(grid.getLocation(home), grid.getLocation(workPlace));
			}
			person.setWorkPlace(workPlace);
			workPlace.addPerson(person);
			
		};
		
		RunEnvironment.getInstance().getCurrentSchedule().schedule(ScheduleParameters.createOneTime(0), () -> {
			for(Object person : getRandomObjects(Person.class, initialInfectedCount)) {
				((Person)person).infectionState.activateState("non-Symptomatic");
			}
		});
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		homeOfficeEnabled = (boolean) params.getValue("allowHomeOffice");
		healthInsuranceEnabled = (boolean) params.getValue("allowHealthInsurance");
		//RunEnvironment.getInstance().pauseAt(3000);
		RunEnvironment.getInstance().endAt(3000);
		
		return this;
	}
	
	private void addPlace(Place place) {
		addPlace(place, Optional.empty());
	}
	

	List<int[]> freePositions;
	private void addPlace(Place place, Optional<GridPoint> coordinates) {
		this.add(place);
		GridPoint pt = coordinates.orElse(new GridPoint(freePositions.remove(0)));
		grid.moveTo(place, pt.getX(), pt.getY());
		placeMatrix.set(place.getType().index(), pt.getX(), pt.getY());
	}
	
	private void regenerateRandomPositions() {
		freePositions = new ArrayList<>();
		for(int x = 0; x < gridsize; x++) {
			for(int y = 0; y < gridsize; y++) {
				if(!grid.getObjectsAt(x,y).iterator().hasNext())freePositions.add(new int[]{x, y});
			}
		}
		SimUtilities.shuffle(freePositions, RandomHelper.getUniform());
	}
	
	@ScheduledMethod(start = 1, interval = 1, priority = ScheduleParameters.LAST_PRIORITY)
	public void processInfectionsPerTick() {
////		double newReproductionNumber = 1 + Math.log((0.0+infectionsThisTick + infectionsTotal) / infectionsTotal) * (Person.meanOfInfectious * daysToTicks);
////		infectionsTotal += infectionsThisTick;
////		infectionsThisTick = 0;
////		reproductionNumbers.add(newReproductionNumber);
////		if(reproductionNumbers.size() > 100) reproductionNumbers.remove(0);
//		infectionCounts.add(infectionsTotal);
//		if(infectionCounts.size() > Person.meanOfInfectious * daysToTicks) infectionCounts.remove(0);
//		reproductionNumber = (0.0 + infectionsTotal) / infectionCounts.get(0);
//		//System.out.println(infectionsTotal);
		double contactsPerInfected = infectedMeetingsThisTick / infectedThisTick;
		infectedMeetingsThisTick = 0;
		infectedThisTick = 0;
		
		reproductionNumber = contactsPerInfected * Person.infectionChance * Person.meanOfInfectious * daysToTicks;
		
//		System.out.println(getReprocutionNumber());
	}
	
	public double getReprocutionNumber() {
		return reproductionNumber;
	}
	
	public double getBasicReprocutionNumber() {
		return reproductionNumber * (double) population / (double) susceptible;
	}
	
}
