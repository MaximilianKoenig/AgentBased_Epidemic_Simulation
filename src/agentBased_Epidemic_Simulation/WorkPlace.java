package agentBased_Epidemic_Simulation;

import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.valueLayer.GridValueLayer;

public class WorkPlace extends Place {
	
	private boolean hasHomeOffice;

	public WorkPlace(Grid<Object> grid, GridValueLayer placeMatrix) {
		super(grid, placeMatrix, PlaceType.WORK);
	}

	public boolean hasHomeOffice() {
		return hasHomeOffice;
	}

	public void setHasHomeOffice(boolean hasHomeOffice) {
		this.hasHomeOffice = hasHomeOffice;
	}

}
