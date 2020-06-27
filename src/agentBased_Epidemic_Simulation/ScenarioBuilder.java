package agentBased_Epidemic_Simulation;

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
	
	private static final int gridsize = 100;

	@Override
	public Context<Object> build(Context<Object> context) {
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
		
		int healthyCount = 1000;
		for (int i = 0; i < healthyCount; i++) {
			context.add(new Person(space, grid, false));
		}
		
		int exposedCount = 10;
		for (int i = 0; i < exposedCount; i++) {
			context.add(new Person(space, grid, true));
		}
		
		int homeCount = 300;
		for (int i = 0; i < homeCount; i++) {
			context.add(new Place(grid, placeMatrix, PlaceType.HOME));
		}
		
		int workPlaceCount = 200;
		for (int i = 0; i < workPlaceCount; i++) {
			context.add(new Place(grid, placeMatrix, PlaceType.WORK));
		}
		
		int leisureCount = 50;
		for (int i = 0; i < leisureCount; i++) {
			context.add(new Place(grid, placeMatrix, PlaceType.LEISURE));
		}
		
		for (Object obj : context.getObjects(Place.class)) {
			Place place = (Place) obj;
			NdPoint pt = space.getLocation(place);
			grid.moveTo(place, (int)pt.getX(), (int)pt.getY());
			placeMatrix.set(place.getType().index(), (int)pt.getX(), (int)pt.getY());
		}
		
		List<Place> homes = StreamSupport.stream(context.getObjects(Place.class).spliterator(), false)
				.map(Place.class::cast)
				.filter(place -> place.getType().equals(PlaceType.HOME))
				.collect(Collectors.toList());
		List<Place> workPlaces = StreamSupport.stream(context.getObjects(Place.class).spliterator(), false)
				.map(Place.class::cast)
				.filter(place -> place.getType().equals(PlaceType.WORK))
				.collect(Collectors.toList());
		
		StreamSupport.stream(context.getObjects(Person.class).spliterator(), false).map(Person.class::cast).forEach(person -> {
			Place home = homes.get(RandomHelper.nextIntFromTo(0, homes.size() - 1));
			Place workPlace = workPlaces.get(RandomHelper.nextIntFromTo(0, workPlaces.size() - 1));
			person.setHome(home);
			home.addPerson(person);
			person.moveTo(home);
			person.setWorkPlace(workPlace);
			workPlace.addPerson(person);
		});
		
		
		return context;
	}
	
}
