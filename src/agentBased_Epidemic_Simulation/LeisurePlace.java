package agentBased_Epidemic_Simulation;

import repast.simphony.space.grid.Grid;
import repast.simphony.valueLayer.GridValueLayer;

public class LeisurePlace extends Place {

	public LeisurePlace(Grid<Object> grid, GridValueLayer placeMatrix) {
		super(grid, placeMatrix, PlaceType.LEISURE);
	}

}
