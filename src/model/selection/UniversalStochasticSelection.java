package model.selection;

import model.entities.Individual;
import model.entities.Population;

public class UniversalStochasticSelection implements Selection{

	@Override
	public Population apply(Population source) {
		Population result=new Population(source);
		
		double[] relativeFitness=new double[source.length()];
		double sum=0.0;
	
		//Calculating relative fitness values
		for(int i=0;i<source.length();i++) {
			relativeFitness[i]=10000.0/source.getIndividual(i).getFitness();
			sum+=relativeFitness[i];
		}
		
		//Clamps values between 0 and 1
		for(int i=0;i<relativeFitness.length;i++) {
			relativeFitness[i]/=sum;
		}
		
		double target= 1.0/result.length();
		int relIndex=0;
		double current=0;
		for(int i=0;i<result.length();i++) {	
			while(current<target&&relIndex<relativeFitness.length) {
				current+=relativeFitness[relIndex];
				relIndex++;
			}
			target+=1.0/result.length();
			result.setIndividual(i, new Individual(source.getIndividual(relIndex-1)));
		}
		
		return result;
	}
	
	public String toString() {
		return "Universal Stochastic";
	}

}
