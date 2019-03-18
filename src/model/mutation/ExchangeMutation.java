package model.mutation;

import model.entities.Individual;

public class ExchangeMutation implements Mutation {

	@Override
	public Individual apply(Individual origin) {
		Individual result = new Individual(origin);
		int first= (int) (0.3*origin.length());		//HARDCODED VALUES
		int second=(int) (0.7*origin.length());
		result.setGene(first, origin.getGene(second));
		result.setGene(second, origin.getGene(first));
		return result;

	}

	public String toString() {
		return "Exchange Mutation";
	}
}
