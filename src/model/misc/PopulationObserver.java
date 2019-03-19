package model.misc;

import model.entities.Individual;

public interface PopulationObserver {
	public void onNewBest(int bestValue,Individual best);
}
