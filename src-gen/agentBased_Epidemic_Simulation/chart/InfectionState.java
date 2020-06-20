
package agentBased_Epidemic_Simulation.chart;

import java.util.Map;
import java.util.HashMap;

import repast.simphony.statecharts.*;
import repast.simphony.statecharts.generator.GeneratedFor;

import agentBased_Epidemic_Simulation.*;

@GeneratedFor("_iNvQ0LMPEeqM7vXeYvDqVw")
public class InfectionState extends DefaultStateChart<agentBased_Epidemic_Simulation.Person> {

	public static InfectionState createStateChart(agentBased_Epidemic_Simulation.Person agent, double begin) {
		InfectionState result = createStateChart(agent);
		StateChartScheduler.INSTANCE.scheduleBeginTime(begin, result);
		return result;
	}

	public static InfectionState createStateChart(agentBased_Epidemic_Simulation.Person agent) {
		InfectionStateGenerator generator = new InfectionStateGenerator();
		return generator.build(agent);
	}

	private InfectionState(agentBased_Epidemic_Simulation.Person agent) {
		super(agent);
	}

	private static class MyStateChartBuilder extends StateChartBuilder<agentBased_Epidemic_Simulation.Person> {

		public MyStateChartBuilder(agentBased_Epidemic_Simulation.Person agent,
				AbstractState<agentBased_Epidemic_Simulation.Person> entryState, String entryStateUuid) {
			super(agent, entryState, entryStateUuid);
			setPriority(0.0);
		}

		@Override
		public InfectionState build() {
			InfectionState result = new InfectionState(getAgent());
			setStateChartProperties(result);
			return result;
		}
	}

	private static class InfectionStateGenerator {

		private Map<String, AbstractState<Person>> stateMap = new HashMap<String, AbstractState<Person>>();

		public InfectionState build(Person agent) {
			SimpleStateBuilder<Person> ssBuilder1 = new SimpleStateBuilder<Person>("Susceptible");
			SimpleState<Person> s1 = ssBuilder1.build();
			stateMap.put("_ruKeMbMPEeqM7vXeYvDqVw", s1);
			MyStateChartBuilder mscb = new MyStateChartBuilder(agent, s1, "_ruKeMbMPEeqM7vXeYvDqVw");
			SimpleStateBuilder<Person> ssBuilder2 = new SimpleStateBuilder<Person>("Exposed");
			SimpleState<Person> s2 = ssBuilder2.build();
			stateMap.put("_5csDsbMPEeqM7vXeYvDqVw", s2);
			mscb.addRootState(s2, "_5csDsbMPEeqM7vXeYvDqVw");
			// Infectious
			CompositeState<Person> cs3 = createCS3();
			mscb.addRootState(cs3, "_-cT_YbMPEeqM7vXeYvDqVw");
			SimpleStateBuilder<Person> ssBuilder4 = new SimpleStateBuilder<Person>("Removed");
			SimpleState<Person> s4 = ssBuilder4.build();
			stateMap.put("_XsGoobMQEeqM7vXeYvDqVw", s4);
			mscb.addRootState(s4, "_XsGoobMQEeqM7vXeYvDqVw");

			createTransitions(mscb);
			return mscb.build();

		}

		// Creates CompositeState 'Infectious'
		private CompositeState<Person> createCS3() {

			SimpleStateBuilder<Person> ssBuilder5 = new SimpleStateBuilder<Person>("non-Symptomatic");
			SimpleState<Person> s5 = ssBuilder5.build();
			stateMap.put("_CXDWobMQEeqM7vXeYvDqVw", s5);

			CompositeStateBuilder<Person> csBuilder3 = new CompositeStateBuilder<Person>("Infectious", s5,
					"_CXDWobMQEeqM7vXeYvDqVw");

			SimpleStateBuilder<Person> ssBuilder6 = new SimpleStateBuilder<Person>("Symptomatic");
			SimpleState<Person> s6 = ssBuilder6.build();
			stateMap.put("_BAE8obMQEeqM7vXeYvDqVw", s6);
			csBuilder3.addChildState(s6, "_BAE8obMQEeqM7vXeYvDqVw");

			CompositeState<Person> cs3 = csBuilder3.build();
			stateMap.put("_-cT_YbMPEeqM7vXeYvDqVw", cs3);
			return cs3;
		}

		private void createTransitions(MyStateChartBuilder mscb) {
			// creates transition Transition 8
			createTransition1(mscb);
			// creates transition Transition 9
			createTransition2(mscb);
			// creates transition Transition 12
			createTransition3(mscb);
			// creates transition Transition 27
			createTransition4(mscb);

		}

		// creates transition Transition 8, from = Susceptible, to = Exposed
		private void createTransition1(MyStateChartBuilder mscb) {
			TransitionBuilder<Person> tb = new TransitionBuilder<Person>("Transition 8",
					stateMap.get("_ruKeMbMPEeqM7vXeYvDqVw"), stateMap.get("_5csDsbMPEeqM7vXeYvDqVw"));
			tb.addTrigger(new MessageTrigger<Person>(
					new MessageEqualsMessageChecker<Person, String>(new SC1MessageEquals1(), String.class), 1.0));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_F02TMbMQEeqM7vXeYvDqVw");
		}

		// creates transition Transition 9, from = Exposed, to = Infectious
		private void createTransition2(MyStateChartBuilder mscb) {
			TransitionBuilder<Person> tb = new TransitionBuilder<Person>("Transition 9",
					stateMap.get("_5csDsbMPEeqM7vXeYvDqVw"), stateMap.get("_-cT_YbMPEeqM7vXeYvDqVw"));
			tb.addTrigger(new TimedTrigger<Person>(new SC1TriggerDoubleFunction2()));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_GWuRMLMQEeqM7vXeYvDqVw");
		}

		// creates transition Transition 12, from = non-Symptomatic, to = Symptomatic
		private void createTransition3(MyStateChartBuilder mscb) {
			TransitionBuilder<Person> tb = new TransitionBuilder<Person>("Transition 12",
					stateMap.get("_CXDWobMQEeqM7vXeYvDqVw"), stateMap.get("_BAE8obMQEeqM7vXeYvDqVw"));
			tb.addTrigger(new TimedTrigger<Person>(new SC1TriggerDoubleFunction3()));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_Lfx1sbMQEeqM7vXeYvDqVw");
		}

		// creates transition Transition 27, from = Symptomatic, to = Removed
		private void createTransition4(MyStateChartBuilder mscb) {
			TransitionBuilder<Person> tb = new TransitionBuilder<Person>("Transition 27",
					stateMap.get("_BAE8obMQEeqM7vXeYvDqVw"), stateMap.get("_XsGoobMQEeqM7vXeYvDqVw"));
			tb.addTrigger(new TimedTrigger<Person>(new SC1TriggerDoubleFunction4()));
			tb.setPriority(0.0);
			mscb.addRegularTransition(tb.build(), "_x9zlYbMREeqM7vXeYvDqVw");
		}

	}
}
