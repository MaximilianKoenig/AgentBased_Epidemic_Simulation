package agentBased_Epidemic_Simulation;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;

public class ScenarioBuilder implements ContextBuilder<Object> {

	@Override
	public Context<Object> build(Context<Object> context) {
		context.setId("AgentBased_Epidemic_Simulation");
		
		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = 
				spaceFactory.createContinuousSpace("space", context,
						new RandomCartesianAdder<Object>(),
						new repast.simphony.space.continuous.WrapAroundBorders(),
						250, 250);
		
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context,
				new GridBuilderParameters<Object>(new repast.simphony.space.grid.WrapAroundBorders(),
				new SimpleGridAdder<Object>(),
					true, 250, 250));
		
		int healthyCount = 10000;
		for (int i = 0; i < healthyCount; i++) {
			context.add(new Person(space, grid, false));
		}		
		
		int exposedCount = 10;
		for (int i = 0; i < exposedCount; i++) {
			context.add(new Person(space, grid, true));
		}
		
		for (Object obj : context) {
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int)pt.getX(), (int)pt.getY());
		}
		
		return context;
	}
	
}
