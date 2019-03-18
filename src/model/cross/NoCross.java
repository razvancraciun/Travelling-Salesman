package model.cross;

import model.entities.Individual;
import model.entities.Pair;

public class NoCross implements Cross {

	//TODO DOC
	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		return new Pair(parent1,parent2);
	}

	public String toString() {
		return "No Cross";
	}
}
