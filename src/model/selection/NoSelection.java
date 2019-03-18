package model.selection;

import model.entities.Population;

public class NoSelection implements Selection {
	
	//TODO : doc
	@Override
	public Population apply(Population source) {
		return source;
	}
	
	@Override
	public String toString() {
		return "No selection";
	}
}
