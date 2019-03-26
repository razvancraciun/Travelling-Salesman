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
	private int _populationSize;
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
	public GeneticAlgorithm(int populationSize, Mutation mutation,Selection selection, Cross cross, 
			double crossChance, double mutationChance, double elitism) {
		_observers=new ArrayList<AlgorithmObserver>();
		_populationSize=populationSize;
		_selection=selection;
		_mutation=mutation;
		_cross=cross;
		_crossChance=crossChance;
		_mutationChance=mutationChance;
		_elitism=elitism;
		
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
		for(int i=0;i<_observers.size();i++) {
			if(o.equals(_observers.get(i))) {
				return;
			}
		}
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
		return _populationSize;
	}
	
	public void run(int generations) {
		_bestDistance=Integer.MAX_VALUE;
		_bestRoute=null;
		_population=new Population(_populationSize);
		_population.add(this);
		//first evaluation
		_population.evaluate();
		for(AlgorithmObserver o : _observers) {
			o.onStart();
		}
		for(int i=0;i<generations;i++) {
			nextGeneration();
			for(AlgorithmObserver o : _observers) {
				o.onNewGeneration(i, _population.getBestFitness(),
						_population.getBestFitnessThisGeneration(),_population.getAverageFitnessThisGeneration());
			}
		}
		for(AlgorithmObserver o : _observers) {
			o.onEnd();
		}
	}

	@Override
	public void onNewBest(int bestValue, Individual best) {
		///System.out.println("best changed:alg");
		_bestDistance=bestValue;
		_bestRoute=best;
		for(AlgorithmObserver o : _observers) {
			o.onNewBest(_bestDistance, _bestRoute);
		}
		
	}

	public void setPopulationSize(int size) {
		_populationSize=size;
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
