package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.cross.Cross;
import model.misc.AlgorithmObserver;
import model.misc.PopulationObserver;
import model.mutation.Mutation;
import model.selection.Selection;

public class GeneticAlgorithm implements PopulationObserver {
	private Population _population;
	private Mutation _mutation;
	private Selection _selection;
	private Cross _cross;
	private double _crossChance;
	private double _mutationChance;
	private double _elitism;
	private List<AlgorithmObserver> _observers;
	
	private int _bestDistance;
	private Individual _bestRoute;
	
	
	/** Initializes the genetic algorithm parameters 
	 * @population : the population that this algorithm will run on
	 * @mutation : the mutation type that will be applied
	 * @selection : the selection type that will be applied
	 * @cross : the cross type that will be applied
	 * @crossChance : the chance that an individual will be selected for cross
	 * @mutationChance: the chance for a mutation to occur
	 * @elitism : coefficient of the population size that will be considered elites
	 * */
	public GeneticAlgorithm(Population population, Mutation mutation,Selection selection, Cross cross, 
			double crossChance, double mutationChance, double elitism) {
		_observers=new ArrayList<AlgorithmObserver>();
		_population=population;
		_population.add(this);
		_selection=selection;
		_mutation=mutation;
		_cross=cross;
		_crossChance=crossChance;
		_mutationChance=mutationChance;
		_elitism=elitism;
		
		//first evaluation
		_population.evaluate();
		
	}
	
	/** Advances one generation */
	public void nextGeneration() {
		Elite elite=new Elite(_population,_elitism);
		_population=_selection.apply(_population);
		_population.cross(_cross,_crossChance);
		_population.mutate(_mutation,_mutationChance);
		elite.replaceWorst(_population);
		_population.evaluate();
	}
	
	public void addObserver(AlgorithmObserver o) {
		_observers.add(o);
	}
	
	public String toString() {
		return _population.toString();
	}

	public Selection getSelection() {
		return _selection;
	}
	public Mutation getMutation() {
		return _mutation;
	}
	public Cross getCross() {
		return _cross;
	}

	public int getPopulationSize() {
		return _population.length();
	}

	@Override
	public void onNewBest(int bestValue, Individual best) {
		System.out.println("best changed:alg");
		_bestDistance=bestValue;
		_bestRoute=best;
		for(AlgorithmObserver o : _observers) {
			o.onNewBest(_bestDistance, _bestRoute);
		}
		
	}

	public void setPopulation(Population population) {
		_population=population;
		_population.add(this);
		_bestDistance=Integer.MAX_VALUE;
		_bestRoute=null;
	}

	public void setCrossChance(double value) {
		_crossChance=value;
	}

	public void setMutationChance(double value) {
		_mutationChance=value;
		
	}

	public void setElitism(double value) {
		_elitism=value;
		
	}

	public void setSelection(Selection selection) {
		_selection=selection;
		
	}

	public void setCross(Cross cross) {
		_cross=cross;
		
	}

	public void setMutation(Mutation mutation) {
		_mutation=mutation;
		
	}
	
	
}
