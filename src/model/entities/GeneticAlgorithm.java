package model.entities;

import model.cross.Cross;
import model.mutation.Mutation;
import model.selection.Selection;

public class GeneticAlgorithm {
	public Population _population;
	public Mutation _mutation;
	public Selection _selection;
	public Cross _cross;
	public double _crossChance;
	
	
	/** Initializes the genetic algorithm parameters 
	 * @population : the population that this algorithm will run on
	 * @mutation : the mutation type that will be applied
	 * @selection : the selection type that will be applied
	 * @cross : the cross type that will be applied
	 * @crossChance : the chance that an individual will be selected for cross*/
	public GeneticAlgorithm(Population population, Mutation mutation,Selection selection, Cross cross, double crossChance) {
		_population=population;
		_selection=selection;
		_mutation=mutation;
		_cross=cross;
		_crossChance=crossChance;
		
		//first evaluation
		_population.evaluate();
		
	}
	
	/** Advances one generation */
	public void nextGeneration() {
		_population=_selection.apply(_population);
		_population.cross(_cross,_crossChance);
		//TODO: apply mutation
		
		_population.evaluate();
	}
	
	public String toString() {
		return _population.toString();
	}
	
	
}
