package agentBased_Epidemic_Simulation;

import java.util.ArrayList;
import java.util.List;

import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.valueLayer.GridValueLayer;

public abstract class Place {
	
	protected PlaceType type;
	protected List<Person> people;
	
	

	public static HomePlace home(Grid<Object> grid, GridValueLayer placeMatrix) {
		HomePlace home = new HomePlace(grid, placeMatrix);
		// https://www.destatis.de/DE/Themen/Gesellschaft-Umwelt/Bevoelkerung/Haushalte-Familien/Tabellen/1-1-privathaushalte-haushaltsmitglieder.html
		home.setCapacity(Random.discrete(new Integer[] {1, 2, 3, 4, 5}, new double[] {0.423, 0.332, 0.119, 0.091, 0.035}));
		return home;
	}

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
