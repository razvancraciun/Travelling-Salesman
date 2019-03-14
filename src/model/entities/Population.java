package model.entities;

import model.Distances;

public class Population {
	private Individual[] _population;
	private int _bestFitness;
	private int _bestFitnessThisGeneration;
	
	//Constructor: fills in the population array and sets fitness values to MAX
	public Population(int size) {
		//we want to minimise the function
		_bestFitness=Integer.MAX_VALUE;
		_bestFitnessThisGeneration=Integer.MAX_VALUE;
		_population=new Individual[size];
		for(int i=0;i<_population.length;i++) {
			_population[i]=new Individual(Distances.getInstance().length());
		}
	}
	
	
	public String toString() {
		String result= "";
		for(int i=0;i<_population.length;i++) {
			result+=_population[i].toString();
		}
		result+="Best overall: "+_bestFitness+"BestThisGeneration: "+_bestFitnessThisGeneration;
		return result;
	}
	
	//GETTERS & SETTERS
	
}
