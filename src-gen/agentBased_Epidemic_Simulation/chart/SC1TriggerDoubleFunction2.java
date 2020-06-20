package agentBased_Epidemic_Simulation.chart;

import repast.simphony.statecharts.*;
import repast.simphony.parameter.Parameters;
import static repast.simphony.random.RandomHelper.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import static repast.simphony.essentials.RepastEssentials.*;

import agentBased_Epidemic_Simulation.*;

/**
 * Trigger Function for Transition 9, from = Exposed, to = Infectious.
 */
@GeneratedFor("_iNvQ0LMPEeqM7vXeYvDqVw")
public class SC1TriggerDoubleFunction2 implements TriggerDoubleFunction<Person> {
	@Override
	public double value(Person agent, Transition<Person> transition, Parameters params) throws Exception {
		return 7;

	}
}
