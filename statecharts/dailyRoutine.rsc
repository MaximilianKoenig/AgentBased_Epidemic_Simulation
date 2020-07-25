<?xml version="1.0" encoding="UTF-8"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns="http://repast.sf.net/statecharts" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.2/notation">
  <StateMachine xmi:id="_p5WPAc5VEeq3Rci7tbl8Sw" agentType="agentBased_Epidemic_Simulation.Person" package="agentBased_Epidemic_Simulation.chart" className="DailyRoutine" nextID="55" id="dailyRoutine" uuid="_p5WPAM5VEeq3Rci7tbl8Sw">
    <states xmi:type="PseudoState" xmi:id="_r0XxQM5VEeq3Rci7tbl8Sw" id="Entry State Pointer" type="entry"/>
    <states xmi:type="State" xmi:id="_sYRvkM5VEeq3Rci7tbl8Sw" id="Sleeping" onEnter="agent.goToBed();" uuid="_sYSWoM5VEeq3Rci7tbl8Sw"/>
    <states xmi:type="State" xmi:id="_wOBSEM5VEeq3Rci7tbl8Sw" id="Working" onEnter="agent.goToWork();" uuid="_wOBSEc5VEeq3Rci7tbl8Sw"/>
    <states xmi:type="State" xmi:id="_w9tpoM5VEeq3Rci7tbl8Sw" id="Freetime" onEnter="agent.goToSpendFreeTime();" uuid="_w9tpoc5VEeq3Rci7tbl8Sw"/>
    <states xmi:type="PseudoState" xmi:id="_0FdO8M5bEeq3Rci7tbl8Sw" id="Choice 17" uuid="_0FdO8c5bEeq3Rci7tbl8Sw" type="choice"/>
    <transitions xmi:type="Transition" xmi:id="_Iq-JIM5WEeq3Rci7tbl8Sw" from="_sYRvkM5VEeq3Rci7tbl8Sw" to="_wOBSEM5VEeq3Rci7tbl8Sw" triggerType="condition" triggerConditionCode="!agent.isTimeToSleep()" messageCheckerClass="Object" id="Transition 4" uuid="_Iq-JIc5WEeq3Rci7tbl8Sw"/>
    <transitions xmi:type="Transition" xmi:id="_JNIbAM5WEeq3Rci7tbl8Sw" from="_wOBSEM5VEeq3Rci7tbl8Sw" to="_w9tpoM5VEeq3Rci7tbl8Sw" triggerType="condition" triggerConditionCode="!agent.isTimeToWork()" messageCheckerClass="Object" id="Transition 5" uuid="_JNIbAc5WEeq3Rci7tbl8Sw"/>
    <transitions xmi:type="Transition" xmi:id="_MS5SEM5WEeq3Rci7tbl8Sw" from="_w9tpoM5VEeq3Rci7tbl8Sw" to="_sYRvkM5VEeq3Rci7tbl8Sw" triggerType="condition" triggerConditionCode="!agent.isFreeTime()" messageCheckerClass="Object" id="Transition 7" uuid="_MS5SEc5WEeq3Rci7tbl8Sw"/>
    <transitions xmi:type="Transition" xmi:id="_1REtgM5bEeq3Rci7tbl8Sw" from="_r0XxQM5VEeq3Rci7tbl8Sw" to="_0FdO8M5bEeq3Rci7tbl8Sw" id="Transition 18" uuid="_1REtgc5bEeq3Rci7tbl8Sw"/>
    <transitions xmi:type="Transition" xmi:id="_393q8M5bEeq3Rci7tbl8Sw" from="_0FdO8M5bEeq3Rci7tbl8Sw" to="_sYRvkM5VEeq3Rci7tbl8Sw" outOfBranch="true" defaultTransition="true" triggerType="condition" triggerConditionCode="agent.isTimeToSleep()" messageCheckerClass="Object" id="Transition 19" uuid="_394SAM5bEeq3Rci7tbl8Sw"/>
    <transitions xmi:type="Transition" xmi:id="_4j37AM5bEeq3Rci7tbl8Sw" from="_0FdO8M5bEeq3Rci7tbl8Sw" to="_wOBSEM5VEeq3Rci7tbl8Sw" outOfBranch="true" triggerType="condition" triggerConditionCode="agent.isTimeToWork()" messageCheckerClass="Object" id="Transition 20" uuid="_4j37Ac5bEeq3Rci7tbl8Sw"/>
    <transitions xmi:type="Transition" xmi:id="_5bpQgM5bEeq3Rci7tbl8Sw" from="_0FdO8M5bEeq3Rci7tbl8Sw" to="_w9tpoM5VEeq3Rci7tbl8Sw" outOfBranch="true" triggerType="condition" triggerConditionCode="agent.isFreeTime()" messageCheckerClass="Object" id="Transition 21" uuid="_5bpQgc5bEeq3Rci7tbl8Sw"/>
    <transitions xmi:type="Transition" xmi:id="_Wi7I4M6BEeq3Rci7tbl8Sw" from="_w9tpoM5VEeq3Rci7tbl8Sw" to="_w9tpoM5VEeq3Rci7tbl8Sw" triggerType="timed" messageCheckerClass="Object" id="Transition 49" triggerTimedCode="agent.getCurrentLeisureDuration();" uuid="_Wi7v8M6BEeq3Rci7tbl8Sw" selfTransition="true"/>
  </StateMachine>
  <notation:Diagram xmi:id="_p5XdIM5VEeq3Rci7tbl8Sw" type="Statechart" element="_p5WPAc5VEeq3Rci7tbl8Sw" name="statechart.rsc" measurementUnit="Pixel">
    <children xmi:type="notation:Shape" xmi:id="_r0gUIM5VEeq3Rci7tbl8Sw" type="2007" element="_r0XxQM5VEeq3Rci7tbl8Sw" fontName="Segoe UI">
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_r0g7MM5VEeq3Rci7tbl8Sw" x="391" y="296"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_sYSWoc5VEeq3Rci7tbl8Sw" type="2003" element="_sYRvkM5VEeq3Rci7tbl8Sw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_sYS9sM5VEeq3Rci7tbl8Sw" type="5001"/>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_sYSWos5VEeq3Rci7tbl8Sw" x="372" y="144"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_wOBSEs5VEeq3Rci7tbl8Sw" type="2003" element="_wOBSEM5VEeq3Rci7tbl8Sw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_wOB5Ic5VEeq3Rci7tbl8Sw" type="5001"/>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_wOB5IM5VEeq3Rci7tbl8Sw" x="495" y="315"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_w9uQsM5VEeq3Rci7tbl8Sw" type="2003" element="_w9tpoM5VEeq3Rci7tbl8Sw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_w9uQss5VEeq3Rci7tbl8Sw" type="5001"/>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_w9uQsc5VEeq3Rci7tbl8Sw" x="252" y="315"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_0FdO8s5bEeq3Rci7tbl8Sw" type="2006" element="_0FdO8M5bEeq3Rci7tbl8Sw" fontName="Segoe UI">
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_0FdO885bEeq3Rci7tbl8Sw" x="391" y="252"/>
    </children>
    <styles xmi:type="notation:DiagramStyle" xmi:id="_p5XdIc5VEeq3Rci7tbl8Sw"/>
    <edges xmi:type="notation:Edge" xmi:id="_Iq-wMM5WEeq3Rci7tbl8Sw" type="4001" element="_Iq-JIM5WEeq3Rci7tbl8Sw" source="_sYSWoc5VEeq3Rci7tbl8Sw" target="_wOBSEs5VEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_Iq-wMc5WEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_Iq-wMs5WEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_Iq-wM85WEeq3Rci7tbl8Sw" points="[29, -2, -63, 0]$[86, -3, -6, -1]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_YBoKEM5WEeq3Rci7tbl8Sw" id="(0.9655172413793104,0.9)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_Iq_-UM5WEeq3Rci7tbl8Sw" id="(0.29310344827586204,0.075)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_JNJCEM5WEeq3Rci7tbl8Sw" type="4001" element="_JNIbAM5WEeq3Rci7tbl8Sw" source="_wOBSEs5VEeq3Rci7tbl8Sw" target="_w9uQsM5VEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_JNJCEc5WEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_JNJCEs5WEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_JNJCE85WEeq3Rci7tbl8Sw" points="[-48, 1, 237, 0]$[-143, 1, 142, 0]$[-232, 1, 53, 0]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_JNJpIM5WEeq3Rci7tbl8Sw" id="(0.8275862068965517,0.575)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_JNJpIc5WEeq3Rci7tbl8Sw" id="(0.1016949152542373,0.6)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_MS55IM5WEeq3Rci7tbl8Sw" type="4001" element="_MS5SEM5WEeq3Rci7tbl8Sw" source="_w9uQsM5VEeq3Rci7tbl8Sw" target="_sYSWoc5VEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_MS55Ic5WEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_MS55Is5WEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_MS55I85WEeq3Rci7tbl8Sw" points="[13, -20, -43, 68]$[43, -89, -13, -1]"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_MS7HQM5WEeq3Rci7tbl8Sw" id="(0.22413793103448276,0.475)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_1RFUkM5bEeq3Rci7tbl8Sw" type="4001" element="_1REtgM5bEeq3Rci7tbl8Sw" source="_r0gUIM5VEeq3Rci7tbl8Sw" target="_0FdO8s5bEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_1RFUkc5bEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_1RFUks5bEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_1RFUk85bEeq3Rci7tbl8Sw" points="[0, 0, 1, 45]$[-1, -35, 0, 10]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_1RGisM5bEeq3Rci7tbl8Sw" id="CENTER"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_1RGisc5bEeq3Rci7tbl8Sw" id="SOUTH"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_394SAc5bEeq3Rci7tbl8Sw" type="4001" element="_393q8M5bEeq3Rci7tbl8Sw" source="_0FdO8s5bEeq3Rci7tbl8Sw" target="_sYSWoc5VEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_394SAs5bEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_394SA85bEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_394SBM5bEeq3Rci7tbl8Sw" points="[0, -9, -1, 77]$[1, -77, 0, 9]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_3945EM5bEeq3Rci7tbl8Sw" id="NORTH"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_395gIM5bEeq3Rci7tbl8Sw" id="(0.5,0.775)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_4j5JIM5bEeq3Rci7tbl8Sw" type="4001" element="_4j37AM5bEeq3Rci7tbl8Sw" source="_0FdO8s5bEeq3Rci7tbl8Sw" target="_wOBSEs5VEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_4j5JIc5bEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_4j5JIs5bEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_4j5JI85bEeq3Rci7tbl8Sw" points="[0, -9, -124, -83]$[95, 71, -29, -3]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_4j6XQM5bEeq3Rci7tbl8Sw" id="EAST"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_5bp3kM5bEeq3Rci7tbl8Sw" type="4001" element="_5bpQgM5bEeq3Rci7tbl8Sw" source="_0FdO8s5bEeq3Rci7tbl8Sw" target="_w9uQsM5VEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_5bp3kc5bEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_5bp3ks5bEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_5bp3k85bEeq3Rci7tbl8Sw" points="[-9, 0, 110, -74]$[-89, 70, 30, -4]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_5brFsM5bEeq3Rci7tbl8Sw" id="WEST"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_Wi7v8c6BEeq3Rci7tbl8Sw" type="4001" element="_Wi7I4M6BEeq3Rci7tbl8Sw" source="_w9uQsM5VEeq3Rci7tbl8Sw" target="_w9uQsM5VEeq3Rci7tbl8Sw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_Wi7v8s6BEeq3Rci7tbl8Sw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_Wi7v886BEeq3Rci7tbl8Sw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_Wi7v9M6BEeq3Rci7tbl8Sw" points="[14, 7, 30, 4]$[-13, 23, 3, 20]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_Wi8XAM6BEeq3Rci7tbl8Sw" id="(0.7627118644067796,0.425)"/>
    </edges>
  </notation:Diagram>
</xmi:XMI>
