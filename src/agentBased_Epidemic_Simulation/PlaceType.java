package agentBased_Epidemic_Simulation;

import java.awt.Color;

public enum PlaceType {
	HOME(new Color(100, 100, 188)), WORK(new Color(188, 188, 100)), LEISURE(new Color(188, 100, 100));
	
	public final Color color;

	PlaceType(Color c) {
		this.color = c;
		
	}
	
	public int index() {
		return PlaceType.indexOf(this) + 1;
	}
	
	public static int indexOf(PlaceType type) {
		for(int i = 0; i < values().length; i++)
			if(values()[i].equals(type)) return i;
		throw new RuntimeException("Unknown place type "+type);
	}
}
