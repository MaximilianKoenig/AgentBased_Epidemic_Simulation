package agentBased_Epidemic_Simulation.styles;

import java.awt.Color;

import agentBased_Epidemic_Simulation.PlaceType;
import repast.simphony.valueLayer.ValueLayer;
import repast.simphony.visualizationOGL2D.ValueLayerStyleOGL;

public class PlaceStyle implements ValueLayerStyleOGL {

	protected ValueLayer layer;

	public void init(ValueLayer layer) {
		this.layer = layer;
	}

	public float getCellSize() {
		return 15.0f;
	}

	/**
	 * Return the color based on the value at given coordinates.
	 */
	public Color getColor(double... coordinates) {
		int index = (int)layer.get(coordinates);
		if(index > 0) {
			PlaceType type = PlaceType.values()[index-1];
			return type.color;
		} else {
			return Color.WHITE;
		}
	}
}