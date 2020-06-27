package agentBased_Epidemic_Simulation;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.space.grid.Grid;
import repast.simphony.valueLayer.GridValueLayer;

public class Place {
	
	private PlaceType type;
	private List<Person> people;


	public Place(Grid<Object> grid, GridValueLayer placeMatrix, PlaceType type) {
		this.type = type;
		this.people = new ArrayList<>();
		//grid.moveTo(this, x, y);
	}
	
	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	public void addPerson(Person person) {
		this.people.add(person);
	}
}
