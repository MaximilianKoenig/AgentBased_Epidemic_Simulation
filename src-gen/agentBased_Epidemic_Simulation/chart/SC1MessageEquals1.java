package agentBased_Epidemic_Simulation.chart;

import repast.simphony.statecharts.*;
import repast.simphony.parameter.Parameters;
import static repast.simphony.random.RandomHelper.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import static repast.simphony.essentials.RepastEssentials.*;

import agentBased_Epidemic_Simulation.*;

/**
 *  MessageEquals for Transition 8, from = Susceptible, to = Exposed.
 */
@GeneratedFor("_iNvQ0LMPEeqM7vXeYvDqVw")
public class SC1MessageEquals1 implements MessageEquals<Person> {
	@Override
	public Object messageValue(Person agent, Transition<Person> transition, Parameters params) throws Exception {
		return "exposed";

	}
}
