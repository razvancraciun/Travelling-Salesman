package model.cross;

import model.entities.Individual;
import model.entities.Pair;

public class CycleCross implements Cross {

	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		
		Individual child1=new Individual(parent1.length());
		Individual child2=new Individual(parent1.length());
		initChild(child1);
		initChild(child2);
		
		
		System.out.println("Child1: "+child1+ " Child2: "+ child2);
		return null;
	}

	private void initChild(Individual child) {
		for(int i=0;i<child.length();i++) {
			child.setGene(i, -1);
		}
	}

}
