package model.entities;

import java.util.LinkedList;
import java.util.List;

import model.Distances;
import model.cross.Cross;

public class Population {
	private Individual[] _population;
	private int _bestFitness;
	private int _bestFitnessThisGeneration;
	
	/**
	 * Constructor: fills in the population array and sets fitness values to MAX (since we want to minimize them)
	 * @size: the size of the generated population
	 */
	public Population(int size) {
		//we want to minimize the function
		_bestFitness=Integer.MAX_VALUE;
		_bestFitnessThisGeneration=Integer.MAX_VALUE;
		_population=new Individual[size];
		for(int i=0;i<_population.length;i++) {
			_population[i]=new Individual(Distances.getInstance().length());
		}
	}
	
	/**Copy constructor */
	public Population(Population other) {
		_bestFitness=other.getBestFitness();
		_bestFitnessThisGeneration=other.getBestFitnessThisGeneration();
		_population=new Individual[other.length()];
		for(int i=0;i<_population.length;i++) {
			_population[i]=new Individual(other.getIndividual(i));
		}
	}
	
	
	/** Calls the evaluate() method of each individual, thus updating their fitness 
	 *  Also updates the _bestFitness and _bestFitnessThisGeneration fields if there is the case
	 */
	public void evaluate() {		
		for(int i=0;i<_population.length;i++) {
			_population[i].evaluate();
			int fitness=_population[i].getFitness();
			if(fitness<_bestFitnessThisGeneration) {
				_bestFitnessThisGeneration=fitness;
				if(fitness<_bestFitness) {
					_bestFitness=fitness;
				}
			}		
		}
	}
	
	//TODO : DOC
	public void cross(Cross cross, double crossChance) {
		List<Pair> pairs = new LinkedList<Pair>();
		int chosen=0;
		Individual previous=null;
		for(int i=0;i<_population.length;i++) {
			if(Math.random()<=crossChance) {
				chosen++;
				if(chosen%2==0) {
					pairs.add(new Pair(previous,_population[i]));
				}
				else {
					previous=_population[i];
				}
			}
		}
		for(Pair x : pairs) {
			cross.apply(x);
		}
	}
	

	

	public String toString() {
		String result= "";
		for(int i=0;i<_population.length;i++) {
			result+=_population[i].toString()+"\n";
		}
		result+="Best overall: "+_bestFitness+" || "+"BestThisGeneration: "+_bestFitnessThisGeneration;
		return result;
	}
	
	//GETTERS & SETTERS
	private int length() {
		return _population.length;
	}
	private Individual getIndividual(int i) {
		return _population[i];
	}
	private int getBestFitnessThisGeneration() {
		return _bestFitnessThisGeneration;
	}

	private int getBestFitness() {
		return _bestFitness;
	}
}
