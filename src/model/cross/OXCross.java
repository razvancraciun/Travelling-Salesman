package model.cross;

import model.entities.Individual;
import model.entities.Pair;

public class OXCross implements Cross{

	private Individual _parent1;
	private Individual _parent2;
	
	private int _point1;
	private int _point2;

	
	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		_parent1=parent1;
		_parent2=parent2;
		
		_point1 = (int)(parent1.length()*0.3);  // HARDCODED VALUES at 30 and 80 % of the parents length
		_point2 = (int)(parent1.length()*0.8);
		
		Individual child1=new Individual(parent2);		//Switching
		Individual child2=new Individual(parent1);
		
		resetOutsidePoints(child1);
		resetOutsidePoints(child2);
		
		int parentIndex1=_point2;
		int parentIndex2=_point2;
		for(int i=_point2;i<child1.length();i++) {
			while(child1.hasGene(_parent1.getGene(parentIndex1%_parent1.length()))) {
				parentIndex1++;
			}
			child1.setGene(i, _parent1.getGene(parentIndex1%_parent1.length()));
			
			while(child2.hasGene(_parent2.getGene(parentIndex2%_parent2.length()))) {
				parentIndex2++;
			}
			child2.setGene(i, _parent2.getGene(parentIndex2%_parent2.length()));
		}
		for(int i=0;i<_point1;i++) {
			while(child1.hasGene(_parent1.getGene(parentIndex1%_parent1.length()))) {
				parentIndex1++;
			}
			child1.setGene(i, _parent1.getGene(parentIndex1%_parent1.length()));
			
			while(child2.hasGene(_parent2.getGene(parentIndex2%_parent2.length()))) {
				parentIndex2++;
			}
			child2.setGene(i, _parent2.getGene(parentIndex2%_parent2.length()));
		}
		
		return new Pair(child1,child2);
	}
	
	public void resetOutsidePoints(Individual ind) {
		for(int i=0;i<_point1;i++) {
			ind.setGene(i, -1);
		} 
		for(int i=_point2;i<ind.length();i++) {
			ind.setGene(i, -1);
		}
	}
	
	
	
	public String toString() {
		return "Order";
	}

}
