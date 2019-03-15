package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Elite {

	private List<Individual> _elites;
	
	
	//TODO : DOC
	public Elite(Population population, double elitism) {
		population.sort();
		_elites=new ArrayList<Individual>();
		for(int i=0;i< (int) (population.length()*elitism);i++) {
			_elites.add( new Individual(population.getIndividual(i)));
		}
	}
	
	//TODO : DOC
	public void replaceWorst(Population population) {
		population.sort();
		for(int i=0;i<_elites.size();i++) {
			population.setIndividual(population.length()-_elites.size()+i, _elites.get(i));
		}
	}
	
	public String toString() {
		String result="";
		for(int i=0;i<_elites.size();i++) {
			result+=_elites.get(i).toString()+"\n";
		}
		return result;
	}
	
	//GETTERS AND SETTERS
	public int size() {
		return _elites.size();
	}
	public Individual getIndividual(int index) {
		return _elites.get(index);
	}

}
