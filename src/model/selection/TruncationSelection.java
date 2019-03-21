package model.selection;

import model.entities.Individual;
import model.entities.Population;

public class TruncationSelection implements Selection{

	@Override
	public Population apply(Population source) {
		Population result=new Population(source);
		source.sort();
		
		for(int i=0;i<result.length();i++) {
			result.setIndividual(i, new Individual(source.getIndividual(i/2)));
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Trucantion";
	}

}
