package model.misc;

import model.entities.Individual;

public interface AlgorithmObserver {
	public void onNewGeneration(int generation,int best, int bestThisGeneration, int averageThisGeneration);
	public void onNewBest(int bestValue,Individual best);
	public void onStart();
	public void onEnd();
}
