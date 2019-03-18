package controller;

import model.entities.GeneticAlgorithm;

public class Controller {
	
	private GeneticAlgorithm _alg;
	
	
	//TODO DOC
	public Controller(GeneticAlgorithm alg) {
		_alg=alg;
	}
	
	
	//TODO DOC
	public void run(int generations) {
		System.out.println(_alg.getSelection()+" "+_alg.getMutation() + " "+ _alg.getCross());
		for(int i=0;i<generations;i++) {
			_alg.nextGeneration();
			//System.out.println(_alg);
		}
	}
}
