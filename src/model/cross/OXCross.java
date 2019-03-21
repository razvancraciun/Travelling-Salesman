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
		
		//clearValuesBetweenPoints
		
		
		return null;
	}

}
