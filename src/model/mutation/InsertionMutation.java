package model.mutation;

import model.entities.Individual;

public class InsertionMutation implements Mutation{

	@Override
	public Individual apply(Individual origin) {
		
		Individual result=new Individual(origin);
		
		for(int i=0;i<origin.length()/4;i++) {
			int from = (int) (Math.random()*origin.length());
			int to = (int) (Math.random()*origin.length());
			
			int value=result.getGene(from);
			
			result.removeAtIndex(from);
			result.addAtIndex(to, value);
		}
		
		
		return result;
	}
	
	public String toString() {
		return "Insertion";
	}

}
