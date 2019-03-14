package model.selection;

import model.entities.Population;

public interface Selection {
	public Population apply(Population source);
}
