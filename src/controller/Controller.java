package controller;

import model.cross.Cross;
import model.entities.GeneticAlgorithm;
import model.mutation.Mutation;
import model.selection.Selection;

public class Controller {
	
	private GeneticAlgorithm _alg;	
	
	/**
	 * Constructor: attaches the controller to the given algorithm
	 */
	public Controller(GeneticAlgorithm alg) {
		_alg=alg;
	}
	
	
	/**
	 * Runs the algorithm
	 * @param generations : the number of generations to run
	 */
	public void run(int generations) {
		_alg.run(generations);
	}
	

	//GETTERS AND SETTERS
	public void setAlgorithm(GeneticAlgorithm alg) {
		_alg=alg;
		
	}
	public GeneticAlgorithm getAlgorithm() {
		return _alg;
		
	}


	public void setPopulationSize(int value) {
		_alg.setPopulationSize(value);
		
	}


	public void setCrossChance(double value) {
		_alg.setCrossChance(value);
		
	}


	public void setMutationChance(double value) {
		_alg.setMutationChance(value);
		
	}


	public void setElitism(double value) {
		_alg.setElitism(value);
		
	}


	public void setSelection(Selection selection) {
		_alg.setSelection(selection);
		
	}


	public void setCross(Cross cross) {
		_alg.setCross(cross);
		
	}


	public void setMutation(Mutation mutation) {
		_alg.setMutation(mutation);
	}
}
