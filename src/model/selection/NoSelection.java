package model.selection;

import model.entities.Population;

public class NoSelection implements Selection {
	
	//TODO : doc
	@Override
	public Population apply(Population source) {
		System.out.println("No selection applied");
		return source;
	}

}
