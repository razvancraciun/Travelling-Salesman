package model.cross;

import model.entities.Individual;
import model.entities.Pair;

public class PMXCross implements Cross{

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
		
		for(int i=_point2;i<parent1.length();i++) {
			int replace1=_parent1.getGene(i);
			int index1=betweenPoints(replace1,child1);

			while(index1!=-1) {
				replace1=_parent1.getGene(index1);
				index1=betweenPoints(replace1,child1);
			}
			child1.setGene(i, replace1);

			int replace2=_parent2.getGene(i);
			int index2=betweenPoints(replace2,child2);
			while(index2!=-1) {
				replace2=_parent2.getGene(index2);
				index2=betweenPoints(replace2,child2);
			}
			child2.setGene(i, replace2); 
		}
		
		for(int i=0;i<_point1;i++) {
			int replace1=_parent1.getGene(i);
			int index1=betweenPoints(replace1,child1);
			while(index1!=-1) {
				replace1=_parent1.getGene(index1);
				index1=betweenPoints(replace1,child1);
			}
			child1.setGene(i, replace1);
			
			int replace2=_parent2.getGene(i);
			int index2=betweenPoints(replace2,child2);
			while(index2!=-1) {
				replace2=_parent2.getGene(index2);
				index2=betweenPoints(replace2,child2);
			}
			child2.setGene(i, replace2);
		}
		
		return new Pair(child1,child2);
		
	}


	/**
	 * Finds the index of a value inside an individual between _point1 and _point2
	 * @param value the value to be searched for
	 * @param ind the individual to search in
	 * @return -1 if we could not find the value between points or the index in the individual if we can
	 */
	private int betweenPoints(int value,Individual ind) {
		for(int i=_point1; i<_point2;i++) {
			if(value==ind.getGene(i))  {
				return i;
			}
		}
		return -1;
		
	}
	
	public String toString() {
		return "Partial Matching";
	}
}
