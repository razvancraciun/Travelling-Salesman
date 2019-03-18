package model.mutation;

import model.entities.Individual;

public class Inversion implements Mutation{

	@Override
	public Individual apply(Individual origin) {
		Individual result = new Individual(origin);
		int first= (int) (0.3*origin.length());		//HARDCODED VALUES
		int second=(int) (0.7*origin.length());
		for(int i=first;i<second;i++) {
			result.setGene(i, origin.getGene(second-1-i + first));
		}
		return result;
	}

}
