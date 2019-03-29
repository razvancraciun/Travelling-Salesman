package model.selection;

import model.entities.Pair;
import model.entities.Population;

public class ProbabilisticTournamentSelection implements Selection{

	@Override
	public Population apply(Population source) {
		Population result=new Population(source);
		for(int i=0;i<result.length();i++) {
			Pair pair=source.getRandomPair();
			double chance=0.5;			//HARDCODED PROBABILITY
			if(pair.getFirst().getFitness()<pair.getSecond().getFitness()&&Math.random()<chance) {
				result.setIndividual(i, pair.getFirst());
			}
			else result.setIndividual(i, pair.getSecond());
		}
		return result;
	}

	public String toString() {
		return "Probabilistic Tournament";
	}
}
