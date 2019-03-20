package model.cross;

import model.entities.Individual;
import model.entities.Pair;

public class PMXCross implements Cross{

	//TODO: DOC
	//TODO BUGGY
	//test if also the second chice is in the array
	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		
		int point1 = (int)(parent1.length()*0.3);  // HARDCODED VALUES at 30 and 80 % of the parents length
		int point2 = (int)(parent1.length()*0.8);
		
		Individual child1=new Individual(parent2);		//Switching
		Individual child2=new Individual(parent1);
		
		for(int i=1;i<point1;i++) { //reseting values that are not in the middle
			child1.setGene(i, -1);
			child2.setGene(i, -1);
		}
		for(int i=point2;i<child1.length();i++) {
			child1.setGene(i, -1);
			child2.setGene(i, -1);
		}
		
		
		for(int i=point2;i<parent1.length();i++) { //filling children values...
			if(child1.hasGene(parent1.getGene(i))) {
				int index=child1.getGeneIndex(parent1.getGene(i));
				child1.setGene(i, parent1.getGene(index));
			}
			else {
				child1.setGene(i,parent1.getGene(i));
			}
			if(child2.hasGene(parent2.getGene(i))) {
				int index=child2.getGeneIndex(parent2.getGene(i));
				child2.setGene(i, parent2.getGene(index));
			}
			else {
				child2.setGene(i,parent2.getGene(i));
			}
		}
		
		for(int i=0;i<point1;i++) { //filling children values...
			if(child1.hasGene(parent1.getGene(i))) {
				int index=child1.getGeneIndex(parent1.getGene(i));
				child1.setGene(i, parent1.getGene(index));
			}
			else {
				child1.setGene(i,parent1.getGene(i));
			}
			if(child2.hasGene(parent2.getGene(i))) {
				int index=child2.getGeneIndex(parent2.getGene(i));
				child2.setGene(i, parent2.getGene(index));
			}
			else {
				child2.setGene(i,parent2.getGene(i));
			}
		}

		if(!child1.valid()||!child2.valid()) {
			throw new IllegalArgumentException("Still buggy");
		}
		
		return new Pair(child1,child2);
	}

	public String toString() {
		return "PMX Cross";
	}
}
