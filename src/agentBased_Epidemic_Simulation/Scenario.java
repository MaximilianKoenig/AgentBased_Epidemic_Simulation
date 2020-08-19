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
	private static final int exposedCount = 100;
	private static final int healthyCount = 4000 - exposedCount;
	private static final int workPlaceCount = 600;
	private static final int leisureCount = 100;
	public boolean homeOfficeEnabled = true;
	
	public static Clock clock;
	public static List<Place> leisurePlaces;
	
	public int infectionsTotal = exposedCount;
	public int susceptible = healthyCount;
	public List<Integer> infectionCounts = new ArrayList<>();
	public double reproductionNumber;

	public static double infectedMeetingsThisTick = 0;
	public static double infectedThisTick = 0;
	
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
			Place leisurePlace = new Place(grid, placeMatrix, PlaceType.LEISURE);
			addPlace(leisurePlace);
			GridPoint firstLocation = grid.getLocation(leisurePlace);
			for(int j = 1; j < 4 && i < leisureCount; i++,j++) {
				int dx = j & 1;
				int dy = (j & 2) / 2;
				addPlace(new Place(grid, placeMatrix, PlaceType.LEISURE), Optional.of(new GridPoint(firstLocation.getX()+dx, firstLocation.getY()+dy)));
			}
		}
		regenerateRandomPositions();
				
		for (int i = 0; i < healthyCount; i++) {
			this.add(new Person(space, grid, false));
		}
		
		for (int i = 0; i < exposedCount; i++) {
			this.add(new Person(space, grid, true));
		}
		
		
		for (int i = 0; i < workPlaceCount; i++) {
			addPlace(new Place(grid, placeMatrix, PlaceType.WORK));
		}

		
		Place home = Place.home(grid, placeMatrix);
		addPlace(home);
		
		List<Place> workPlaces = StreamSupport.stream(this.getObjects(Place.class).spliterator(), false)
				.map(Place.class::cast)
				.filter(place -> place.getType().equals(PlaceType.WORK))
				.collect(Collectors.toList());
		
		leisurePlaces = StreamSupport.stream(this.getObjects(Place.class).spliterator(), false)
				.map(Place.class::cast)
				.filter(place -> place.getType().equals(PlaceType.LEISURE))
				.collect(Collectors.toList());
		
		for(Person person : StreamSupport.stream(this.getObjects(Person.class).spliterator(), false).map(Person.class::cast).collect(Collectors.toList())) {
			if(!home.hasCapacity()) {
				home = Place.home(grid, placeMatrix);
				addPlace(home);
			}
			person.setHome(home);
			home.addPerson(person);
			
			Place workPlace = null;
			double distanceFromHome = Integer.MAX_VALUE;
			for(int i = 0; i < distanceFromHome; i++) {
				workPlace = workPlaces.get(RandomHelper.nextIntFromTo(0, workPlaces.size() - 1));
				distanceFromHome = grid.getDistance(grid.getLocation(home), grid.getLocation(workPlace));
			}
			person.setWorkPlace(workPlace);
			workPlace.addPerson(person);
		};
		
		Parameters params = RunEnvironment.getInstance().getParameters();
		homeOfficeEnabled = (boolean) params.getValue("allowHomeOffice");
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
	
}
