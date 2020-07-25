package agentBased_Epidemic_Simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.valueLayer.GridValueLayer;

public class ScenarioBuilder implements ContextBuilder<Object> {
	
	
	public static final int daysToTicks = 24;
	private static final int gridsize = 100;
	private static final int healthyCount = 4000;
	private static final int exposedCount = 10;
	private static final int workPlaceCount = 800;
	private static final int leisureCount = 100;
	
	public static Clock clock;
	public static List<Place> leisurePlaces;

	@Override
	public Context<Object> build(Context<Object> context) {

		RandomHelper.createNormal(1000, 5000);
		
		context.setId("AgentBased_Epidemic_Simulation");
		
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = 
				spaceFactory.createContinuousSpace("space", context,
						new RandomCartesianAdder<Object>(),
						new repast.simphony.space.continuous.WrapAroundBorders(),
						gridsize, gridsize);
		
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context,
				new GridBuilderParameters<Object>(new repast.simphony.space.grid.WrapAroundBorders(),
				new SimpleGridAdder<Object>(),
					true, gridsize, gridsize));
		
		
		GridValueLayer placeMatrix = new GridValueLayer("Place Matrix", true, 
				new repast.simphony.space.grid.WrapAroundBorders(), gridsize, gridsize);
		
		context.addValueLayer(placeMatrix);
		
		clock = new Clock();
		context.add(clock);
		
		for (int i = 0; i < healthyCount; i++) {
			context.add(new Person(space, grid, false));
		}
		
		for (int i = 0; i < exposedCount; i++) {
			context.add(new Person(space, grid, true));
		}
		
		
		for (int i = 0; i < workPlaceCount; i++) {
			context.add(new Place(grid, placeMatrix, PlaceType.WORK));
		}
		
		for (int i = 0; i < leisureCount; i++) {
			context.add(new Place(grid, placeMatrix, PlaceType.LEISURE));
		}
		
		
		Place home = Place.home(grid, placeMatrix);
		context.add(home);
		
		List<Place> workPlaces = StreamSupport.stream(context.getObjects(Place.class).spliterator(), false)
				.map(Place.class::cast)
				.filter(place -> place.getType().equals(PlaceType.WORK))
				.collect(Collectors.toList());
		
		leisurePlaces = StreamSupport.stream(context.getObjects(Place.class).spliterator(), false)
				.map(Place.class::cast)
				.filter(place -> place.getType().equals(PlaceType.LEISURE))
				.collect(Collectors.toList());
		
		for(Person person : StreamSupport.stream(context.getObjects(Person.class).spliterator(), false).map(Person.class::cast).collect(Collectors.toList())) {
			if(!home.hasCapacity()) {
				home = Place.home(grid, placeMatrix);
				context.add(home);
			}
			person.setHome(home);
			home.addPerson(person);
			
			Place workPlace = null;
			double distanceFromHome = Integer.MAX_VALUE;
			for(int i = 0; i < distanceFromHome; i++) {
				workPlace = workPlaces.get(RandomHelper.nextIntFromTo(0, workPlaces.size() - 1));
				distanceFromHome = space.getDistance(space.getLocation(home), space.getLocation(workPlace));
			}
			person.setWorkPlace(workPlace);
			workPlace.addPerson(person);
		};
		
		for (Object obj : context.getObjects(Place.class)) {
			Place place = (Place) obj;
			NdPoint pt = space.getLocation(place);
			grid.moveTo(place, (int)pt.getX(), (int)pt.getY());
			placeMatrix.set(place.getType().index(), (int)pt.getX(), (int)pt.getY());
		}
		
		
		return context;
	}
	
}
