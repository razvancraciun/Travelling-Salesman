package model.selection;

import model.entities.Pair;
import model.entities.Population;

public class DeterministicTournamentSelection implements Selection{

	@Override
	public Population apply(Population source) {
		Population result=new Population(source);
		for(int i=0;i<result.length();i++) {
			Pair pair=source.getRandomPair();
			if(pair.getFirst().getFitness()<pair.getSecond().getFitness()) {
				result.setIndividual(i, pair.getFirst());
			}
			else result.setIndividual(i, pair.getFirst());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Deterministic Tournament";
	}

}
