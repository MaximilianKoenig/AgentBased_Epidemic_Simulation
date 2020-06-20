<?xml version="1.0" encoding="UTF-8"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns="http://repast.sf.net/statecharts" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.2/notation">
  <StateMachine xmi:id="_iNvQ0bMPEeqM7vXeYvDqVw" agentType="agentBased_Epidemic_Simulation.Person" package="agentBased_Epidemic_Simulation.chart" className="InfectionState" nextID="53" id="InfectionState" uuid="_iNvQ0LMPEeqM7vXeYvDqVw">
    <states xmi:type="State" xmi:id="_ruKeMLMPEeqM7vXeYvDqVw" id="Susceptible" uuid="_ruKeMbMPEeqM7vXeYvDqVw"/>
    <states xmi:type="State" xmi:id="_5csDsLMPEeqM7vXeYvDqVw" id="Exposed" uuid="_5csDsbMPEeqM7vXeYvDqVw"/>
    <states xmi:type="CompositeState" xmi:id="_-cT_YLMPEeqM7vXeYvDqVw" id="Infectious" uuid="_-cT_YbMPEeqM7vXeYvDqVw">
      <children xmi:type="State" xmi:id="_BAE8oLMQEeqM7vXeYvDqVw" id="Symptomatic" uuid="_BAE8obMQEeqM7vXeYvDqVw"/>
      <children xmi:type="State" xmi:id="_CXDWoLMQEeqM7vXeYvDqVw" id="non-Symptomatic" uuid="_CXDWobMQEeqM7vXeYvDqVw"/>
      <children xmi:type="PseudoState" xmi:id="_KH4OoLMQEeqM7vXeYvDqVw" id="Initial State Pointer 10" uuid="_KH41sLMQEeqM7vXeYvDqVw"/>
    </states>
    <states xmi:type="State" xmi:id="_XsGooLMQEeqM7vXeYvDqVw" id="Removed" uuid="_XsGoobMQEeqM7vXeYvDqVw"/>
    <states xmi:type="PseudoState" xmi:id="_C2ZvoLMREeqM7vXeYvDqVw" id="Entry State Pointer" type="entry"/>
    <transitions xmi:type="Transition" xmi:id="_F02TMLMQEeqM7vXeYvDqVw" from="_ruKeMLMPEeqM7vXeYvDqVw" to="_5csDsLMPEeqM7vXeYvDqVw" triggerType="message" messageCheckerType="equals" messageCheckerClass="String" messageCheckerCode="&quot;exposed&quot;;" id="Transition 8" triggerTimedCode="5" uuid="_F02TMbMQEeqM7vXeYvDqVw"/>
    <transitions xmi:type="Transition" xmi:id="_GWtqILMQEeqM7vXeYvDqVw" from="_5csDsLMPEeqM7vXeYvDqVw" to="_-cT_YLMPEeqM7vXeYvDqVw" triggerType="timed" messageCheckerClass="Object" id="Transition 9" triggerTimedCode="7;" uuid="_GWuRMLMQEeqM7vXeYvDqVw"/>
    <transitions xmi:type="Transition" xmi:id="_K1W_sLMQEeqM7vXeYvDqVw" from="_KH4OoLMQEeqM7vXeYvDqVw" to="_CXDWoLMQEeqM7vXeYvDqVw" id="Transition 11" uuid="_K1W_sbMQEeqM7vXeYvDqVw"/>
    <transitions xmi:type="Transition" xmi:id="_Lfx1sLMQEeqM7vXeYvDqVw" from="_CXDWoLMQEeqM7vXeYvDqVw" to="_BAE8oLMQEeqM7vXeYvDqVw" triggerType="timed" id="Transition 12" triggerTimedCode="7;" uuid="_Lfx1sbMQEeqM7vXeYvDqVw"/>
    <transitions xmi:type="Transition" xmi:id="_ESpEILMREeqM7vXeYvDqVw" from="_C2ZvoLMREeqM7vXeYvDqVw" to="_ruKeMLMPEeqM7vXeYvDqVw" id="Transition 23" uuid="_ESprMLMREeqM7vXeYvDqVw"/>
    <transitions xmi:type="Transition" xmi:id="_x9zlYLMREeqM7vXeYvDqVw" from="_BAE8oLMQEeqM7vXeYvDqVw" to="_XsGooLMQEeqM7vXeYvDqVw" triggerType="timed" messageCheckerClass="Object" id="Transition 27" triggerTimedCode="7;" uuid="_x9zlYbMREeqM7vXeYvDqVw"/>
  </StateMachine>
  <notation:Diagram xmi:id="_iOb0YLMPEeqM7vXeYvDqVw" type="Statechart" element="_iNvQ0bMPEeqM7vXeYvDqVw" name="statechart.rsc" measurementUnit="Pixel">
    <children xmi:type="notation:Shape" xmi:id="_ruNhgLMPEeqM7vXeYvDqVw" type="2003" element="_ruKeMLMPEeqM7vXeYvDqVw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_ruQk0LMPEeqM7vXeYvDqVw" type="5001"/>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_ruNhgbMPEeqM7vXeYvDqVw" x="309" y="120"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_5csqwLMPEeqM7vXeYvDqVw" type="2003" element="_5csDsLMPEeqM7vXeYvDqVw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_5csqwrMPEeqM7vXeYvDqVw" type="5001"/>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_5csqwbMPEeqM7vXeYvDqVw" x="317" y="192"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_-cUmcLMPEeqM7vXeYvDqVw" type="2004" element="_-cT_YLMPEeqM7vXeYvDqVw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_-cVNgbMPEeqM7vXeYvDqVw" type="5004"/>
      <children xmi:type="notation:DecorationNode" xmi:id="_-cVNgrMPEeqM7vXeYvDqVw" type="7001">
        <children xmi:type="notation:Shape" xmi:id="_BAFjsLMQEeqM7vXeYvDqVw" type="3001" element="_BAE8oLMQEeqM7vXeYvDqVw" fontName="Segoe UI">
          <children xmi:type="notation:DecorationNode" xmi:id="_BAFjsrMQEeqM7vXeYvDqVw" type="5002"/>
          <layoutConstraint xmi:type="notation:Bounds" xmi:id="_BAFjsbMQEeqM7vXeYvDqVw" x="180" y="51"/>
        </children>
        <children xmi:type="notation:Shape" xmi:id="_CXDWorMQEeqM7vXeYvDqVw" type="3001" element="_CXDWoLMQEeqM7vXeYvDqVw" fontName="Segoe UI">
          <children xmi:type="notation:DecorationNode" xmi:id="_CXD9sLMQEeqM7vXeYvDqVw" type="5002"/>
          <layoutConstraint xmi:type="notation:Bounds" xmi:id="_CXDWo7MQEeqM7vXeYvDqVw" x="12" y="51"/>
        </children>
        <children xmi:type="notation:Shape" xmi:id="_KH41sbMQEeqM7vXeYvDqVw" type="3003" element="_KH4OoLMQEeqM7vXeYvDqVw" fontName="Segoe UI">
          <layoutConstraint xmi:type="notation:Bounds" xmi:id="_KH41srMQEeqM7vXeYvDqVw" x="60" y="19" width="5" height="5"/>
        </children>
      </children>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_-cVNgLMPEeqM7vXeYvDqVw" x="195" y="276" width="298" height="193"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_XsHPsLMQEeqM7vXeYvDqVw" type="2003" element="_XsGooLMQEeqM7vXeYvDqVw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_XsHPsrMQEeqM7vXeYvDqVw" type="5001"/>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_XsHPsbMQEeqM7vXeYvDqVw" x="312" y="504"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_C2cy8LMREeqM7vXeYvDqVw" type="2007" element="_C2ZvoLMREeqM7vXeYvDqVw" fontName="Segoe UI">
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_C2cy8bMREeqM7vXeYvDqVw" x="336" y="72"/>
    </children>
    <styles xmi:type="notation:DiagramStyle" xmi:id="_iOb0YbMPEeqM7vXeYvDqVw"/>
    <edges xmi:type="notation:Edge" xmi:id="_F026QLMQEeqM7vXeYvDqVw" type="4001" element="_F02TMLMQEeqM7vXeYvDqVw" source="_ruNhgLMPEeqM7vXeYvDqVw" target="_5csqwLMPEeqM7vXeYvDqVw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_F026QbMQEeqM7vXeYvDqVw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_F026QrMQEeqM7vXeYvDqVw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_F026Q7MQEeqM7vXeYvDqVw" points="[-2, 20, -2, -64]$[-2, 104, -2, 20]"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_GWuRMbMQEeqM7vXeYvDqVw" type="4001" element="_GWtqILMQEeqM7vXeYvDqVw" source="_5csqwLMPEeqM7vXeYvDqVw" target="_-cUmcLMPEeqM7vXeYvDqVw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_GWuRMrMQEeqM7vXeYvDqVw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_GWuRM7MQEeqM7vXeYvDqVw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_GWuRNLMQEeqM7vXeYvDqVw" points="[5, 20, 0, -75]$[-16, 76, -21, -19]"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_GWvfULMQEeqM7vXeYvDqVw" id="(0.4983388704318937,0.03821656050955414)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_K1XmwLMQEeqM7vXeYvDqVw" type="4001" element="_K1W_sLMQEeqM7vXeYvDqVw" source="_KH41sbMQEeqM7vXeYvDqVw" target="_CXDWorMQEeqM7vXeYvDqVw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_K1XmwbMQEeqM7vXeYvDqVw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_K1XmwrMQEeqM7vXeYvDqVw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_K1Xmw7MQEeqM7vXeYvDqVw" points="[0, 0, 1, -45]$[2, 25, 3, -20]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_K1YN0LMQEeqM7vXeYvDqVw" id="CENTER"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_LfycwLMQEeqM7vXeYvDqVw" type="4001" element="_Lfx1sLMQEeqM7vXeYvDqVw" source="_CXDWorMQEeqM7vXeYvDqVw" target="_BAFjsLMQEeqM7vXeYvDqVw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_LfycwbMQEeqM7vXeYvDqVw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_LfycwrMQEeqM7vXeYvDqVw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_Lfycw7MQEeqM7vXeYvDqVw" points="[55, 2, -160, 2]$[217, 20, 2, 20]"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_ESprMbMREeqM7vXeYvDqVw" type="4001" element="_ESpEILMREeqM7vXeYvDqVw" source="_C2cy8LMREeqM7vXeYvDqVw" target="_ruNhgLMPEeqM7vXeYvDqVw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_ESprMrMREeqM7vXeYvDqVw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_ESprM7MREeqM7vXeYvDqVw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_ESprNLMREeqM7vXeYvDqVw" points="[0, 0, 1, -58]$[-1, 38, 0, -20]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_ESq5ULMREeqM7vXeYvDqVw" id="CENTER"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_x90McLMREeqM7vXeYvDqVw" type="4001" element="_x9zlYLMREeqM7vXeYvDqVw" source="_BAFjsLMQEeqM7vXeYvDqVw" target="_XsHPsLMQEeqM7vXeYvDqVw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_x90McbMREeqM7vXeYvDqVw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_x90McrMREeqM7vXeYvDqVw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_x90Mc7MREeqM7vXeYvDqVw" points="[-4, 6, 84, -124]$[-88, 150, 0, 20]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_x92BoLMREeqM7vXeYvDqVw" id="(0.5301204819277109,0.85)"/>
    </edges>
  </notation:Diagram>
</xmi:XMI>
