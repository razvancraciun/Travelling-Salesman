package model.cross;

import model.entities.Individual;
import model.entities.Pair;

/**
 * A Cycle crossover method (CX) implementation
 *
 */
public class CycleCross implements Cross {

	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		
		Individual child1=new Individual(parent1.length());
		Individual child2=new Individual(parent1.length());
		initChild(child1);
		initChild(child2);
		//FIRST CHILD
		int first=parent1.getGene(0);
		int current=parent2.getGene(0);
		child1.setGene(0, parent1.getGene(0));
		//ADDING CYCLE
		while(current!=first) {
			child1.setGene(parent1.getGeneIndex(current), current);
			current=parent2.getGene(parent1.getGeneIndex(current));
		}
		//ADDING REMAINING VALUES
		for(int i=0;i<child1.length();i++) {
			if(child1.getGene(i)==-1) {
				child1.setGene(i,parent2.getGene(i));
			}
		}
		
		//CHILD 2
		first=parent2.getGene(0);
		current=parent1.getGene(0);
		child2.setGene(0, parent2.getGene(0));
		//ADDING CYCLE
		while(current!=first) {
			child2.setGene(parent2.getGeneIndex(current), current);
			current=parent1.getGene(parent2.getGeneIndex(current));
		}
		//ADDING REMAINING VALUES
				for(int i=0;i<child2.length();i++) {
					if(child2.getGene(i)==-1) {
						child2.setGene(i,parent1.getGene(i));
					}
				}
				
		return new Pair(child1,child2);
	}

	private void initChild(Individual child) {
		for(int i=0;i<child.length();i++) {
			child.setGene(i, -1);
		}
	}
	
	
	public String toString() {
		return "Cycle";
	}

}
