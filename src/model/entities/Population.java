package model.entities;

import java.util.Arrays;
import java.util.Comparator;

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
		_bestFitnessThisGeneration=Integer.MAX_VALUE;
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
	
	/**
	 * Crosses Individuals based on the crossChance creating new child Individuals
	 * @param cross : The cross method to be used
	 * @param crossChance The chance that an Individual will be selected for cross
	 */
	public void cross(Cross cross, double crossChance) {
		int chosen=0;
		int previous=-1;
		for(int i=0;i<_population.length;i++) {
			if(Math.random()<=crossChance) {
				chosen++;
				if(chosen%2==0) {
					Pair pair=cross.apply(_population[previous], _population[i]);
					_population[previous]=pair.getFirst();
					_population[i]=pair.getSecond();
					
				}
				else {
					previous=i;
				}
			}
		}
	}
	
	/**
	 * Sorts Individuals by fitness. The ones with the best values will be first (lowest fitness since we want to minimise function)
	 */
	public void sort() {
		Arrays.sort(_population, new Comparator<Individual>() {
			public int compare(Individual first, Individual second) {
				if(first.getFitness()<second.getFitness()) {
					return -1;
				}
				else if(first.getFitness()==second.getFitness()) 
					return 0;
				else return 1;
			}
		});
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
	public int length() {
		return _population.length;
	}
	public Individual getIndividual(int i) {
		return _population[i];
	}
	public int getBestFitnessThisGeneration() {
		return _bestFitnessThisGeneration;
	}

	public int getBestFitness() {
		return _bestFitness;
	}
	
	public int getMaximumFitness() {
		int max=_population[0].getFitness();
		for(int i=1;i<_population.length;i++) {
			if(_population[i].getFitness()>max) {
				max=_population[i].getFitness();
			}
		}
		return max;
	}
	
	public void setIndividual(int index,Individual ind) {
		_population[index]=ind;
	}
}
