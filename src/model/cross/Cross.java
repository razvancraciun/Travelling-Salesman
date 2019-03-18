package model.cross;

import model.entities.Individual;
import model.entities.Pair;

public interface Cross {
	public Pair apply(Individual parent1, Individual parent2);
	public String toString();
}
