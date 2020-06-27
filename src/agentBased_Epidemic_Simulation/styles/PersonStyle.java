package agentBased_Epidemic_Simulation.styles;

import java.awt.Color;

import agentBased_Epidemic_Simulation.Person;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class PersonStyle extends DefaultStyleOGL2D {

	@Override
	public Color getColor(Object agent) {
		if(agent instanceof Person) {
			Person person = (Person) agent;
			switch(person.getInfectionStateState()) {
			case "Susceptible": return Color.GREEN;
			case "Exposed": return Color.YELLOW;
			case "non-Symptomatic": return Color.RED;
			case "Symptomatic": return Color.MAGENTA;
			case "Removed": return Color.GRAY;
			default: return Color.BLUE;
			}
		} else {
			return Color.BLACK;
		}
	}
}
