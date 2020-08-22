package agentBased_Epidemic_Simulation;

import repast.simphony.space.grid.Grid;
import repast.simphony.valueLayer.GridValueLayer;

public class HomePlace extends Place {
	
	private int capacity = Integer.MAX_VALUE;

	public HomePlace(Grid<Object> grid, GridValueLayer placeMatrix) {
		super(grid, placeMatrix, PlaceType.HOME);
	}
	
	public boolean hasCapacity() {
		return getCapacity() > people.size();
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
